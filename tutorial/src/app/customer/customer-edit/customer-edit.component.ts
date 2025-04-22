import { Component, Inject, OnInit } from '@angular/core';
import { Customer } from '../model/Customer';
import { CustomerService } from '../customer.service';
import {  MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-customer-edit',
  templateUrl: './customer-edit.component.html',
  styleUrls: ['./customer-edit.component.scss']
})
export class CustomerEditComponent implements OnInit {
  customer: Customer;
  constructor(
    public dialogRef:MatDialogRef<CustomerEditComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private customerService: CustomerService
  ) {
    

  }
  ngOnInit(): void {
    if (this.data.customer != null) {
      this.customer = this.data.customer;
    }
    else {
      this.customer = new Customer();
    }
  }

  onSave() {
    this.customerService.saveCustomer(this.customer).subscribe(result => {
      this.dialogRef.close();
    });    
  }  

  onClose() {
    this.dialogRef.close();
  }

}
