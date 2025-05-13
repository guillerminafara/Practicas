import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Customer } from './model/Customer';
import { HttpClient } from '@angular/common/http';

let url = 'http://localhost:8080/customer';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  constructor(
    private http: HttpClient
  ) { }

  getCustomer(): Observable<Customer[]> {
    return this.http.get<Customer[]>(url);
  }

  saveCustomer(customer: Customer): Observable<Customer> {
    if (customer.id != null) url += '/' + customer.id;
    return this.http.put<Customer>(url, customer);
  }

  deleteCustomer(idCustomer: number): Observable<any> {
    return this.http.delete(url + '/' + idCustomer);
  }
}
