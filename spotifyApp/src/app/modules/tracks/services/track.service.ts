import { Injectable } from '@angular/core';
import { TrackModel } from '@core/models/tracks.model';
import { Observable, of } from 'rxjs';
import * as dataRaw from '../../../data/tracks.json';

@Injectable({
  providedIn: 'root'
})
export class TrackService {
  dataTracksTrending$: Observable<TrackModel[]> = of([])
  dataTracksRandom$:Observable<TrackModel[]>= of([])

    constructor() {
      const {data}: any= (dataRaw as any)
      this.dataTracksTrending$= of(data)
      
      this.dataTracksRandom$= new Observable((observer) =>{
       const trackExample: TrackModel = {
        _id: '9',
         name: 'Lament',
         album: 'Time Fate Love',
         cover: 'https://i.ytimg.com/vi/dz_NJf-h9bo/maxresdefault.jpg?sqp=-oaymwEmCIAKENAF8quKqQMa8AEB-AH-CYAC0AWKAgwIABABGD0gZShTMA8=&rs=AOn4CLDFOrMNZTYczOfTraJsoEYfZ6cXQg',
         url: 'http://'
         
       }
       setTimeout( () => {
        observer.next([trackExample])
       },3500)
        
      })
     }
}


