import { Component, OnInit, ViewChild } from "@angular/core";
import { animate, state, style, transition, trigger } from '@angular/animations';
import { Grupo } from "../models/Grupo";
import { MatTableDataSource } from "@angular/material/table";
import { MatPaginator } from "@angular/material/paginator";
import { GrupoService } from "../service/Grupo.service";
import { UsuarioService } from "../service/Usuario.service";
import { FormGroup } from "@angular/forms";
import { COMMA, ENTER } from '@angular/cdk/keycodes';
import { MatAutocompleteSelectedEvent } from "@angular/material/autocomplete";
import { MatDialog } from "@angular/material/dialog";
import { DialogInscribirGrupoComponent } from "./DialogInscribirGrupo.component";
import { globalEnum } from "../globalEnum";
import * as CryptoJS from 'crypto-js';

@Component({
  selector: 'equipo-admin',
  templateUrl: '../html/Grupo.component.html',
  styleUrls: ['../css/Grupo.component.css'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({ height: '0px', minHeight: '0' })),
      state('expanded', style({ height: '*' })),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ]
})
export class GrupoComponent implements OnInit {
  title = "saol"
  usuarioNombreSelectedList: Array<string> = [];
  grupoPage!: MatTableDataSource<Grupo>;
  separatorKeysCodes: number[] = [ENTER, COMMA];
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  expandedElement: Grupo | null | undefined;
  newGrupo: any;
  displayedColumns: string[] = ['id', 'nombre', 'fechaCreacion', 'profesor', 'equipo'];
  cadenaBuscar: string = "";
  showChip: boolean = false;
  copyGrupoPage!: MatTableDataSource<Grupo>;
  alumnoId: number;

  constructor(private usuarioService: UsuarioService, private grupoService: GrupoService, private dialog: MatDialog) {
    this.grupoPage = new MatTableDataSource<Grupo>();
    this.newGrupo = {};
    this.alumnoId = Number(JSON.parse(CryptoJS.enc.Utf8.stringify(CryptoJS.AES.decrypt(localStorage.getItem(globalEnum.usuarioLocalStorage), globalEnum.secret))).id);
  }
  ngOnInit(): void {
    this.grupoService.getByAlumnoId(this.alumnoId).subscribe(data => this.iniciarPaginacion(data));
  }

  iniciarPaginacion(data: Grupo[]): void {
    this.grupoPage.data.push(...data);
    this.grupoPage.paginator = this.paginator;
  }

  openInscribirDialog() {
    const dialogRef = this.dialog.open(DialogInscribirGrupoComponent, {
      data: {  },
    });

    dialogRef.afterClosed().subscribe(result => {
      {
        if (result != undefined) {
          this.grupoPage.data.push(result);
          this.grupoPage.paginator = this.paginator;
        }        
      }
    });

  }
 

  /*getUsuariosOptions(event: any) {
    const name = event.target.value;
    this.usuarioService.getUserByNameAndAvailable(name).subscribe(data => this.usuarioOptionList = data);
  }

  remove(usuario: Usuario) {
    const index = this.newGrupo.usuarios.findIndex((x: Usuario) => x.id == usuario.id);
    if (index != -1) {
      this.newGrupo.usuarios.splice(index, 1);
    }
  }*/  

  selected(event: MatAutocompleteSelectedEvent) {
    const exist = this.newGrupo.usuarios.indexOf(event.option.value);
    if (exist == -1) {
      this.newGrupo.usuarios.push(event.option.value);
    }
  }

  buscar() {
    this.grupoService.getByNombreLike(this.cadenaBuscar).subscribe(data => { this.copyGrupoPage = this.grupoPage; this.grupoPage = new MatTableDataSource<Grupo>(data) });
    this.showChip = true;
    this.cadenaBuscar = "";
  }
  deleteBusqueda() {
    this.grupoPage = this.copyGrupoPage;
    this.showChip = false;
  }



}
