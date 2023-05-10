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
import { globalEnum } from "../globalEnum";
import { MatSnackBar } from "@angular/material/snack-bar";
import * as CryptoJS from 'crypto-js';

@Component({
  selector: 'equipo-admin',
  templateUrl: '../html/GrupoAdmin.component.html',
  styleUrls: ['../css/GrupoAdmin.component.css'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({ height: '0px', minHeight: '0' })),
      state('expanded', style({ height: '*' })),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ]
})
export class GrupoAdminComponent implements OnInit{
  title = "saol"    
  usuarioNombreSelectedList: Array<string> = [];  
  grupoPage!: MatTableDataSource<Grupo>;
  separatorKeysCodes: number[] = [ENTER, COMMA];
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  expandedElement: Grupo | null | undefined;
  newGrupo: any;
  displayedColumns: string[] = ['id', 'nombre', 'fechaCreacion', 'codigo','equipos'];
  cadenaBuscar: string = "";
  showChip: boolean = false;
  copyGrupoPage!: MatTableDataSource<Grupo>;
  profesorId: number;

  constructor(private snackBar: MatSnackBar, private usuarioService: UsuarioService, private grupoService: GrupoService) {
    this.grupoPage = new MatTableDataSource<Grupo>([new Grupo({ "nombre": "crear" })]);
    this.newGrupo = {};
    this.profesorId = Number(JSON.parse(CryptoJS.enc.Utf8.stringify(CryptoJS.AES.decrypt(localStorage.getItem(globalEnum.usuarioLocalStorage), globalEnum.secret))).id);
  }
  ngOnInit(): void {
    this.grupoService.getByProfesorId(this.profesorId).subscribe(data => this.iniciarPaginacion(data));
  }

  iniciarPaginacion(data: Grupo[]): void {
    data.forEach(x => {
      x.encryptedId = CryptoJS.AES.encrypt(x.id + "", globalEnum.secret).toString().replaceAll("/","*");
    });
    this.grupoPage.data.push(...data);
    this.grupoPage.paginator = this.paginator;
  }

  expandir(element: any): void {
    this.newGrupo = element;
    this.expandedElement = this.expandedElement === element ? null : element; 
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

  guardar() {
    this.newGrupo.profesor = {"id": this.profesorId }
    this.grupoService.save(this.newGrupo).subscribe(data => {
      if (this.newGrupo.id == undefined) {
        this.grupoPage.data.push(data);
        this.grupoPage.paginator = this.paginator;
      }      
      this.snackBar.open("Grupo guardado","Ok");
    });

  }

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
