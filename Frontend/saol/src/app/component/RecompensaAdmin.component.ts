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
import { globalEnum } from '../globalEnum';
import { Usuario } from '../models/Usuario';
import * as CryptoJS from 'crypto-js';

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
  newRecompensa: Recompensa;
  displayedColumns: string[] = ['id', 'nombre', 'descripcion', 'puntos'];
  recompensaPage!: MatTableDataSource<Recompensa>;
  copyRecompensaPage!: MatTableDataSource<Recompensa>;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  expandedElement: Recompensa | null | undefined;  
  cadenaBuscar: string = "";
  showChip: boolean = false;
  blob!: Blob;
  profesorId: number;

  constructor(private snackBar: MatSnackBar,private recompensaService: RecompensaService, private _snackBar: MatSnackBar) {
    this.minDate = new Date();
    this.newRecompensa = new Recompensa({});
    this.recompensaPage = new MatTableDataSource<Recompensa>([new Recompensa({ "nombre": "Crear recompensa", "isEditable": true })]);
    this.profesorId = Number(JSON.parse(CryptoJS.enc.Utf8.stringify(CryptoJS.AES.decrypt(localStorage.getItem(globalEnum.usuarioLocalStorage), globalEnum.secret))).id);
  }


  ngOnInit() {
    this.recompensaService.getCommons().subscribe(data => {
      data.forEach(x => x.isEditable = false);
      this.iniciarPaginacion(data);
    });
    this.recompensaService.getByProfesorId(this.profesorId).subscribe(data => {
      if (data != undefined && data.length != -1) {
        data.forEach(x => x.isEditable = true);
        this.recompensaPage.data.push(...data);
        this.recompensaPage.paginator = this.paginator;
      }       
    });

  }

  




  iniciarPaginacion(data: Recompensa[]): void {
    this.recompensaPage.data.push(...data);
    this.recompensaPage.paginator = this.paginator;
  }

  expandir(element: any): void {
    if (element.isEditable || element.isEditable == undefined) {
      this.newRecompensa = element;
      this.expandedElement = this.expandedElement === element ? null : element;    
    }    
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
    this.newRecompensa.profesor = JSON.parse(CryptoJS.enc.Utf8.stringify(CryptoJS.AES.decrypt(localStorage.getItem(globalEnum.usuarioLocalStorage), globalEnum.secret))) as Usuario;
    const e = new Recompensa(this.newRecompensa);
    const fnames: string[] = [];        
    this.recompensaService.save(e).subscribe(data => {
      if (this.newRecompensa.id == undefined) {
        this.recompensaPage.data.push(data);
        this.recompensaPage.paginator = this.paginator;
        this.snackBar.open("Recompensa guardada","Ok");
      }      
    });
    const formData = new FormData();
  }

  optionText(op: Equipo) {
    if (op == null) { return ""; }
    return op.nombre;

  }
}
