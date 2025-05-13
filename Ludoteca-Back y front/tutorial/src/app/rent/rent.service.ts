import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Pageable } from '../core/model/page/Pageable';
import { Observable } from 'rxjs';
import { RentPage } from './model/RentPage';
import { Rent } from './model/Rent';

let url = 'http://localhost:8080/rent';
@Injectable({
  providedIn: 'root'
})
export class RentService {
  constructor(private http: HttpClient) { }
  getRents(pageable: Pageable, customerId: number, gameId: number, dateSelectedDay: string): Observable<RentPage> {
    return this.http.post<RentPage>(this.composeFindUrl(customerId, gameId, dateSelectedDay), { pageable: pageable });
  }

  private composeFindUrl(customerId: number, gameId: number, dateSelectedDay): string {
    let param = new URLSearchParams();
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
    const queryString = param.toString();
    return queryString ? `${url}?${queryString}` : url;
  }

  SaveRent(rent: Rent): Observable<Rent> {
    return this.http.put<Rent>(url, rent);
  }

  deleteRent(idRent: number): Observable<void> {
    return this.http.delete<void>(url + '/' + idRent);
  }
}
