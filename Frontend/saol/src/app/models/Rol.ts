import { Usuario } from "./Usuario";

export class Rol {
  id: number;
  descripcion: string;
  nombre: string;
  usuarios: Array<Usuario>;

  constructor(rol: any) {
    this.id = rol.id;
    this.descripcion = rol.descripcion;
    this.nombre = rol.nombre;
    this.usuarios = rol.usuarios;
  }
}
