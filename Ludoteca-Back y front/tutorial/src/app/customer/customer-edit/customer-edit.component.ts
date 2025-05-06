import { Component, Inject, OnInit } from '@angular/core';
import { Customer } from '../model/Customer';
import { CustomerService } from '../customer.service';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-customer-edit',
  templateUrl: './customer-edit.component.html',
  styleUrls: ['./customer-edit.component.scss']
})
export class CustomerEditComponent implements OnInit {
  customer: Customer;
  errorText: String;
  errorB:Boolean;
  constructor(
    public dialogRef: MatDialogRef<CustomerEditComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private customerService: CustomerService
  ) {


  }
  ngOnInit(): void {
    if (this.data.customer != null) {
      this.customer = Object.assign({}, this.data.customer);
    }
    else {
      this.customer = new Customer();
    }
  }

  onSave() {
    this.customerService.saveCustomer(this.customer).subscribe(result => {
      this.dialogRef.close();
    },

      err => {
       // console.log("aqui","Error::::"+err.status);
        if (err.status === 400) {
          this.errorB=true;
          this.errorText = "Cliente repetido";
        }
      }

    );

  }

  // checkRepeatName(){
  //   this.customerService.getCustomer().subscribe(result=> {

  //   })
  // }

  onClose() {
    this.dialogRef.close();
  }

}
