import { Recompensa } from "./Recompensa";
import { Tarea } from "./Tarea";
import { Usuario } from "./Usuario";

export class Notificacion {
  id: number;
  visto: boolean;
  usuario: Usuario;
  recompensa: Recompensa;
  tarea: Tarea;

  constructor(notificacion: any) {
    this.id = notificacion.id;
    this.visto = notificacion.visto;
    this.usuario = notificacion.usuario;
    this.recompensa = notificacion.recompensa;
    this.tarea = notificacion.tarea
  }
}
