import { UsuarioRecompensa } from "./UsuarioRecompensa";

export class Recompensa {
  id: number;
  descripcion:string;
  nombre: string;
  puntos: number;
  files: string;
  usuarioRecompensas: Array<UsuarioRecompensa>;

  constructor(recompensa:any) {
    this.id = recompensa.id;
    this.descripcion = recompensa.descripcion;
    this.nombre = recompensa.nombre;
    this.puntos = recompensa.puntos;
    this.files = recompensa.files;
    this.usuarioRecompensas = recompensa.usuarioRecompensas;
  }


}
