
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

  // getRents(request:any): Observable<RentPage> {
  //   return this.http.post<RentPage>( ,this.composeFindUrl(customerId, gameId));
  // }

  getRents(pageable: Pageable, customerId: number, gameId: number): Observable<RentPage> {
    return this.http.post<RentPage>( this.composeFindUrl(customerId, gameId), { pageable: pageable });
  }

  private composeFindUrl(customerId: number, gameId: number): string {
    let param ='';
    if(customerId != null){
      param +='customerId'+ customerId+"";
    }
    if(gameId!= null){
      param += 'gameId'+ gameId+"";
    }

    let url = 'http://localhost:8080/rent';
    if (param == '') return url;
    else return url + '?' + param;
  }

  SaveRent(rent: Rent): Observable<void> {
    let url = 'http://localhost:8080/rent/' + rent.id;
    if (rent.id != null) url += '/' + rent.id;
    return this.http.put<void>(url, rent);
  }

  deleteAuthor(idRent: number): Observable<void> {
    return this.http.delete<void>('http://localhost/8080/rent/' + idRent);
  }

}
