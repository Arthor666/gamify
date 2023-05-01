import { HttpErrorResponse } from "@angular/common/http";
import { Component, OnInit } from "@angular/core";
import { MatSnackBar } from "@angular/material/snack-bar";
import { ActivatedRoute, Router, RouterLink } from "@angular/router";
import { catchError, throwError } from "rxjs";
import { globalEnum } from "../globalEnum";
import { AuthenticationRequestObject } from "../models/AuthenticationRequestObject";
import { AuthenticationService } from "../service/Authentication.service";
import { UsuarioService } from "../service/Usuario.service";
import * as CryptoJS from 'crypto-js';  
import { DialogRecuperarCuentaComponent } from "./DialogRecuperarCuenta.component";
import { MatDialog } from "@angular/material/dialog";

@Component({  
  templateUrl: '../html/Signin.component.html',
  styleUrls: ['../css/Signin.component.css']
})
export class SigninComponent implements OnInit {
  authObject: AuthenticationRequestObject;
  error: boolean = false;
  constructor(private snackBar: MatSnackBar, private dialog: MatDialog, private usuarioService: UsuarioService, private authService: AuthenticationService, private router: Router) {
    this.authObject = new AuthenticationRequestObject({});
  }
  ngOnInit() {

  }

  openDialogRecuperar() {
    const dialogRef = this.dialog.open(DialogRecuperarCuentaComponent, {
      data: { email: this.authObject.correo },
    });

    dialogRef.afterClosed().subscribe(result => {
      {
        if (result != undefined) {
          this.snackBar.open("Revisa tu correo para recuperar tu cuenta");
        }
      }
    });
  }

  private catchError() {
    this.error = true;
  }
  
  ingresar() {
    this.authService.authenticate(this.authObject).pipe(catchError(this.catchError.bind(this))).subscribe(data => {
      localStorage.setItem(globalEnum.usuarioLocalStorage, CryptoJS.AES.encrypt(JSON.stringify(data), globalEnum.secret));
      localStorage.setItem(globalEnum.token, data.token);
      this.router.navigate(['menu'])
        .then(() => {
          window.location.reload();
        });     
    });
  }
}
