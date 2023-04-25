import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { waitForAsync } from '@angular/core/testing';
import { FormControl, FormGroup } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute } from '@angular/router';
import { Equipo } from '../models/Equipo';
import { Page } from '../models/Page';
import { Proyecto } from '../models/Proyecto';
import { EquipoService } from '../service/Equipo.service';
import { ProyectoService } from '../service/Proyecto.service';
import { animate, state, style, transition, trigger } from '@angular/animations';
import { FileService } from '../service/File.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog } from '@angular/material/dialog';
import { DialogFlujoAcumuladoComponent } from './DialogFlujoAcumulado.component';


@Component({
  selector: 'equipo-admin',
  templateUrl: '../html/Proyecto.component.html',
  styleUrls: ['../css/Proyecto.component.css'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({ height: '0px', minHeight: '0' })),
      state('expanded', style({ height: '*' })),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ]
})
export class ProyectoComponent implements OnInit {
  minDate: Date;
  newProyecto: any;
  equipoOptionList: Array<Equipo> = [];
  displayedColumns: string[] = ['id', 'nombre', 'fechaEntrega', 'descripcion', 'porcentajePenalizacion', 'flujoAcumulado','diagramaQuemado','tareas','kanban','activo'];
  proyectoPage!: MatTableDataSource<Proyecto>;
  copyProyectoPage!: MatTableDataSource<Proyecto>;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  expandedElement: Proyecto | null | undefined;
  files: Array<File>;
  cadenaBuscar: string = "";
  showChip: boolean = false;
  blob!: Blob;
  equipoId: number = 51;

  constructor(private equipoService: EquipoService, private route: ActivatedRoute, private proyectoService: ProyectoService, private fileService: FileService, private _snackBar: MatSnackBar, private dialog: MatDialog) {
    this.minDate = new Date();
    this.newProyecto = {};
    this.files = [];
    this.proyectoPage = new MatTableDataSource<Proyecto>();
  }


  ngOnInit() {
    this.proyectoService.getByEquipoId(this.equipoId).subscribe(data => this.iniciarPaginacion(data));

  }
  iniciarPaginacion(data: Proyecto): void {
    this.proyectoPage.data.push(data);
    this.proyectoPage.paginator = this.paginator;
  }

  openFile(file: string) {
    this.fileService.getFile(file).subscribe(data => {
      this.blob = new Blob([data], { type: 'application/pdf' });
      var downloadURL = window.URL.createObjectURL(data);
      var link = document.createElement('a');
      link.href = downloadURL;
      link.download = file;
      link.click();

    });
  }

  abrirFlujoAcumulado(proyectoSelected: Proyecto) {
    const dialogRef = this.dialog.open(DialogFlujoAcumuladoComponent, {
      data: { proyecto: proyectoSelected },
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  removeFile(file: string) {
    let aux = this.newProyecto.files.split(",")
    aux.splice(aux.indexOf(file), 1);
    this.newProyecto.files = aux.join();
  }

  expandir(element: any): void {
    this.newProyecto = element;
    this.expandedElement = this.expandedElement === element ? null : element;
    if (this.newProyecto.id != undefined) {
      this.equipoService.getByProyectoId(this.newProyecto.id).subscribe(data => this.newProyecto.equipo = data);
    }
  }

  getEquiposDisponibles(): void {
    if (this.equipoOptionList.length <= 0) {
      this.equipoService.getAll().subscribe(data => this.equipoOptionList = data);
    }
  }


  onFileSelected(event: any) {
    let size: number = 0;
    let aux = (file: any) => {
      if (file instanceof File) { return file.size; } return 0;
    };
    Array.from(event.target.files).forEach(file => size += aux(file));
    if (size > 1048576) {
      this._snackBar.open("Archivos muy pesados", "Cerrar");
      return
    }
    this.files = event.target.files;
  }


  deleteBusqueda() {
    this.showChip = false;
    this.cadenaBuscar = "";
    this.proyectoPage = this.copyProyectoPage;
  }

  buscar() {
    this.proyectoService.getByNombreLike(this.cadenaBuscar).subscribe(data => { this.copyProyectoPage = this.proyectoPage; this.proyectoPage = new MatTableDataSource<Proyecto>(data) });
    this.showChip = true;
  }

  guardar() {
    const e = new Proyecto(this.newProyecto);
    if (this.files.length > 0) {
      const fnames: string[] = [];
      Array.from(this.files).forEach(file => fnames.push(file.name));
      if (e.files != "") {
        e.files += "," + fnames.join();
      } else {
        e.files += fnames.join();
      }
      const formData = new FormData();
      Array.from(this.files).forEach(file => formData.append("files", file));
      this.fileService.save(formData).subscribe(data => console.log(data));
    }
    this.proyectoService.save(e).subscribe(data => this.proyectoPage.data.push(data));

  }

  optionText(op: Equipo) {
    if (op == null) { return ""; }
    return op.nombre;

  }
}
