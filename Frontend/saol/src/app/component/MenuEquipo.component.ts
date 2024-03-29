import { Component, OnInit } from "@angular/core";
import { MatDialog } from "@angular/material/dialog";
import { MatSnackBar } from "@angular/material/snack-bar";
import { ActivatedRoute } from "@angular/router";
import { globalEnum } from "../globalEnum";
import { Equipo } from "../models/Equipo";
import { Usuario } from "../models/Usuario";
import { EquipoService } from "../service/Equipo.service";
import { DialogDiagramaQuemadoComponent } from "./DialogDiagramaQuemado.component";
import { DialogFlujoAcumuladoComponent } from "./DialogFlujoAcumulado.component";
import { DialogInscribirGrupoComponent } from "./DialogInscribirGrupo.component";
import { DialogProyectoComponent } from "./DialogProyecto.component";
import * as CryptoJS from 'crypto-js';

@Component({
  templateUrl:"../html/MenuEquipo.component.html",
  styleUrls: ["../css/MenuEquipo.component.css"],
})
export class MenuEquipoComponent implements OnInit {
  listEquipos: Array<Equipo>;
  cUser: Usuario;
  idEquipo: number;
  equipo: Equipo;
  idEquipoCiphred: string;
  token: string
  constructor(private route: ActivatedRoute, private dialog: MatDialog, private snackBar: MatSnackBar, private equipoService: EquipoService) {
    this.cUser = JSON.parse(CryptoJS.enc.Utf8.stringify(CryptoJS.AES.decrypt(localStorage.getItem(globalEnum.usuarioLocalStorage), globalEnum.secret))) as Usuario;
    this.idEquipoCiphred = this.route.snapshot.paramMap.get("idEquipo");
    this.idEquipo = Number(CryptoJS.enc.Utf8.stringify(CryptoJS.AES.decrypt(this.idEquipoCiphred.replaceAll("*", "/"), globalEnum.secret)));
    this.token = JSON.parse(CryptoJS.enc.Utf8.stringify(CryptoJS.AES.decrypt(localStorage.getItem(globalEnum.usuarioLocalStorage), globalEnum.secret))).token;
  }
  ngOnInit(): void {
    this.equipoService.getByUsuarioId(this.cUser.id, this.token).subscribe(data => this.listEquipos = data);
    this.equipoService.getById(this.idEquipo).subscribe(data => this.equipo = data);
  }

  openDialogQuemado() {
    const dialogRef = this.dialog.open(DialogDiagramaQuemadoComponent, {
      data: this.equipo
    });

    dialogRef.afterClosed().subscribe(result => {
    });
  }

  openDialogFlujo() {
    const dialogRef = this.dialog.open(DialogFlujoAcumuladoComponent, {
      data: this.equipo       
    });

    dialogRef.afterClosed().subscribe(result => {
    });
  }
  

  openDialogProyecto() {
    const dialogRef = this.dialog.open(DialogProyectoComponent, {
      data: {
        id: this.idEquipo
        },
    });

    dialogRef.afterClosed().subscribe(result => {      
    });
  }
}
