import { Equipo } from "./Equipo";
import { FlujoAcumulado } from "./FlujoAcumulado";
import { HistoriaUsuario } from "./HistoriaUsuario";
import { Quemado } from "./Quemado";
import { Tarea } from "./Tarea";
import { Usuario } from "./Usuario";

export class Proyecto {
  id: number;
  descripcion: string;  
  fechaCreacion: Date;
  fechaEntrega: Date;
  files: string;
  nombre: string;
  porcentajePenalizacion: number;
  equipos: Array<Equipo>;    
  isActive: boolean;
  profesor: Usuario;
  
  constructor(proyecto: any) {
    this.id = proyecto.id;
    this.descripcion = proyecto.descripcion;
    this.fechaCreacion = proyecto.fechaCreacion;
    this.fechaEntrega = proyecto.fechaEntrega;
    this.files = proyecto.files;
    this.nombre = proyecto.nombre;
    this.porcentajePenalizacion = proyecto.porcentajePenalizacion;
    this.equipos = proyecto.equipos;  
    this.isActive = proyecto.isActive;
    this.profesor = proyecto.profesor;
  }  

}
