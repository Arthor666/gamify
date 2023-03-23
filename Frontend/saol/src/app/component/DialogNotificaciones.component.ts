import { Component, Inject, OnInit } from "@angular/core";
import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material/dialog";
import { Notificacion } from "../models/Notificacion";
import { NotificacionService } from "../service/Notificacion.service";

@Component({
  templateUrl: '../html/DialogNotificacion.component.html',
  styleUrls: ['../css/DialogNotificacion.component.css']
})
export class DialogNotificacionComponent implements OnInit {
  notificacionList: Array<Notificacion>
  displayedColumns: string[] = ["id"]
  constructor(public dialogRef: MatDialogRef<DialogNotificacionComponent>, @Inject(MAT_DIALOG_DATA) public data: any, private notificacionService: NotificacionService) {
    this.notificacionList = [];
  }

  ngOnInit() {
    this.notificacionService.getByUsuarioId(this.data.id).subscribe(data => this.notificacionList = data);
  }

  onNoClick(): void {
    this.dialogRef.close();
  }
}
