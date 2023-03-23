import { Tarea } from "./Tarea";

export class Column {
  constructor(public name: string, public id: number, public tasks: Tarea[]) { }
}
