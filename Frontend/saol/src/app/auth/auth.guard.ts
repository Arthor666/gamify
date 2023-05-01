import { globalEnum } from '../globalEnum';
import { Usuario } from '../models/Usuario';
import { AuthenticationService } from '../service/Authentication.service';
import * as CryptoJS from 'crypto-js';
import { Router } from '@angular/router';

export const authGuardAlumnos = (router: Router) => {
  if (!globalEnum.environment.production) {
    return true;
  }
  var cUser = JSON.parse(CryptoJS.enc.Utf8.stringify(CryptoJS.AES.decrypt(localStorage.getItem(globalEnum.usuarioLocalStorage), globalEnum.secret))) as Usuario;
  if (cUser != undefined && (cUser.token != undefined || cUser.token != "")) {
    if (cUser.rol.nombre == "Alumno") {
      return true;
    }    
  }  
  return false;
};
export const authGuardProfesores = () => {
  if (!globalEnum.environment.production) {
    return true;
  }
  var cUser = JSON.parse(CryptoJS.enc.Utf8.stringify(CryptoJS.AES.decrypt(localStorage.getItem(globalEnum.usuarioLocalStorage), globalEnum.secret))) as Usuario;
  if (cUser != undefined && (cUser.token != undefined || cUser.token != "")) {
    if (cUser.rol.nombre == "Profesor") {
      return true;
    }
  }
  return false;
};

export const authGuardCommons = () => {
  if (!globalEnum.environment.production) {
    return true;
  }
  var cUser = JSON.parse(CryptoJS.enc.Utf8.stringify(CryptoJS.AES.decrypt(localStorage.getItem(globalEnum.usuarioLocalStorage), globalEnum.secret))) as Usuario;
  if (cUser != undefined && (cUser.token != undefined || cUser.token != "")) {    
      return true;    
  }
  return false;
};

export const authGuardPublic = () => {
  if (!globalEnum.environment.production) {
    return true;
  }
  var cUser = localStorage.getItem(globalEnum.usuarioLocalStorage);
  if (cUser == undefined) {
    return true;
  }
  return false;
};
