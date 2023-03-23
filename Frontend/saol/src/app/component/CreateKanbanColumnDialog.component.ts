import { Component, Inject } from "@angular/core";
import { MatDialog, MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';


@Component({  
  templateUrl: '../html/CreateKanbanColumnDialog.component.html',
  styleUrls: ['../css/CreateKanbanColumnDialog.component.css']
})
export class CreateKanbanColumnDialog {
  constructor(public dialogRef: MatDialogRef<CreateKanbanColumnDialog>, @Inject(MAT_DIALOG_DATA) public data: string) {

  }

  onNoClick(): void {
    this.dialogRef.close();
  }
}
