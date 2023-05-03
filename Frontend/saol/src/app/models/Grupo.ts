import { Equipo } from "./Equipo";
import { Usuario } from "./Usuario";

export class Grupo {
  id: number;
  codigoAcceso:string;
  fechaCreacion: Date;
  nombre: string;
  equipos: Array<Equipo>;
  alumnos: Array<Usuario>;  
  profesor: Usuario;
  encryptedId: string;

  constructor(grupo: any) {
    this.id = grupo.id;
    this.codigoAcceso = grupo.codigoAcceso;
    this.fechaCreacion = grupo.fechaCreacion;
    this.nombre = grupo.nombre;
    this.equipos = grupo.equipos;
    this.alumnos = grupo.alumnos;
    this.profesor = grupo.profesor;
  }
}
