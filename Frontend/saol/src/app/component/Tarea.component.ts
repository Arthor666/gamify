import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';

import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Equipo } from '../models/Equipo';
import { animate, state, style, transition, trigger } from '@angular/animations';
import { FileService } from '../service/File.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Tarea } from '../models/Tarea';
import { TareaService } from '../service/Tarea.service';
import { Usuario } from '../models/Usuario';
import { COMMA, ENTER } from '@angular/cdk/keycodes';
import { MatAutocompleteSelectedEvent } from '@angular/material/autocomplete';
import { Status } from '../models/Status';
import { StatusService } from '../service/Status.service';
import { UsuarioService } from '../service/Usuario.service';
import { EquipoService } from '../service/Equipo.service';
import { Proyecto } from '../models/Proyecto';
import { ProyectoService } from '../service/Proyecto.service';
import { ActivatedRoute } from '@angular/router';
import { globalEnum } from '../globalEnum';
import { HistoriaUsuarioService } from '../service/HistoriaUsuario.service';
import { HistoriaUsuario } from '../models/HistoriaUsuario';
import * as CryptoJS from 'crypto-js';


@Component({
  selector: 'recompensa-admin',
  templateUrl: '../html/Tarea.component.html',
  styleUrls: ['../css/Tarea.component.css'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({ height: '0px', minHeight: '0' })),
      state('expanded', style({ height: '*' })),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ]
})
export class TareaComponent implements OnInit {
  minDate: Date;
  newTarea: Tarea;
  separatorKeysCodes: number[] = [ENTER, COMMA];
  displayedColumns: string[] = ['id', 'nombre','proyecto' ,'descripcion', 'fechaCreacion','fechaTentativa','autor','status'];
  tareaPage!: MatTableDataSource<Tarea>;
  copyTareaPage!: MatTableDataSource<Tarea>;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  expandedElement: Tarea | null | undefined;
  files: Array<File>;
  cadenaBuscar: string = "";
  showChip: boolean = false;  
  statusList: Array<Status> = [];
  blob!: Blob;
  proyecto: Proyecto;
  usuarioId: number;
  equipoId: number = -1;
  usuariosSelectList: Array<Usuario>;
  historiasUsuarios: Array<HistoriaUsuario>;

  constructor(private historiaService: HistoriaUsuarioService, private snackBar: MatSnackBar, private route: ActivatedRoute, private usuarioService: UsuarioService, private tareaService: TareaService, private statusService: StatusService, private fileService: FileService, private _snackBar: MatSnackBar, private proyectoService: ProyectoService) {
    this.minDate = new Date();
    this.newTarea = new Tarea({});
    this.files = [];
    this.equipoId = Number(CryptoJS.enc.Utf8.stringify(CryptoJS.AES.decrypt(this.route.snapshot.paramMap.get("idEquipo").replace("*", "/"), globalEnum.secret)));
    this.tareaPage = new MatTableDataSource<Tarea>([new Tarea({ "nombre": "Crear tarea" })]);
    this.usuarioId = Number(JSON.parse(CryptoJS.enc.Utf8.stringify(CryptoJS.AES.decrypt(localStorage.getItem(globalEnum.usuarioLocalStorage), globalEnum.secret))).id);
  }


  ngOnInit() {
    this.tareaService.getTareasByAutorIdAndEquipoId(this.usuarioId, this.equipoId).subscribe(data => this.iniciarPaginacion(data));
    this.statusService.getByClase("tarea").subscribe(data => this.statusList = data);
    this.proyectoService.getByEquipoId(this.equipoId).subscribe(data => {
      this.proyecto = data;
      this.statusService.getByClase(this.proyecto.nombre).subscribe(data => this.statusList.push(...data));
    });
    this.usuarioService.getUserByEquipoId(this.equipoId).subscribe(data => this.usuariosSelectList = data.filter((x, i) => { return x.id != this.usuarioId }));
  }

  openFile(file: string) {
    this.fileService.getFile(file).subscribe(data => {     
      this.blob = new Blob([data], { type: 'application/pdf' });
      var downloadURL = window.URL.createObjectURL(data);
      var link = document.createElement('a');
      link.href = downloadURL;
      link.download = file;
      link.click();

    });
  }



