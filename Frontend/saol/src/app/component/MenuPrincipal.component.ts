import { Component, OnInit } from "@angular/core";
import { MatDialog } from "@angular/material/dialog";
import { MatSnackBar } from "@angular/material/snack-bar";
import { globalEnum } from "../globalEnum";
import { Equipo } from "../models/Equipo";
import { Notificacion } from "../models/Notificacion";
import { Usuario } from "../models/Usuario";
import { EquipoService } from "../service/Equipo.service";
import { NotificacionService } from "../service/Notificacion.service";
import { DialogInscribirGrupoComponent } from "./DialogInscribirGrupo.component";
import * as CryptoJS from 'crypto-js';

@Component({
  templateUrl:"../html/MenuPrincipal.component.html",
  styleUrls: ["../css/MenuPrincipal.component.css"],
})
export class MenuPrincipalComponent implements OnInit {
  listEquipos: Array<Equipo>;
  cUser: Usuario;
  notificacionList: Array<Notificacion>;
  token: string;
  constructor(private notificacionService: NotificacionService, private dialog: MatDialog, private snackBar: MatSnackBar, private equipoService: EquipoService) {    
    this.cUser = JSON.parse(CryptoJS.enc.Utf8.stringify(CryptoJS.AES.decrypt(localStorage.getItem(globalEnum.usuarioLocalStorage), globalEnum.secret))) as Usuario;
    this.token = JSON.parse(CryptoJS.enc.Utf8.stringify(CryptoJS.AES.decrypt(localStorage.getItem(globalEnum.usuarioLocalStorage), globalEnum.secret))).token;
  }
  ngOnInit(): void {
    this.equipoService.getByUsuarioId(this.cUser.id, this.token).subscribe(data => {
      data.forEach(x => {
        x.encryptedId = CryptoJS.AES.encrypt(x.id + "", globalEnum.secret).toString().replace("/", "*");
      });
      this.listEquipos = data;
    });
    this.notificacionService.getByUsuarioId(this.cUser.id).subscribe(data => this.notificacionList = data);
  }

  openInscribirDialog() {
    const dialogRef = this.dialog.open(DialogInscribirGrupoComponent, {
      data: {},
    });

    dialogRef.afterClosed().subscribe(result => {
      {
        if (result != undefined) {
          this.snackBar.open("Te has inscrito exitosamente");
        }
      }
    });

  }

} 
