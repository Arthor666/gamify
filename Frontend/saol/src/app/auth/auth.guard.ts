import { globalEnum } from '../globalEnum';
import { Usuario } from '../models/Usuario';
import { AuthenticationService } from '../service/Authentication.service';
export const authGuardAlumnos = () => {
  if (!globalEnum.environment.production) {
    return true;
  }  
  var cUser = JSON.parse(localStorage.getItem(globalEnum.usuarioLocalStorage)) as Usuario;
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
  var cUser = JSON.parse(localStorage.getItem(globalEnum.usuarioLocalStorage)) as Usuario;
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
  var cUser = JSON.parse(localStorage.getItem(globalEnum.usuarioLocalStorage)) as Usuario;
  if (cUser != undefined && (cUser.token != undefined || cUser.token != "")) {    
      return true;    
  }
  return false;
};
