import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Customer } from '../model/Customer';
import { CustomerService } from '../customer.service';

@Component({
  selector: 'app-customer-list',
  templateUrl: './customer-list.component.html',
  styleUrls: ['./customer-list.component.scss']
})
export class CustomerListComponent implements OnInit {
  dataSource = new MatTableDataSource<Customer>();
  displayedColumns: string[] = ['id', 'name', 'action'];

  constructor(
    private customerService: CustomerService
  ) { }
  ngOnInit(): void {
    this.customerService.getCustomer().subscribe(customer => this.dataSource.data = customer);
  }

}
