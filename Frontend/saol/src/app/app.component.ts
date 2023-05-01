import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { DialogNotificacionComponent } from './component/DialogNotificaciones.component';
import { globalEnum } from './globalEnum';
import { Usuario } from './models/Usuario';
import { NotificacionService } from './service/Notificacion.service';
import * as CryptoJS from 'crypto-js';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'saol';
  numNotificacion: number = 0;
  usuarioId: number;

  constructor(private router: Router, private notificacionesService: NotificacionService, private dialog: MatDialog) {
    let encryptedUser = localStorage.getItem(globalEnum.usuarioLocalStorage);
    if (encryptedUser != undefined) {
      let cUsuario = JSON.parse(CryptoJS.enc.Utf8.stringify(CryptoJS.AES.decrypt(encryptedUser, globalEnum.secret))) as Usuario;
      if (cUsuario != undefined) {
        this.usuarioId = Number(cUsuario.id);
      }    
    }    
  }

  ngOnInit() {
    if (this.usuarioId != undefined) {
      this.notificacionesService.countByUsuarioId(this.usuarioId).subscribe(data => console.log(data));
    }    
  }

  logout() {
    localStorage.removeItem(globalEnum.usuarioLocalStorage);
    localStorage.removeItem(globalEnum.token);  
    this.router.navigate(['/signin'])
      .then(() => {
        window.location.reload();
      });  
  }

  abrirNotificaciones() {
    const dialogRef = this.dialog.open(DialogNotificacionComponent, {
      data: { id: this.usuarioId },
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');      
    });  
  }
}
