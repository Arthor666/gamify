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

@Component({
  templateUrl:"../html/MenuPrincipal.component.html",
  styleUrls: ["../css/MenuPrincipal.component.css"],
})
export class MenuPrincipalComponent implements OnInit {
  listEquipos: Array<Equipo>;
  cUser: Usuario;
  notificacionList: Array<Notificacion>
  constructor(private notificacionService: NotificacionService,private dialog: MatDialog, private snackBar: MatSnackBar, private equipoService: EquipoService) {
    this.cUser = JSON.parse(localStorage.getItem(globalEnum.usuarioLocalStorage)) as Usuario;
  }
  ngOnInit(): void {
    this.equipoService.getByUsuarioId(this.cUser.id).subscribe(data => this.listEquipos = data);
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
