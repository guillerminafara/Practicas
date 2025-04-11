import { Injectable } from '@angular/core';
import { Observable, of} from 'rxjs';
import { catchError, map, mergeMap, tap } from 'rxjs/operators';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { TrackModel } from '@core/models/tracks.model';


@Injectable({
  providedIn: 'root'
})
export class TrackService {
  private readonly URL = environment.api;
  constructor(private httpClient: HttpClient) {

  }
  private skipById(listTracks: TrackModel[], id: number): Promise<TrackModel[]> {
    return new Promise((resolve, reject) => {
      const listTmp = listTracks.filter(a => a._id !== id)
      resolve(listTmp)
    })
  }
  getAllTracks$(): Observable<any> {

    return this.httpClient.get(`${this.URL}/tracks`, {
      headers: new HttpHeaders({authorization: 'Bearer TOKEN'})
    })
      .pipe(
        map(({ data }: any) => {
          return data
        })
      )
  }

  getAllRandom$(): Observable<any> {

    return this.httpClient.get(`${this.URL}/tracks`)
      .pipe(
        mergeMap(({ data }: any) => this.skipById(data,3)),
        // map(( dataRevertida) => {
        //   return dataRevertida.filter(
        //     (track: TrackModel) => track._id !== 1)
        // })
        tap(data => console.log("tap",data)),
        catchError((err) => {
          const{ status, statusText}= err;
          console.log("algo paso: ", [status, statusText]);
          return of([])
        })
      )
  }
}




