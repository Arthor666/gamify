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
import { UsuarioRecompensa } from '../models/UsuarioRecompensa';
import { UsuarioRecompensaService } from '../service/UsuarioRecompensa.service';



@Component({
  selector: 'recompensa-admin',
  templateUrl: '../html/UsuarioRecompensa.component.html',
  styleUrls: ['../css/UsuarioRecompensa.component.css'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({ height: '0px', minHeight: '0' })),
      state('expanded', style({ height: '*' })),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ]
})
export class UsuarioRecompensaComponent implements OnInit {    
  separatorKeysCodes: number[] = [ENTER, COMMA];
  displayedColumns: string[] = ['foto','nombre', 'puntos', 'fechaAlcance','status'];
  usuarioRecompensaPage!: MatTableDataSource<UsuarioRecompensa>;
  copyUsuarioRecompensaPage!: MatTableDataSource<UsuarioRecompensa>;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  expandedElement: UsuarioRecompensa | null | undefined;
  files: Array<File>;
  cadenaBuscar: string = "";
  showChip: boolean = false;
  usuarioOptionList: Array<Usuario> = [];
  statusList: Array<Status> = [];
  blob!: Blob;
  usuarioRecompensaSelected: UsuarioRecompensa;

  constructor(private usuarioRecompensaService: UsuarioRecompensaService, private statusService: StatusService, private fileService: FileService, private _snackBar: MatSnackBar) {    
    this.files = [];
    //this.usuarioRecompensaPage = new MatTableDataSource<Tarea>([new Tarea({ "nombre": "Crear recompensa" })]);
    this.usuarioRecompensaSelected = new UsuarioRecompensa({});
    this.usuarioRecompensaPage = new MatTableDataSource<UsuarioRecompensa>([]);
  }


  ngOnInit() {
    this.usuarioRecompensaService.getAllByUsuarioId(56).subscribe(data => this.iniciarPaginacion(data));

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


  


  iniciarPaginacion(data: UsuarioRecompensa[]): void {
    this.usuarioRecompensaPage.data.push(...data);
    this.usuarioRecompensaPage.paginator = this.paginator;
  }

  expandir(element: any): void {
    this.usuarioRecompensaSelected = element;
    this.expandedElement = this.expandedElement === element ? null : element;
    if (this.statusList.length <= 0) {
      this.statusService.getByClase("recompensa").subscribe(data => this.statusList = data);
    }
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
    this.usuarioRecompensaPage = this.copyUsuarioRecompensaPage;
  }

  buscar() {
    this.usuarioRecompensaService.getByNombreLike(this.cadenaBuscar).subscribe(data => { this.copyUsuarioRecompensaPage = this.usuarioRecompensaPage; this.usuarioRecompensaPage = new MatTableDataSource<UsuarioRecompensa>(data) });
    this.showChip = true;
  }

 

  guardar() {
    const e = new UsuarioRecompensa(this.usuarioRecompensaSelected);
    this.usuarioRecompensaService.save(e).subscribe(data => this.usuarioRecompensaPage.data.push(data));
  }

  optionText(op: Equipo) {
    if (op == null) { return ""; }
    return op.nombre;

  }
}
