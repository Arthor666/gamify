import { Component, Inject, OnInit } from "@angular/core";
import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material/dialog";
import { Equipo } from "../models/Equipo";
import { Proyecto } from "../models/Proyecto";
import { FileService } from "../service/File.service";
import { ProyectoService } from "../service/Proyecto.service";

@Component({
  templateUrl: '../html/DialogProyecto.component.html',
  styleUrls: ['../css/DialogProyecto.component.css']
})
export class DialogProyectoComponent implements OnInit {
  proyecto: Proyecto;
  blob!: Blob;
  constructor(private fileService: FileService,public dialogRef: MatDialogRef<DialogProyectoComponent>, @Inject(MAT_DIALOG_DATA) public data: any, private proyectoService: ProyectoService) {

  }
  ngOnInit(): void {
    this.proyectoService.getByEquipoId(this.data.id).subscribe(data => this.proyecto=data);
  }


  onNoClick(): void {
    this.dialogRef.close();
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

}
