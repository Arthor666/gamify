import { Proyecto } from "./Proyecto";
import { Status } from "./Status";
import { Tarea } from "./Tarea";

export class HistoriaUsuario {
  id: number;
  descripcion: string;
  status: Status;
  nombre: string;
  puntosHistoria: number;
  proyecto: Proyecto;
  tareas: Array<Tarea>;

  constructor(historiaUsuario: any) {
    this.id = historiaUsuario.id;
    this.descripcion = historiaUsuario.descripcion;
    this.status = historiaUsuario.status;
    this.nombre = historiaUsuario.nombre;
    this.puntosHistoria = historiaUsuario.puntosHistoria;
    this.proyecto = historiaUsuario.proyecto;
    this.tareas = historiaUsuario.tareas;
  }

}
