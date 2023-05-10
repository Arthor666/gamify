import { animate, state, style, transition, trigger } from '@angular/animations';
import { Component, OnInit, ViewChild } from "@angular/core";
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute } from '@angular/router';
import { number } from 'echarts';
import { Equipo } from '../models/Equipo';
import { HistoriaUsuario } from '../models/HistoriaUsuario';
import { Status } from '../models/Status';
import { HistoriaUsuarioService } from '../service/HistoriaUsuario.service';
import { StatusService } from '../service/Status.service';
import * as CryptoJS from 'crypto-js';
import { globalEnum } from '../globalEnum';

@Component({
  selector: 'recompensa-admin',
  templateUrl: '../html/HistoriasUsuario.component.html',
  styleUrls: ['../css/HistoriasUsuario.component.css'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({ height: '0px', minHeight: '0' })),
      state('expanded', style({ height: '*' })),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ]
})
export class HistoriasUsuarioComponent implements OnInit {

  newHistoria: HistoriaUsuario;
  cadenaBuscar: string = "";
  showChip: boolean = false;
  historiaPage!: MatTableDataSource<HistoriaUsuario>;
  copyHistoriaPage!: MatTableDataSource<HistoriaUsuario>;
  displayedColumns: string[] = ['id', 'nombre', 'descripcion', 'puntosHistoria', 'status'];
  expandedElement: HistoriaUsuario | null | undefined;
  statusList: Array<Status> = [];
  idEquipo: number;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  constructor(private snackBar: MatSnackBar, private route: ActivatedRoute, private historiaService: HistoriaUsuarioService, private statusService: StatusService) {
    this.newHistoria = new HistoriaUsuario({});
    this.historiaPage = new MatTableDataSource<HistoriaUsuario>([new HistoriaUsuario({ "nombre": "Crear historia" })]);
    this.idEquipo = Number(CryptoJS.enc.Utf8.stringify(CryptoJS.AES.decrypt(this.route.snapshot.paramMap.get("idEquipo").replaceAll("*", "/"), globalEnum.secret)));
  }

  buscar() {
    this.historiaService.getByNombreLike(this.cadenaBuscar).subscribe(data => {
      this.copyHistoriaPage = this.historiaPage;
      this.historiaPage= new MatTableDataSource<HistoriaUsuario>(data)
    });
    this.showChip = true;
  }

  guardar() {
    this.newHistoria.equipo = new Equipo({ "id": this.idEquipo });
    this.historiaService.save(this.newHistoria).subscribe(data => {
      if (this.newHistoria.id == undefined) {
        this.historiaPage.data.push(data);
        this.historiaPage.paginator = this.paginator;
      }   
      this.snackBar.open("Historia guardada","Ok");
    });
  }

  expandir(element: any): void {
    this.newHistoria = element;
    this.expandedElement = this.expandedElement === element ? null : element;

  }

  ngOnInit() {
    this.historiaService.getByEquipoId(this.idEquipo).subscribe(data => this.iniciarPaginacion(data))
    this.statusService.getByClase("historiasUsuario").subscribe(data => this.statusList =data);
  }

  iniciarPaginacion(data: HistoriaUsuario[]): void {
    this.historiaPage.data.push(...data);
    this.historiaPage.paginator = this.paginator;
  }

  compareStatus(h1: HistoriaUsuario, h2: HistoriaUsuario) {
    return h1 && h2 ? h1.id === h2.id : h1 === h2;
  }

  deleteBusqueda() {
    this.showChip = false;
    this.cadenaBuscar = "";
    this.historiaPage = this.copyHistoriaPage;
  }
}
