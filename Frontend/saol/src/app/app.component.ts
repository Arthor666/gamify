import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { DialogNotificacionComponent } from './component/DialogNotificaciones.component';
import { NotificacionService } from './service/Notificacion.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'saol';
  numNotificacion: number = 0;
  usuarioId: number = 67;

  constructor(private notificacionesService: NotificacionService, private dialog: MatDialog) {

  }

  ngOnInit() {
    this.notificacionesService.countByUsuarioId(this.usuarioId).subscribe(data => this.numNotificacion = data.count);
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
