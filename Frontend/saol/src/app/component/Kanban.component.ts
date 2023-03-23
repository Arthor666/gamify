import { Component, VERSION, OnInit } from '@angular/core';
import { CdkDragDrop, moveItemInArray, transferArrayItem, CdkDropList } from '@angular/cdk/drag-drop';
import { StatusService } from '../service/Status.service';
import { Tarea } from '../models/Tarea';
import { TareaService } from '../service/Tarea.service';
import { ProyectoService } from '../service/Proyecto.service';
import { Proyecto } from '../models/Proyecto';
import { Status } from '../models/Status';
import { Usuario } from '../models/Usuario';
import { UsuarioService } from '../service/Usuario.service';
import { MatDialog } from '@angular/material/dialog';
import { CreateKanbanColumnDialog } from './CreateKanbanColumnDialog.component';
import { ClasesService } from '../service/Clases.service';
import { Clases } from '../models/Clases';



@Component({
  selector: 'my-app',
  templateUrl: '../html/Kanban.component.html',
  styleUrls: ['../css/Kanban.component.css']
})


export class KanbanComponent implements OnInit {
  
  proyecto: Proyecto = new Proyecto({ "nombre": 'Cargando...' });
  arrayStatus: Status[] = [];
  arrayStatusId: string[] = ['18', '19', '20'];
  cUsuario!:Usuario;

  constructor(private statusService: StatusService, private tareaService: TareaService, private proyectoService: ProyectoService, private usuarioService: UsuarioService, private dialog: MatDialog, private clasesService: ClasesService) { }

  public ngOnInit(): void {
    this.usuarioService.getById(56).subscribe(usuario => {
      this.cUsuario = usuario;
      this.proyectoService.getById(5).subscribe(proyecto => {
        this.proyecto = proyecto;        
        this.statusService.getByClase("tarea").subscribe(data => {
          this.arrayStatus = data;
          this.statusService.getByClase(this.proyecto.nombre).subscribe(data2 => {
            this.arrayStatus.push(...data2);
            this.getTareas();
          });
        });
      });         
    });    
  }
  
  openCreateDialog() {
    const dialogRef = this.dialog.open(CreateKanbanColumnDialog, { data: "" });
    dialogRef.afterClosed().subscribe(result => {
      this.statusService.save(new Status({ "nombre": result })).subscribe(data => {
        this.arrayStatus.push(data);
        const c = new Clases({ "nombre": this.proyecto.nombre, "statuses": [data] });
        this.clasesService.save(c).subscribe(data => console.log(data));
      });
    });
  }

  getStatusListId():string[] {
    return this.arrayStatus.map(s => s.id.toString());
  }

  getTareas() {
    var aux:Array<Tarea> = [];
    this.tareaService.getTareasByUsuarioId(this.cUsuario.id).subscribe(tareByUser =>
    {
      tareByUser.forEach(x => {
        x.editable = true;        
      });
      this.mergeStatusTarea(tareByUser);
      this.tareaService.getByProyectoId(this.proyecto.id).subscribe(tareaByProyecto =>
        this.mergeStatusTarea(tareaByProyecto)
      );          
    });    
  }

  mergeStatusTarea(data: Array<Tarea>) {
    data.forEach(x => this.arrayStatus.forEach(y => {
      if (y.tareas == undefined) {
        y.tareas = []
      }
      if (y.id == x.status.id && y.tareas.findIndex(z => z.id == x.id) == -1) {
        y.tareas.push(x)
      }
    }));
  }

  public dropGrid(event: CdkDragDrop<string[]>): void {
    moveItemInArray(this.arrayStatus, event.previousIndex, event.currentIndex);
  }

  public drop(event: CdkDragDrop<Tarea[]>): void {
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else {
      event.previousContainer.data[event.previousIndex].status = this.arrayStatus.filter(f => f.id == Number(event.container.id))[0];      
      this.tareaService.updateStatus(event.previousContainer.data[event.previousIndex]).subscribe(data => {  transferArrayItem(event.previousContainer.data, event.container.data, event.previousIndex, event.currentIndex); });      
    }
  }
}
