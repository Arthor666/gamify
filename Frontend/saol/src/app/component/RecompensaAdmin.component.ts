import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { waitForAsync } from '@angular/core/testing';
import { FormControl, FormGroup } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute } from '@angular/router';
import { Equipo } from '../models/Equipo';
import { Page } from '../models/Page';
import { EquipoService } from '../service/Equipo.service';
import { ProyectoService } from '../service/Proyecto.service';
import { animate, state, style, transition, trigger } from '@angular/animations';
import { FileService } from '../service/File.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Recompensa } from '../models/Recompensa';
import { RecompensaService } from '../service/Recompensa.service';


@Component({
  selector: 'recompensa-admin',
  templateUrl: '../html/RecompensaAdmin.component.html',
  styleUrls: ['../css/RecompensaAdmin.component.css'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({ height: '0px', minHeight: '0' })),
      state('expanded', style({ height: '*' })),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ]
})
export class RecompensaAdminComponent implements OnInit {
  minDate: Date;
  newRecompensa: any;
  displayedColumns: string[] = ['id', 'nombre', 'descripcion', 'puntos'];
  recompensaPage!: MatTableDataSource<Recompensa>;
  copyRecompensaPage!: MatTableDataSource<Recompensa>;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  expandedElement: Recompensa | null | undefined;
  files: Array<File>;
  cadenaBuscar: string = "";
  showChip: boolean = false;
  blob!: Blob;

  constructor(private recompensaService: RecompensaService, private fileService: FileService, private _snackBar: MatSnackBar) {
    this.minDate = new Date();
    this.newRecompensa = {};
    this.files = [];
    this.recompensaPage = new MatTableDataSource<Recompensa>([new Recompensa({ "nombre": "Crear recompensa" })]);
  }


  ngOnInit() {
    this.recompensaService.getAll().subscribe(data => this.iniciarPaginacion(data));

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


  removeFile(file: string) {
    let aux = this.newRecompensa.files.split(",")
    aux.splice(aux.indexOf(file), 1);
    this.newRecompensa.files = aux.join();
  }


  iniciarPaginacion(data: Recompensa[]): void {
    this.recompensaPage.data.push(...data);
    this.recompensaPage.paginator = this.paginator;
  }

  expandir(element: any): void {
    this.newRecompensa = element;
    this.expandedElement = this.expandedElement === element ? null : element;    
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
    console.log(this.files);
  }


  deleteBusqueda() {
    this.showChip = false;
    this.cadenaBuscar = "";
    this.recompensaPage = this.copyRecompensaPage;
  }

  buscar() {
    this.recompensaService.getByNombreLike(this.cadenaBuscar).subscribe(data => { this.copyRecompensaPage = this.recompensaPage; this.recompensaPage = new MatTableDataSource<Recompensa>(data) });
    this.showChip = true;
  }

  guardar() {
    const e = new Recompensa(this.newRecompensa);
    const fnames: string[] = [];
    Array.from(this.files).forEach(file => fnames.push(file.name));
    e.files = fnames.join();
    console.log()
    this.recompensaService.save(e).subscribe(data => this.recompensaPage.data.push(data));
    const formData = new FormData();
    Array.from(this.files).forEach(file => formData.append("files", file));
    this.fileService.save(formData).subscribe(data => console.log(data));

  }

  optionText(op: Equipo) {
    if (op == null) { return ""; }
    return op.nombre;

  }
}
