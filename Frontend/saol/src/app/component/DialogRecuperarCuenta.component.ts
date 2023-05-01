import { Component, Inject, OnInit } from "@angular/core";
import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material/dialog";
import { globalEnum } from "../globalEnum";
import { Grupo } from "../models/Grupo";
import { GrupoService } from "../service/Grupo.service";
import * as CryptoJS from 'crypto-js';
import { FormControl, Validators } from "@angular/forms";
import { CorreoRecuperacionService } from "../service/CorreoRecuperacion.service";
import { MatSnackBar } from "@angular/material/snack-bar";

@Component({
  templateUrl: '../html/DialogRecuperarCuenta.component.html',
  styleUrls: ['../css/DialogRecuperarCuenta.component.css']
})
export class DialogRecuperarCuentaComponent implements OnInit {
  email: FormControl;

  ngOnInit() {

  }

  constructor(private snackBar: MatSnackBar, private correoService: CorreoRecuperacionService, public dialogRef: MatDialogRef<DialogRecuperarCuentaComponent>, @Inject(MAT_DIALOG_DATA) public data: any) {
    this.email = new FormControl(data.email,[Validators.required, Validators.email]);
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  enviarCorreoRecuperacion() {
    this.correoService.sendMailToRecoverCount(this.email.value).subscribe(data => this.snackBar.open("Se ha enviado un correo a "+data.correo+" para que recuperes la cuenta, no  olvides revisar el spam","ok"));
  }
}
