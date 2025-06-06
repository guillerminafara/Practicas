import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-dialog-confirmation',
  templateUrl: './dialog-confirmation.component.html',
  styleUrls: ['./dialog-confirmation.component.scss']
})
export class DialogConfirmationComponent implements OnInit {
  title: string;
  description: string;

  constructor(public dialogRef: MatDialogRef<DialogConfirmationComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {

  }
  ngOnInit(): void {
    this.title = this.data.title;
    this.description = this.data.descriptionM
  }

  onYes() {
    this.dialogRef.close(true);
  }
  onNo() {
    this.dialogRef.close(false);
  }
}
