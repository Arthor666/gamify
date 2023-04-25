import { Equipo } from "./Equipo";
import { Proyecto } from "./Proyecto";
import { Status } from "./Status";
import { Tarea } from "./Tarea";

export class HistoriaUsuario {
  id: number;
  descripcion: string;
  status: Status;
  nombre: string;
  puntosHistoria: number;
  equipo: Equipo;
  tareas: Array<Tarea>;

  constructor(historiaUsuario: any) {
    this.id = historiaUsuario.id;
    this.descripcion = historiaUsuario.descripcion;
    this.status = historiaUsuario.status;
    this.nombre = historiaUsuario.nombre;
    this.puntosHistoria = historiaUsuario.puntosHistoria;
    this.equipo = historiaUsuario.equipo;
    this.tareas = historiaUsuario.tareas;
  }

}
