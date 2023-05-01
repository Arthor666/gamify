import { Component, Inject, OnInit } from "@angular/core";
import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material/dialog";
import { globalEnum } from "../globalEnum";
import { Grupo } from "../models/Grupo";
import { GrupoService } from "../service/Grupo.service";
import * as CryptoJS from 'crypto-js';
import { MatSnackBar } from "@angular/material/snack-bar";

@Component({
  templateUrl: '../html/DialogInscribirGrupo.component.html',
  styleUrls: ['../css/DialogInscribirGrupo.component.css']
})
export class DialogInscribirGrupoComponent implements OnInit {
  codigoAcceso: string = "";
  alumnoId: number;  

  ngOnInit() {

  }

  constructor(private snackBar: MatSnackBar, public dialogRef: MatDialogRef<DialogInscribirGrupoComponent>, @Inject(MAT_DIALOG_DATA) public data: any, private grupoService: GrupoService) {
    this.alumnoId = Number(JSON.parse(CryptoJS.enc.Utf8.stringify(CryptoJS.AES.decrypt(localStorage.getItem(globalEnum.usuarioLocalStorage), globalEnum.secret))).id);
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  inscribir() {
    this.grupoService.inscribirAlumno(this.alumnoId, this.codigoAcceso).subscribe(data => this.snackBar.open("Alumno inscrito"));
  }
}
