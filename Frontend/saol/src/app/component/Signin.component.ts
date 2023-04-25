import { HttpErrorResponse } from "@angular/common/http";
import { Component, OnInit } from "@angular/core";
import { MatSnackBar } from "@angular/material/snack-bar";
import { ActivatedRoute, Router, RouterLink } from "@angular/router";
import { catchError, throwError } from "rxjs";
import { globalEnum } from "../globalEnum";
import { AuthenticationRequestObject } from "../models/AuthenticationRequestObject";
import { AuthenticationService } from "../service/Authentication.service";

import { UsuarioService } from "../service/Usuario.service";

@Component({
  selector: 'my-app',
  templateUrl: '../html/Signin.component.html',
  styleUrls: ['../css/Signin.component.css']
})
export class SigninComponent implements OnInit {
  authObject: AuthenticationRequestObject;

  constructor(private usuarioService: UsuarioService, private authService: AuthenticationService, private router: Router) {
    this.authObject = new AuthenticationRequestObject({});
  }
  ngOnInit() {

  }

  
  ingresar() {
    this.authService.authenticate(this.authObject).subscribe(data => {
      localStorage.setItem(globalEnum.usuarioLocalStorage, JSON.stringify(data));      
      this.router.navigate(['menu'])
        .then(() => {
          window.location.reload();
        });     
    });
  }
}
