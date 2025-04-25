
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Pageable } from '../core/model/page/Pageable';
import { Observable, of } from 'rxjs';
import { RentPage } from './model/RentPage';
import { Rent } from './model/rent';
import { RENT_DATA } from './model/mock-rent';

@Injectable({
  providedIn: 'root'
})
export class RentService {

  constructor(private http: HttpClient) { }

  getRents(pageable: Pageable): Observable<RentPage> {
     return this.http.post<RentPage>('http://localhost:8080/rent', { pageable: pageable });
    // return of(RENT_DATA);
  }

  // getAllRents(): Observable<Rent[]>{
  //   return this.http.get<Rent[]>('http://localhost:8080/rent');
  // }

   SaveRent(rent: Rent): Observable<void> {
      let url = 'http://localhost:8080/rent/' + rent.id;
      if (rent.id != null) url += '/' + rent.id;
      return this.http.put<void>(url, rent);
    }
  
    deleteAuthor(idRent: number): Observable<void> {
      return this.http.delete<void>('http://localhost/8080/rent/' + idRent);
    }

}