  removeFile(file: string) {    
    let aux = this.newTarea.files.split(",")    
    aux.splice(aux.indexOf(file), 1);
    this.newTarea.files = aux.join();
  }

  remove(usuario: Usuario) {
    const index = this.newTarea.etiquetados.findIndex((x: Usuario) => x.id == usuario.id);
    if (index != -1) {
      this.newTarea.etiquetados.splice(index, 1);
    }    
  }

  iniciarPaginacion(data: Tarea[]): void {    
    this.tareaPage.data.push(...data);
    this.tareaPage.paginator = this.paginator;
  }

  compareFunction(o1: any, o2: any) {    
    return o1 && o2 ? o1.id === o2.id : o1 === o2;
  }

  expandir(element: any): void {    
    this.newTarea = element;  
    this.expandedElement = this.expandedElement === element ? null : element;
    if (this.historiasUsuarios == undefined) {
      this.historiaService.getByEquipoId(this.equipoId).subscribe(data => this.historiasUsuarios = data);
    }
    if (this.newTarea.id != undefined  ) {
      this.historiaService.getByTareaId(this.newTarea.id).subscribe(data => this.newTarea.historiasUsuario = data);
      if (this.newTarea.etiquetados == undefined) {
        this.usuarioService.getUserByEtiquetado(this.newTarea.id).subscribe(data =>
          this.newTarea.etiquetados = data
        );
      }      
    }

  }

  updateEtiquetados() {
    this.usuarioService.getUserByEquipoId(this.newTarea.equipo.id).subscribe(data => {
      this.newTarea.equipo = new Equipo({ id: this.equipoId, "usuarios": [] });
      data.forEach(x => { if (x.id != this.usuarioId) { this.newTarea.equipo.usuarios.push(x) } });
    });
  }


  onFileSelected(event: any) {
    let size: number = 0;    
    Array.from(event.target.files).forEach(file => {
      if (file instanceof File) {
        size += file.size;
      }
    });
    if (size > 1048576) {
      this._snackBar.open("Archivos muy pesados", "Cerrar");
      return
    }
    this.files = event.target.files;
    const fnames: string[] = [];
    Array.from(this.files).forEach(file => fnames.push(file.name));
    if (this.newTarea.files != undefined) {
      this.newTarea.files += "," + fnames.join();
    } else {
      this.newTarea.files = fnames.join();
    }
  }


  deleteBusqueda() {
    this.showChip = false;
    this.cadenaBuscar = "";
    this.tareaPage = this.copyTareaPage;
  }

  buscar() {
    this.tareaService.getByNombreLike(this.cadenaBuscar).subscribe(data => { 
      this.copyTareaPage = this.tareaPage;
      this.tareaPage = new MatTableDataSource<Tarea>(data)
    });
    this.showChip = true;
  }

  selected(event:MatAutocompleteSelectedEvent) {
    const exist = this.newTarea.etiquetados.indexOf(event.option.value);
    if (exist == -1) {
      this.newTarea.etiquetados.push(event.option.value);
    }
  }

  guardar() {
    if (this.newTarea.id == undefined) {
      this.newTarea.status = new Status({ "id": this.statusList.filter((x, i) => { return x.nombre == "Por hacer" })[0].id });
    }
    this.newTarea.autor = new Usuario({ "id": this.usuarioId });
    this.newTarea.equipo = new  Equipo({ "id": this.equipoId });
    const e = new Tarea(this.newTarea);
    if (this.files.length > 0) {      
      const formData = new FormData();
      Array.from(this.files).forEach(file => formData.append("files", file));
      this.fileService.save(formData).subscribe(data => console.log(data));
    }    
    this.tareaService.save(e).subscribe(data => {
      if (this.newTarea.id == undefined) {
        this.tareaPage.data.push(data);        
        this.tareaPage.paginator = this.paginator;        
      }
      this.snackBar.open("Tarea guardada", "Ok");
    });
  }

  optionText(op: Equipo) {
    if (op == null) { return ""; }
    return op.nombre;

  }
}
