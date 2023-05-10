import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { DialogHistoriaJuegoComponent } from './DialogHistoriaJuego.component';
import * as CryptoJS from 'crypto-js';
import { ActivatedRoute } from '@angular/router';
import { globalEnum } from '../globalEnum';
import { JuegoServidorService } from '../service/JuegoServidor.service';
import { JuegoServidor } from '../models/JuegoServidor';
import { Equipo } from '../models/Equipo';


@Component({
  templateUrl:'../html/JuegoServidor.component.html',
  styleUrls: ['../css/JuegoServidor.component.css']
})
export class JuegoServidorComponent implements OnInit{  
  numberOfUsers = 0;
  showAnimation :boolean= false;          
  idEquipo = 0;
  juegoServidor : JuegoServidor;
  precio = 50;
  

  constructor(private juegoService : JuegoServidorService,private route: ActivatedRoute,private dialog: MatDialog,private snackBar: MatSnackBar){
    let idEquipoCiphred = this.route.snapshot.paramMap.get("idEquipo");
    this.idEquipo = Number(CryptoJS.enc.Utf8.stringify(CryptoJS.AES.decrypt(idEquipoCiphred.replaceAll("*", "/"), globalEnum.secret)));
    this.juegoServidor = new JuegoServidor({});
  }

  ngOnInit(){
    this.juegoService.getByEquipoId(this.idEquipo).subscribe(data => {
      if(data.length <= 0){
        this.juegoServidor.equipo = new Equipo({"id":this.idEquipo});        
        this.juegoService.save(this.juegoServidor).subscribe(data => this.juegoServidor = data);
        return;
      }
      this.juegoServidor = data[0];
      this.calcularNumeroUsuarios();
    });    
  }

  calcularNumeroUsuarios(){
    let factor = this.juegoServidor.levelServer*4+this.juegoServidor.levelHdd+this.juegoServidor.levelRam+this.juegoServidor.levelRed;
    this.numberOfUsers = 25*factor;
  }

  openDialogHistoria(){    
    this.dialog.open(DialogHistoriaJuegoComponent,{});
  }

  delay(ms: number) {
    return new Promise( resolve => setTimeout(resolve, ms) );
  }

  async aumentarUsuarios(usersIncremented:number,increment: number){    
    this.showAnimation = true;
    for(let i = 0; i < usersIncremented ; i+=increment ){
      this.numberOfUsers += increment;      
      await this.delay(300);
    }
    this.showAnimation = false;    
  }


  mejorarRam(){    
    this.juegoServidor.levelRam += 1;
    this.juegoServidor.monedas -= this.precio;
    this.juegoService.save(this.juegoServidor).subscribe(data => this.juegoServidor = data);
    this.aumentarUsuarios(25,5);
  }

  mejorarHdd(){
    this.juegoServidor.levelHdd += 1;
    this.juegoServidor.monedas -= this.precio;
    this.juegoService.save(this.juegoServidor).subscribe(data => this.juegoServidor = data);
    this.aumentarUsuarios(25,5);
  }

  mejorarRed(){
    this.juegoServidor.levelRed += 1;
    this.juegoServidor.monedas -= this.precio;
    this.juegoService.save(this.juegoServidor).subscribe(data => this.juegoServidor = data);
    this.aumentarUsuarios(25,1);
  }

  cambiarServidor(){
    if(this.juegoServidor.levelServer >= 5){
      this.snackBar.open("Tu servidor ya está al nivel máximo","ok");
      return;
    }
    this.juegoServidor.levelServer += 1;
    this.juegoServidor.levelHdd =0;
    this.juegoServidor.levelRed =0;
    this.juegoServidor.levelRam =0;
    this.juegoServidor.monedas -= this.precio;
    this.aumentarUsuarios(25,5);        
    this.juegoService.save(this.juegoServidor).subscribe(data => this.juegoServidor = data);
  }

  isUpgradable(){
    return ! (this.juegoServidor.levelHdd == 1 && this.juegoServidor.levelRam == this.juegoServidor.levelHdd && this.juegoServidor.levelRed == this.juegoServidor.levelHdd && this.juegoServidor.monedas >= this.precio);
  }
}
