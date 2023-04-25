import { Component, Inject, OnInit } from "@angular/core";
import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material/dialog";
import { FlujoAcumulado } from "../models/FlujoAcumulado";
import { Status } from "../models/Status";
import { FlujoAcumuladoService } from "../service/FlujoAcumulado.service";
import { StatusService } from "../service/Status.service";
import { DatePipe } from '@angular/common';
import { Quemado } from "../models/Quemado";
import { QuemadoService } from "../service/Quemado.service";
import { ProyectoService } from "../service/Proyecto.service";
import { Proyecto } from "../models/Proyecto";
import { HistoriaUsuarioService } from "../service/HistoriaUsuario.service";
import { EquipoService } from "../service/Equipo.service";


@Component({
  templateUrl: '../html//DialogDiagramaQuemado.component.html',
  styleUrls: ['../css/DialogDiagramaQuemado.component.css']
})
export class DialogDiagramaQuemadoComponent implements OnInit {
  countryCasesChartOptions: any;
  dataQuemado: Array<Quemado>
  proyecto: Proyecto;
  totalPuntos: number
  constructor(private equipoService: EquipoService, private historiaService: HistoriaUsuarioService, private proyectoService: ProyectoService, public dialogRef: MatDialogRef<DialogDiagramaQuemadoComponent>, @Inject(MAT_DIALOG_DATA) public data: any, private quemadoService: QuemadoService, private datepipe: DatePipe) {

  }

  ngOnInit() {
    this.quemadoService.getByEquipoId(this.data.id).subscribe(quemados => {
      this.dataQuemado = quemados;      
      this.proyectoService.getByEquipoId(this.data.id).subscribe(proyecto => {
        this.proyecto = proyecto;
        this.historiaService.getByPuntosByEquipoId(this.data.id).subscribe(puntos => {
          this.totalPuntos = puntos;          
          this.createChart();
        });        
      });
    });

  }

  getRandomColor() {
    let letters = 'A45F2E7910B3C6D8';
    let color = '#';
    for (var i = 0; i < 6; i++) {
      color += letters[Math.floor(Math.random() * 16)];
    }
    return color
  }


  getDiffDays(sDate: Date, eDate: Date) {
    var startDate = new Date(sDate);
    var endDate = new Date(eDate);
    var days = endDate.getTime() - startDate.getTime();
    return Math.floor(days / (1000 * 3600 * 24));
  }

  createChart() {
    let ideal =
    {
      name: "Remanente ideal",
      type: 'line',
      color: this.getRandomColor(),
      data: []
    };
    let real = {
      name: "Remanente real",
      type: 'line',
      color: this.getRandomColor(),
      data: []
    };
    let days = this.getDiffDays(this.data.fechaCreacion, this.dataQuemado[0].fechaGuardado)
    for (let i = 0; i < (days - 1); i++) {
      real.data.push(this.totalPuntos);
    }
    real.data.push(...this.dataQuemado.map(x => { return this.totalPuntos - x.puntosQuemado }));
    days = this.getDiffDays(this.dataQuemado[0].fechaGuardado, this.proyecto.fechaEntrega);
    for (let i = 0; i < days ; i++) {
      real.data.push(undefined);
    }
    days = this.getDiffDays(this.data.fechaCreacion, this.proyecto.fechaEntrega);
    let totalPuntosPerDay = this.totalPuntos / days;
    let xAxis = [];
    for (let i = 0; i < days; i++) {
      ideal.data.push((this.totalPuntos) - i * totalPuntosPerDay);
      xAxis.push(days-i);

    }    
    this.countryCasesChartOptions = {
      title: {
        text: 'Equipo:' + this.data.nombre,
      },
      legend: {
        data: ["Remanente ideal","Remanente real"]
      },
      tooltip: {
      },
      xAxis: {
        data: xAxis,
        name: "No. días para concluir el proyecto"
      },
      yAxis: {
        type: 'value',
        name: "Puntos de historia"
      },
      series: [ideal, real]
    }
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

}
