import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Customer } from './model/Customer';
import { HttpClient } from '@angular/common/http';
import { CUSTOMER_DATA } from './model/mock-customer';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(
    // private http:HttpClient
  ) { }

  getCustomer(): Observable<Customer[]> {
    return of(CUSTOMER_DATA);
  }
  saveCustomer(customer: Customer):Observable<Customer>{
    return of(null);
  }
  deleteCustomer(idCustomer:number):Observable<any>{
    return of(null);
  }
}
