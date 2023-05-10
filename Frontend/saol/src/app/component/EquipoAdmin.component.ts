import { Component, OnInit, ViewChild } from '@angular/core';
import { FormArray, FormControl, FormGroup } from '@angular/forms';
import { Usuario } from '../models/Usuario';
import { UsuarioService } from '../service/Usuario.service';
import { MatAutocompleteSelectedEvent } from '@angular/material/autocomplete';
import { MatChipInputEvent } from '@angular/material/chips';
import { COMMA, ENTER } from '@angular/cdk/keycodes';
import { animate, state, style, transition, trigger } from '@angular/animations';
import { Equipo } from '../models/Equipo';
import { EquipoService } from '../service/Equipo.service';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { globalEnum } from '../globalEnum';
import { ActivatedRoute } from '@angular/router';
import { Proyecto } from '../models/Proyecto';
import { Grupo } from '../models/Grupo';
import { ProyectoService } from '../service/Proyecto.service';
import { RecompensaService } from '../service/Recompensa.service';
import { Recompensa } from '../models/Recompensa';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog } from '@angular/material/dialog';
import { DialogFlujoAcumuladoComponent } from './DialogFlujoAcumulado.component';
import { DialogDiagramaQuemadoComponent } from './DialogDiagramaQuemado.component';
import * as CryptoJS from 'crypto-js';

@Component({
  selector: 'equipo-admin',
  templateUrl: '../html/EquipoAdmin.component.html',
  styleUrls: ['../css/EquipoAdmin.component.css'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({ height: '0px', minHeight: '0' })),
      state('expanded', style({ height: '*' })),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ]
})
export class EquipoAdminComponent implements OnInit {
  title = "saol"
  usuarioFormCtl: FormGroup = new FormGroup({ nombreUsuarioCtl:new  FormControl('') });
  usuarioOptionList: Array<Usuario> = [];
  usuarioNombreSelectedList: Array<string> = [];
  usuarioSelectedList: Array<Usuario> = [];
  equipoPage!: MatTableDataSource<Equipo>;
  separatorKeysCodes: number[] = [ENTER, COMMA];
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  expandedElement: Equipo | null | undefined;
  newEquipo: Equipo;
  displayedColumns: string[] = ['id', 'nombre', 'fechaCreacion', 'calificacion','flujoAcumulado','diagramaQuemado'];
  cadenaBuscar: string = "";
  showChip: boolean = false;
  copyEquipoPage!: MatTableDataSource<Equipo>;
  profesorId: number;
  grupoId: number;
  proyectos: Array<Proyecto>;
  recompensas: Array<Recompensa>

  constructor(private dialog: MatDialog, private snackBar: MatSnackBar, private recompensaService: RecompensaService, private proyectoService: ProyectoService, private route: ActivatedRoute, private usuarioService: UsuarioService, private equipoService: EquipoService) {
    this.equipoPage = new MatTableDataSource<Equipo>([new Equipo({ "nombre": "crear" })]);
    this.newEquipo = new Equipo({});
    this.profesorId = Number(JSON.parse(CryptoJS.enc.Utf8.stringify(CryptoJS.AES.decrypt(localStorage.getItem(globalEnum.usuarioLocalStorage), globalEnum.secret))).id);
    this.grupoId = Number(CryptoJS.enc.Utf8.stringify(CryptoJS.AES.decrypt(this.route.snapshot.paramMap.get("idGrupo").replaceAll("*","/"), globalEnum.secret)));
  }
  ngOnInit(): void {
    this.equipoService.getByGrupoId(this.grupoId).subscribe(data => this.iniciarPaginacion(data));
  }

  iniciarPaginacion(data: Equipo[]): void {
    this.equipoPage.data.push(...data);
    this.equipoPage.paginator = this.paginator;    
  }

  expandir(element: any): void {
    this.newEquipo = element;    
    this.expandedElement = this.expandedElement === element ? null : element;
    this.proyectoService.getByProfesorId(this.profesorId).subscribe(data => this.proyectos = data);    
    if (this.newEquipo.id != null) {
      this.usuarioService.getUserByEquipoId(this.newEquipo.id).subscribe(data => this.newEquipo.usuarios = data);
      this.recompensaService.getCommonsAndProfesorId(this.profesorId).subscribe(data => this.recompensas = data);
      this.recompensaService.getByEquipoId(this.newEquipo.id).subscribe(data => this.newEquipo.recompensa = data);
      this.proyectoService.getByEquipoId(this.newEquipo.id).subscribe(data => this.newEquipo.proyecto = data);
    }    
  }

  abrirFlujoAcumulado(element: Equipo) {
    const dialogRef = this.dialog.open(DialogFlujoAcumuladoComponent, {
      data: element
    });

    dialogRef.afterClosed().subscribe(result => {
    });
  }

  abrirDiagramaQuemado(element: Equipo) {
    const dialogRef = this.dialog.open(DialogDiagramaQuemadoComponent, {
      data: element
    });

    dialogRef.afterClosed().subscribe(result => {
    });

  }

  getUsuariosOptions(event:any) {
    const name = event.target.value;
    this.usuarioService.getUserByNameAndGrupoIdAndNotInEquipo(name, this.grupoId).subscribe(data => this.usuarioOptionList = data);    
  }
  remove(usuario: Usuario) {
    const index = this.newEquipo.usuarios.findIndex((x: Usuario) => x.id == usuario.id);    
    if (index != -1) {
      this.newEquipo.usuarios.splice(index, 1);      
    }    
  }


  selected(event: MatAutocompleteSelectedEvent) {
    if (this.newEquipo.usuarios == undefined) {
      this.newEquipo.usuarios = [];
    }
    const exist = this.newEquipo.usuarios.indexOf(event.option.value);
    if (exist == -1) {
      this.newEquipo.usuarios.push(event.option.value);
    }    
  }

  buscar() {
    this.equipoService.getByNombreLike(this.cadenaBuscar).subscribe(data => { this.copyEquipoPage = this.equipoPage; this.equipoPage = new MatTableDataSource<Equipo>(data) });
    this.showChip = true;
    this.cadenaBuscar = "";
  }
  deleteBusqueda() {
    this.equipoPage = this.copyEquipoPage;
    this.showChip = false;
  }

  compareFunction(e1: any, e2: any) {
      return e1 && e2 ? e1.id === e2.id : e1 === e2;
    
  }

  guardar(): void {
    this.newEquipo.grupo = new Grupo({ "id": this.grupoId });
    if (this.newEquipo.id == undefined) {      
      this.newEquipo.isActive = true;
      this.newEquipo.calificacion = -1;
    }       
    this.equipoService.saveEquipo(this.newEquipo).subscribe(data => {
      if (this.newEquipo.id == undefined) {
        this.equipoPage.data.push(data);
        this.equipoPage.paginator = this.paginator;        
      }
      this.snackBar.open("Equipo guardado", "Ok");
    });
    }
}
