import { Component, OnInit } from "@angular/core";
import { MatSnackBar } from "@angular/material/snack-bar";
import { globalEnum } from "../globalEnum";
import { Rol } from "../models/Rol";
import { Usuario } from "../models/Usuario";
import { RolService } from "../service/Rol.service";
import { UsuarioService } from "../service/Usuario.service";

@Component({  
  templateUrl: '../html/ModifyUser.component.html',
  styleUrls: ['../css/ModifyUser.component.css']
})
export class ModifyUserComponent implements OnInit {

  cUser: Usuario;
  nombre: string;
  pApellido: string;
  sApellido: string;
  rolList: Array<Rol>;
  cPassword: string;
  nPassword: string;

  constructor(private snackBar: MatSnackBar, private rolService: RolService, private usuarioService: UsuarioService) {
    this.cUser = JSON.parse(localStorage.getItem(globalEnum.usuarioLocalStorage)) as Usuario;
    const splitNombre = this.cUser.nombre.split(" ");
    this.nombre = splitNombre[0];
    this.pApellido = splitNombre[1];
    this.sApellido = splitNombre[2];
  }

  ngOnInit() {
    this.rolService.getAll().subscribe(data => this.rolList = data);
    
  }


  guardar() {
    this.cUser.nombre = this.nombre + " " + this.pApellido + " " + this.sApellido;
    this.cUser.password = this.cPassword;
    this.usuarioService.update(this.cUser, this.nPassword, this.cPassword).subscribe(data => {
      this.cUser = data;
      localStorage.setItem(globalEnum.usuarioLocalStorage, JSON.stringify(this.cUser));
      this.snackBar.open("Usuario actualizado","Ok");
    });
  }
}
