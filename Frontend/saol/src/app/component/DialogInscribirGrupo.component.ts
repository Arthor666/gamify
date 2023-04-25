import { Component, Inject, OnInit } from "@angular/core";
import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material/dialog";
import { globalEnum } from "../globalEnum";
import { Grupo } from "../models/Grupo";
import { GrupoService } from "../service/Grupo.service";

@Component({
  templateUrl: '../html/DialogInscribirGrupo.component.html',
  styleUrls: ['../css/DialogInscribirGrupo.component.css']
})
export class DialogInscribirGrupoComponent implements OnInit {
  codigoAcceso: string = "";
  alumnoId: number;  

  ngOnInit() {

  }

  constructor(public dialogRef: MatDialogRef<DialogInscribirGrupoComponent>, @Inject(MAT_DIALOG_DATA) public data: any, private grupoService: GrupoService) {
    this.alumnoId = Number(JSON.parse(localStorage.getItem(globalEnum.usuarioLocalStorage)).id);
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  inscribir() {
    this.grupoService.inscribirAlumno(this.alumnoId, this.codigoAcceso).subscribe(data => this.dialogRef.close(data));
  }
}
