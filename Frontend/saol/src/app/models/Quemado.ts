import { Equipo } from "./Equipo";
import { Proyecto } from "./Proyecto";

export class Quemado {
  id: number;
  fechaGuardado: Date;
  puntosQuemado: number;
  equipo: Equipo;

  constructor(quemado: any) {
    this.id = quemado.id;
    this.fechaGuardado = quemado.fechaGuardado;
    this.puntosQuemado = quemado.puntosQuemado;
    this.equipo = quemado.equipo;
  }

}
