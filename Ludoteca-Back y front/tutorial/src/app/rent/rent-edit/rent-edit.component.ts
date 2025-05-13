import { Component, Inject, OnInit } from '@angular/core';
import { GameService } from '../../game/game.service';
import { RentService } from '../rent.service';
import { CustomerService } from 'src/app/customer/customer.service';
import { Game } from 'src/app/game/model/Game';
import { Customer } from 'src/app/customer/model/Customer';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Rent } from '../model/Rent';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { spaceValidator } from 'src/app/validators/space-validator';


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
  minDate = new Date();
  minDateEnd = new Date();
  form: FormGroup

  constructor(
    private rentService: RentService,
    private gameService: GameService,
    private customerService: CustomerService,
    @Inject(MAT_DIALOG_DATA) public data: any,
    public dialogRef: MatDialogRef<RentEditComponent>,
    private snackBar: MatSnackBar,
    private fb: FormBuilder
  ) {
    this.form = this.fb.group({
      id: [{ value: '', disabled: true }],
      customer: ['', [Validators.required]],
      game: ['', [Validators.required]],
      initialDate: [null, [Validators.required, spaceValidator]],
      endDate: [null, [Validators.required, spaceValidator]],
    });
    this.form.get('initialDate')?.valueChanges.subscribe((f: Date) => {
      const endDateControl = this.form.get('endDate');
      const endDAte = endDateControl?.value;
      if (f) {
        this.minDateEnd = f;
        if (endDAte && endDAte < f) {
          endDateControl?.setValue(null);
        }
        endDateControl?.updateValueAndValidity();
      }

    });
    this.minDate = new Date();

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
          const gameFilter = games.filter(ga =>
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
          const customerFilter = customers.find(cust => cust.id == this.data.rent.customer.id);
          if (customerFilter) {
            this.rent.customer = customerFilter[0];
          }
        }

      }
    )
  }

  onInitEndDateValidator(control: AbstractControl) {
    const initialDate = this.form?.get('initialDate')?.value;
    if (initialDate && control.value < initialDate) {
      return { invalidDate: true };
    }
    return null;
  }

  onSave() {
    if (this.rent.customer != null || this.rent.initialDate != null || this.rent.endDate != null) {
      this.rent.initialDate = this.selectedInitialDay.toLocaleDateString('en-CA')
      this.rent.endDate = this.selectedEndDay.toLocaleDateString('en-CA')
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
          }
        }
      })
    }
  }

  onClose() {
    this.dialogRef.close();
  }
}
