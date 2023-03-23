import { Proyecto } from "./Proyecto";

export class Quemado {
  id: number;
  fechaGuardado: Date;
  puntosQuemado: number;
  proyecto: Proyecto;

  constructor(quemado: any) {
    this.id = quemado.id;
    this.fechaGuardado = quemado.fechaGuardado;
    this.puntosQuemado = quemado.puntosQuemado;
    this.proyecto = quemado.proyecto;
  }

}
