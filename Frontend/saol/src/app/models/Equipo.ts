import { Timestamp } from "rxjs";
import { Proyecto } from "./Proyecto";
import { Usuario } from "./Usuario";

export class Equipo {
  id: number;
  fechaCreacion: Date;
  isActive: boolean;  
  usuarios: Array<Usuario>;
  nombre: string;
  proyectos: Array<Proyecto>;
  numeroProyectos!: number;

  constructor(equipo: any) {
    this.id = equipo.id;
    this.fechaCreacion = equipo.fechaCreacion;
    this.isActive = equipo.isActive;
    this.usuarios = equipo.usuarios;
    this.nombre = equipo.nombre;
    this.proyectos = equipo.proyectos;
  }
}
