import { Clases } from "./Clases";
import { Equipo } from "./Equipo";
import { Tarea } from "./Tarea";
import { UsuarioRecompensa } from "./UsuarioRecompensa";

export class Status {
  id: number;  
  nombre: string;
  tareas: Array<Tarea>;
  usuarioRecompensas: Array<UsuarioRecompensa>;
  clases: Array<Clases>;
  equipo: Equipo;

  constructor(status: any) {
    this.id = status.id;
    this.clases = status.clases;
    this.nombre = status.nombre;    
    this.tareas = status.tareas;
    this.usuarioRecompensas = status.usuarioRecompensas;
    this.equipo = status.equipo;
  }

}
