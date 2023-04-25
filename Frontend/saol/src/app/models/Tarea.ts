import { Equipo } from "./Equipo";
import { HistoriaUsuario } from "./HistoriaUsuario";
import { Proyecto } from "./Proyecto";
import { Status } from "./Status";
import { Usuario } from "./Usuario";

export class Tarea {
  id: number;
  descripcion: string;
  fechaCreacion: Date;
  fechaTentativa: Date;
  files: string;
  autor: Usuario;
  etiquetados: Array<Usuario>;
  status: Status;
  equipo: Equipo;
  historiasUsuario: HistoriaUsuario;
  nombre: string;
  editable: boolean = false;

  constructor(tarea:any) {
    this.id = tarea.id;
    this.descripcion = tarea.descripcion;
    this.fechaCreacion = tarea.fechaCreacion;
    this.fechaTentativa = tarea.fechaTentativa;
    this.files = tarea.files;
    this.autor = tarea.autor;
    this.etiquetados = tarea.etiquetados;
    this.status = tarea.status;
    this.equipo = tarea.equipo;
    this.historiasUsuario = tarea.historiasUsuario;
    this.nombre = tarea.nombre;
  }

}
