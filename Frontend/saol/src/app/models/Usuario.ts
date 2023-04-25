import { Equipo } from "./Equipo";
import { Notificacion } from "./Notificacion";
import { Recompensa } from "./Recompensa";
import { Rol } from "./Rol";
import { Tarea } from "./Tarea";
import { UsuarioRecompensa } from "./UsuarioRecompensa";

export class Usuario {
  id: number;
  correo: string;
  isActive: boolean;
  nombre: string;
  nombreUsuario: string;
  password: string;
  equipos: Array<Equipo>;
  tareas: Array<Tarea>;
  etiquetadas: Array<Tarea>;
  puntaje: number;  
  rol: Rol | undefined;
  usuarioRecompensas: Array<UsuarioRecompensa>;
  notificaciones: Array<Notificacion>;
  token: string

  constructor(usuario: any) {
    this.id = usuario.id;
    this.correo = usuario.correo;
    this.isActive = usuario.isActive;
    this.nombre = usuario.nombre;
    this.nombreUsuario = usuario.nombreUsuario;
    this.password = usuario.password;
    this.equipos = usuario.equipos;
    this.tareas = usuario.tareas;
    this.etiquetadas = usuario.etiquetadas;
    this.puntaje = usuario.puntaje;
    this.rol = usuario.rol;
    this.usuarioRecompensas = usuario.usuarioRecompensas;
    this.notificaciones = usuario.notificaciones;
    this.token = usuario.token;
  }
}
