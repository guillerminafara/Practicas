import { Component, Inject, OnInit } from '@angular/core';
import { GameService } from '../../game/game.service';
import { RentService } from '../rent.service';
import { CustomerService } from 'src/app/customer/customer.service';
import { Game } from 'src/app/game/model/Game';
import { Customer } from 'src/app/customer/model/Customer';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Rent } from '../model/rent';
import { MatSnackBar } from '@angular/material/snack-bar';


@Component({
  selector: 'app-rent-edit',
  templateUrl: './rent-edit.component.html',
  styleUrls: ['./rent-edit.component.scss']
})
export class RentEditComponent implements OnInit {
  games: Game[];
  customers: Customer[];
  rent: Rent;

  customerName: Customer;
  gameName: Game;
  Identificador: number;
  selectedInitialDay: Date = new Date();
  intialPicker: Date;
  endPicker: Date;
  selectedEndDay: Date;
  errorB: Boolean;
  errorText: string;

  constructor(
    private rentService: RentService,
    private gameService: GameService,
    private customerService: CustomerService,
    @Inject(MAT_DIALOG_DATA) public data: any,
    public dialogRef: MatDialogRef<RentEditComponent>,
    private snackBar: MatSnackBar
  ) {

  }
  ngOnInit(): void {
    if (this.data.rent != null) {
      this.rent = Object.assign({}, this.data.rent);
    } else {
      this.rent = new Rent();
    }

    this.gameService.getGames().subscribe(
      games => {
        this.games = games
        if (this.rent.game) {
          let gameFilter = games.filter(ga =>
            ga.id == this.rent.game.id);

          if (gameFilter) {
            this.rent.game = gameFilter[0];
          }
        }

      }
    );

    this.customerService.getCustomer().subscribe(
      customers => {
        this.customers = customers

        if (this.rent.customer) {
          let customerFilter = customers.find(cust => cust.id == this.data.rent.customer.id);
          console.log("<<----------------->", customerFilter);
          if (customerFilter) {
            this.rent.customer = customerFilter[0];
          }
        }

      }
    )
  }


  onSave() {
    console.log("--->",!this.rent.customer,!this.rent.initialDate, !this.rent.endDate)
    //if (!this.rent.customer || !this.rent.initialDate || !this.rent.endDate) {
    if (this.rent.customer != null || this.rent.initialDate != null || this.rent.endDate != null) {

      this.rent.initialDate = this.selectedInitialDay.toLocaleDateString('en-CA')
      this.rent.endDate = this.selectedEndDay.toLocaleDateString('en-CA')
      console.log(JSON.stringify(this.rent))

 
      this.rentService.SaveRent(this.rent).subscribe({
        next: () => {
          this.dialogRef.close();
        },
        error: (error) => {
          if (error.status === 400) {
            this.errorB = true;
            this.errorText = " El alquiler no puede superar los 14 días";

            this.snackBar.open(this.errorText, 'Cerrar', { duration: 4000 });
          } else if (error.status === 409) {
            this.errorB = true;

            this.errorText = "Error al guardar el alquiler"

            this.snackBar.open(this.errorText, 'Cerrar', { duration: 4000 });
          } else if (error.status === 500) {
            this.errorB = true;
            this.errorText = "Error interno del servidor.";
          } else {
            console.log(error.status);
          }

        }
      })
 
    }
  }

  onClose() {
    this.dialogRef.close();
  }
}
