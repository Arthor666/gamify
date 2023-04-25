import { Component, OnInit } from "@angular/core";
import { globalEnum } from "../globalEnum";
import { AuthenticationRequestObject } from "../models/AuthenticationRequestObject";
import { Rol } from "../models/Rol";
import { Usuario } from "../models/Usuario";
import { AuthenticationService } from "../service/Authentication.service";
import { RolService } from "../service/Rol.service";

import { UsuarioService } from "../service/Usuario.service";

@Component({
  selector: 'my-app',
  templateUrl: '../html/Login.component.html',
  styleUrls: ['../css/Login.component.css']
})
export class LoginComponent implements OnInit {
  authObject: AuthenticationRequestObject;
  newUsuario: Usuario;
  nombre: string = "";
  pApellido: string = "";
  sApellido: string = "";
  rolList: Array<Rol>;

  constructor(private usuarioService: UsuarioService, private authService: AuthenticationService, private rolService: RolService) {
    this.authObject = new AuthenticationRequestObject({});
    this.newUsuario = new Usuario({});

  }
  ngOnInit() {
    this.rolService.getAll().subscribe(data => this.rolList = data);

  }

  registrar() {
    this.newUsuario.nombre = this.nombre + " " + this.pApellido + " " + this.sApellido;
    this.newUsuario.isActive = true;
    this.usuarioService.save(this.newUsuario).subscribe(data => localStorage.setItem(globalEnum.usuarioLocalStorage,JSON.stringify(data)));
  }
}
