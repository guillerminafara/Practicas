import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RentListComponent } from './rent-list/rent-list.component';
import { MatTableModule } from '@angular/material/table';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatPaginatorModule } from '@angular/material/paginator';
import { HttpClientModule } from '@angular/common/http';
import { MatInputModule } from '@angular/material/input';



@NgModule({
  declarations: [
    RentListComponent
  ],
  imports: [
    CommonModule,
    MatTableModule,
    MatButtonModule,
    MatDialogModule,
    MatFormFieldModule,
    FormsModule,
    ReactiveFormsModule,
    MatPaginatorModule,
    MatInputModule,
    MatIconModule,
    HttpClientModule,
  ]
})
export class RentModule { }
