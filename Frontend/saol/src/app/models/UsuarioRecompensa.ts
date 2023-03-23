import { Recompensa } from "./Recompensa";
import { Status } from "./Status";
import { Usuario } from "./Usuario";

export class UsuarioRecompensa {
  id: number;
  status: Status;
  recompensa: Recompensa;
  usuario: Usuario;
  fecha: Date;

  constructor(usuarioRecompensas: any) {
    this.id = usuarioRecompensas.id;
    this.status = usuarioRecompensas.status;
    this.recompensa = usuarioRecompensas.recompensa;
    this.usuario = usuarioRecompensas.usuario;
    this.fecha = usuarioRecompensas.fecha;
  }
}
