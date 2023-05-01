import { Timestamp } from "rxjs";
import { FlujoAcumulado } from "./FlujoAcumulado";
import { Grupo } from "./Grupo";
import { Proyecto } from "./Proyecto";
import { Quemado } from "./Quemado";
import { Recompensa } from "./Recompensa";
import { Tarea } from "./Tarea";
import { Usuario } from "./Usuario";

export class Equipo {
  id: number;
  fechaCreacion: Date;
  isActive: boolean;
  calificacion: number;
  usuarios: Array<Usuario>;
  nombre: string;
  proyecto: Proyecto;
  numeroProyectos!: number;
  grupo: Grupo;
  flujoAcumulados: Array<FlujoAcumulado>;
  historiasUsuarios: Array<FlujoAcumulado>;
  quemados: Array<Quemado>;
  tareas: Array<Tarea>;
  recompensa: Recompensa;
  encryptedId: string;


  constructor(equipo: any) {
    this.id = equipo.id;
    this.fechaCreacion = equipo.fechaCreacion;
    this.isActive = equipo.isActive;
    this.usuarios = equipo.usuarios;
    this.nombre = equipo.nombre;
    this.proyecto = equipo.proyecto;
    this.grupo = equipo.grupo;
    this.calificacion = equipo.calificacion;
    this.flujoAcumulados = equipo.flujoAcumulados;
    this.historiasUsuarios = equipo.historiasUsuarios;
    this.quemados = equipo.quemados;
    this.tareas = equipo.tareas;
    this.recompensa = equipo.recompensa;
  }
}
