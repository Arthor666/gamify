import { Component, OnInit } from "@angular/core";
import { globalEnum } from "../globalEnum";
import { AuthenticationRequestObject } from "../models/AuthenticationRequestObject";
import { Rol } from "../models/Rol";
import { Usuario } from "../models/Usuario";
import { AuthenticationService } from "../service/Authentication.service";
import { RolService } from "../service/Rol.service";
import * as CryptoJS from 'crypto-js';
import { UsuarioService } from "../service/Usuario.service";
import {Router} from "@angular/router";
import { MatSnackBar } from "@angular/material/snack-bar";
import { FormControl, FormGroup, FormRecord, Validators } from "@angular/forms";
@Component({
  selector: 'my-app',
  templateUrl: '../html/Login.component.html',
  styleUrls: ['../css/Login.component.css']
})
export class LoginComponent implements OnInit {
  authObject: AuthenticationRequestObject;
  userFromGroup : FormGroup;
  newUsuario: Usuario;
  sNombre: string = "";
  sPApellido: string = "";
  sSApellido: string = "";
  rolList: Array<Rol>;

  constructor(private snackBar: MatSnackBar,private router: Router,private usuarioService: UsuarioService, private authService: AuthenticationService, private rolService: RolService) {
    this.authObject = new AuthenticationRequestObject({});
    this.newUsuario = new Usuario({});
  }
  ngOnInit() {
    this.rolService.getAll().subscribe(data => this.rolList = data);
    this.userFromGroup = new FormGroup({
      nombre: new FormControl(this.sNombre,[Validators.required,Validators.minLength(3)]),
      nombreUsuario: new FormControl(this.newUsuario.nombreUsuario,[Validators.required,Validators.minLength(3)]),
      pApellido: new FormControl(this.sPApellido,[Validators.required,Validators.minLength(3)]),
      password: new FormControl(this.newUsuario.password,[Validators.required,Validators.minLength(8)]),
      sApellido: new FormControl(this.sSApellido,[Validators.required,Validators.minLength(3)]),
      correo: new FormControl(this.newUsuario.correo,[Validators.required,Validators.email]),
      rol: new FormControl(this.newUsuario.rol,[Validators.required])
    });
  }

  get nombre(){return this.userFromGroup.get("nombre");}
  get nombreUsuario(){return this.userFromGroup.get("nombreUsuario");}
  get password(){return this.userFromGroup.get("password");}
  get pApellido(){return this.userFromGroup.get("pApellido");}
  get sApellido (){return this.userFromGroup.get("sApellido");}
  get correo(){return this.userFromGroup.get("correo");}
  get rol(){return this.userFromGroup.get("rol");}

  registrar() {    
    this.newUsuario.nombre = this.userFromGroup.value.nombre + " " + this.userFromGroup.value.pApellido + " " + this.userFromGroup.value.sApellido;
    this.newUsuario.isActive = true;
    this.newUsuario.rol = this.userFromGroup.value.rol;
    this.newUsuario.password = this.userFromGroup.value.password;
    this.newUsuario.correo = this.userFromGroup.value.correo;
    this.usuarioService.save(this.newUsuario).subscribe(data => {
      this.snackBar.open("Usuario registrado","ok");      
    });
  }
}
