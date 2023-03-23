import { Status } from "./Status";

export class Clases {
  id: number;
  nombre: string;
  statuses: Array<Status>;

  constructor(clase:any) {
    this.id = clase.id;
    this.nombre = clase.nombre;
    this.statuses = clase.statuses;
  }
}
