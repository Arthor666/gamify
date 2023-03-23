import { Equipo } from "./Equipo";
import { FlujoAcumulado } from "./FlujoAcumulado";
import { HistoriaUsuario } from "./HistoriaUsuario";
import { Quemado } from "./Quemado";
import { Tarea } from "./Tarea";

export class Proyecto {
  id: number;
  descripcion: string;  
  fechaCreacion: Date;
  fechaEntrega: Date;
  files: string;
  nombre: string;
  porcentajePenalizacion: number;
  equipo: Equipo;
  puntosRecompensa: number;
  flujoAcumulados: Array<FlujoAcumulado>;
  historiasUsuarios: Array<HistoriaUsuario>;
  quemados: Array<Quemado>;
  tareas: Array<Tarea>;
  isActive: boolean;
  
  constructor(proyecto: any) {    
    this.id = proyecto.id;
    this.descripcion = proyecto.descripcion;
    this.fechaCreacion = proyecto.fechaCreacion;
    this.fechaEntrega = proyecto.fechaEntrega;
    this.files = proyecto.files;
    this.nombre = proyecto.nombre;
    this.porcentajePenalizacion = proyecto.porcentajePenalizacion;
    this.equipo = proyecto.equipo;
    this.puntosRecompensa = proyecto.puntosRecompensa;
    this.flujoAcumulados = proyecto.flujoAcumulados;
    this.historiasUsuarios = proyecto.historiasUsuarios;
    this.quemados = proyecto.quemados;
    this.tareas = proyecto.tareas;
    this.isActive = proyecto.isActive;
  }  

}
