import { Equipo } from "./Equipo";
import { Proyecto } from "./Proyecto";
import { Status } from "./Status";

export class FlujoAcumulado {
  id: number;  
  fechaGuardado: Date;
  status: Status;
  numTareas: number;
  equipo: Equipo;

  constructor(flujoAcumulado: any) {
    this.id = flujoAcumulado.id;
    this.fechaGuardado = flujoAcumulado.fechaGuardado;
    this.status = flujoAcumulado.status;
    this.numTareas = flujoAcumulado.numTareas;
    this.equipo = flujoAcumulado.equipo;
  }

}
