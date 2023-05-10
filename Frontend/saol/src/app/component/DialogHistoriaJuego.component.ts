import { Component, Inject, OnInit } from "@angular/core";
import { MAT_DIALOG_DATA, MatDialogRef } from "@angular/material/dialog";


@Component({
    templateUrl:"../html/DialogHistoriaJuego.component.html",
    styleUrls: ["../css/DialogHistoriaJuego.component.css"]
})
export class DialogHistoriaJuegoComponent implements OnInit{
    constructor(public dialogRef: MatDialogRef<DialogHistoriaJuegoComponent>, @Inject(MAT_DIALOG_DATA) public data: any){

    }

    ngOnInit(): void {
        
    }
}