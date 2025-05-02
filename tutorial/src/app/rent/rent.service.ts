
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

  getRents(pageable: Pageable, customerId: number, gameId: number, dateSelectedDay: string): Observable<RentPage> {
    return this.http.post<RentPage>(this.composeFindUrl(customerId, gameId, dateSelectedDay), { pageable: pageable });
  }

  private composeFindUrl(customerId: number, gameId: number, dateSelectedDay): string {
    let param = new URLSearchParams();
    // if(customerId != null && gameId!= null){
    //   param+='customerId='+ customerId+"&"+'gameId='+ gameId+"";
    // }else{
    if (customerId != null) {
      param.append('customerId', customerId.toString());
    }
    if (gameId != null) {
      param.append('gameId', gameId + "");
    }
    if (dateSelectedDay != null) {
      param.append('initialDay', dateSelectedDay);
      console.log("comprobaciones de NO  nuoll", param);
    } else {
      console.log("comprobaciones de nuoll", param);
    }
    // }
    let url = 'http://localhost:8080/rent';
    const queryString = param.toString();

    // let url = 'http://localhost:8080/rent';
    // if (param.toString() == '') return url;
    // else return url + '?' + param.toString();
    return  queryString ? `${url}?${queryString}` :url;
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
