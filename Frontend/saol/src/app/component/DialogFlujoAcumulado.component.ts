import { Component, Inject, OnInit } from "@angular/core";
import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material/dialog";
import { FlujoAcumulado } from "../models/FlujoAcumulado";
import { Status } from "../models/Status";
import { FlujoAcumuladoService } from "../service/FlujoAcumulado.service";
import { StatusService } from "../service/Status.service";
import { DatePipe } from '@angular/common';


@Component({
  templateUrl: '../html/DialogFlujoAcumulado.component.html',
  styleUrls : ['../css/DialogFlujoAcumulado.component.css']
})
export class DialogFlujoAcumuladoComponent implements OnInit {
  countryCasesChartOptions: any;

  constructor(public dialogRef: MatDialogRef<DialogFlujoAcumuladoComponent>, @Inject(MAT_DIALOG_DATA) public data: any, private statusService: StatusService, private flujoAcumuladoService: FlujoAcumuladoService,private datepipe: DatePipe) {

  }
  ngOnInit() {
    var mapStatus = new Map<Status, Array<FlujoAcumulado>>();    
    this.statusService.getByClase("tarea").subscribe(statusList1 => {
      statusList1.forEach(s => mapStatus.set(s,[]));      
      this.statusService.getByEquipoId(this.data.id).subscribe(statusList2 => {
        statusList2.forEach(s => mapStatus.set(s, []));
        mapStatus.forEach((value, key) => {
          this.flujoAcumuladoService.getByEquipoIdAndStatusId(this.data.id, key.id).subscribe(data => {
            mapStatus.get(key).push(...data);
            this.createChart(mapStatus);
          });          
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

  createChart(mapStatus: Map<Status, Array<FlujoAcumulado>>) {
    let s = [];
    mapStatus.forEach((value, key) => {
      const rColor = this.getRandomColor();
      s.push({
        name: key.nombre,
        type: 'line',
        color: rColor,
        areaStyle: {
          color: rColor,
          opacity: 0.5        },
        data: []
      });
      value.forEach(x => {
        s[(s.length - 1)].data.push(x.numTareas);
      });
    });
    this.countryCasesChartOptions = {
      title: {
        text: 'Equipo:' + this.data.nombre,
      },
      legend: {
        data: Array.from(mapStatus.keys()).map(x => x.nombre)
      },
      tooltip: {
      },
      xAxis: {
        data: mapStatus.get(Array.from(mapStatus.keys())[0]).map(x => this.datepipe.transform(x.fechaGuardado, 'dd/MM/yyyy')),
      },
      yAxis: {
        type: 'value'
      },
      series: s
    }
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

}
