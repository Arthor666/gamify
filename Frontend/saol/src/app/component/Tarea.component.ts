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
  newTarea: any;
  separatorKeysCodes: number[] = [ENTER, COMMA];
  displayedColumns: string[] = ['id', 'nombre','proyecto' ,'descripcion', 'fechaCreacion','fechaTentativa','autor','status'];
  tareaPage!: MatTableDataSource<Tarea>;
  copyTareaPage!: MatTableDataSource<Tarea>;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  expandedElement: Tarea | null | undefined;
  files: Array<File>;
  cadenaBuscar: string = "";
  showChip: boolean = false;
  usuarioOptionList: Array<Usuario> = [];
  statusList: Array<Status> = [];
  blob!: Blob;
  proyectosOptionList!: Array<Proyecto>;
  usuarioId: number = 57;

  constructor(private usuarioService: UsuarioService, private tareaService: TareaService, private statusService: StatusService, private fileService: FileService, private _snackBar: MatSnackBar, private proyectoService: ProyectoService) {
    this.minDate = new Date();
    this.newTarea = {};
    this.files = [];
    //this.usuarioRecompensaPage = new MatTableDataSource<Tarea>([new Tarea({ "nombre": "Crear recompensa" })]);
    this.tareaPage = new MatTableDataSource<Tarea>([new Tarea({ "nombre": "Crear tarea" })]);
  }


  ngOnInit() {
    this.tareaService.getTareasByUsuarioId(this.usuarioId).subscribe(data => this.iniciarPaginacion(data));
    this.proyectoService.getByUsuarioId(this.usuarioId).subscribe(data => this.proyectosOptionList =data);
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

  expandir(element: any): void {
    if (element.autor != undefined && element.autor.id != this.usuarioId) {
      return 
    }
    this.newTarea = element;  
    this.expandedElement = this.expandedElement === element ? null : element;
    if (this.statusList.length <= 0) {
      this.statusService.getByClase("tarea").subscribe(data => this.statusList = data);
    }
    if (this.newTarea.proyecto != undefined) {
      this.usuarioService.getUserByProyectoId(this.newTarea.proyecto.id).subscribe(data => {
        this.newTarea.proyecto.equipo = new Equipo({ "usuarios": [] });
        data.forEach(x => { if (x.id != this.usuarioId) { this.newTarea.proyecto.equipo.usuarios.push(x) } });
      });
    } else {
      this.newTarea.proyecto = new Proyecto({ "id": 0 });
    }
    if (this.newTarea.id != undefined) {
      this.usuarioService.getUserByEtiquetado(this.newTarea.id).subscribe(data => this.newTarea.etiquetados = data);
    } else {
      this.newTarea.autor = new Usuario({ "id": this.usuarioId });
      this.newTarea.etiquetados = [];
      this.newTarea.status = new Status({});
    }
  }

  updateEtiquetados() {    
    this.usuarioService.getUserByProyectoId(this.newTarea.proyecto.id).subscribe(data => {
      this.newTarea.proyecto.equipo = new Equipo({ "usuarios": [] });
      data.forEach(x => { if (x.id != this.usuarioId) { this.newTarea.proyecto.equipo.usuarios.push(x) } });
    });
  }


  onFileSelected(event: any) {
    let size: number = 0;
    let aux = (file: any) => {
      if (file instanceof File) { return file.size; } return 0;
    };
    Array.from(event.target.files).forEach(file => size += aux(file));
    if (size > 1048576) {
      this._snackBar.open("Archivos muy pesados", "Cerrar");
      return
    }
    this.files = event.target.files;    
  }


  deleteBusqueda() {
    this.showChip = false;
    this.cadenaBuscar = "";
    this.tareaPage = this.copyTareaPage;
  }

  buscar() {
    this.tareaService.getByNombreLike(this.cadenaBuscar).subscribe(data => { this.copyTareaPage = this.tareaPage; this.tareaPage = new MatTableDataSource<Tarea>(data) });
    this.showChip = true;
  }

  selected(event:MatAutocompleteSelectedEvent) {
    const exist = this.newTarea.etiquetados.indexOf(event.option.value);
    if (exist == -1) {
      this.newTarea.etiquetados.push(event.option.value);
    }
  }

  guardar() {
    const e = new Tarea(this.newTarea);
    if (this.files.length > 0) {
      const fnames: string[] = [];
      Array.from(this.files).forEach(file => fnames.push(file.name));
      if (e.files != "") {
        e.files += "," + fnames.join();
      } else {
        e.files += fnames.join();
      }
      const formData = new FormData();
      Array.from(this.files).forEach(file => formData.append("files", file));
      this.fileService.save(formData).subscribe(data => console.log(data));
    }    
    this.tareaService.save(e).subscribe(data => this.tareaPage.data.push(data));
  }

  optionText(op: Equipo) {
    if (op == null) { return ""; }
    return op.nombre;

  }
}
