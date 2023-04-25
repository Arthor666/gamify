import { Equipo } from "./Equipo";
import { Usuario } from "./Usuario";
import { UsuarioRecompensa } from "./UsuarioRecompensa";

export class Recompensa {
  id: number;
  descripcion:string;
  nombre: string;
  equipos: Array<Equipo>;
  isEditable: boolean;
  profesor: Usuario;

  constructor(recompensa:any) {
    this.id = recompensa.id;
    this.descripcion = recompensa.descripcion;
    this.nombre = recompensa.nombre;    
    this.equipos = recompensa.equipos;
    this.profesor = recompensa.profesor;
  }


}
