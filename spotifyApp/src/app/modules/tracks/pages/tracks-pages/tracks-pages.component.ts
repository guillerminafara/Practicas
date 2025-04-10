import { Component, OnInit } from '@angular/core';
import { TrackModel } from '@core/models/tracks.model';
import { TrackService } from '@modules/tracks/services/track.service';
import { Subscription } from 'rxjs';
@Component({
  selector: 'app-tracks-pages',
  templateUrl: './tracks-pages.component.html',
  styleUrls: ['./tracks-pages.component.css']
})
export class TracksPagesComponent implements OnInit {
  tracksTrending: Array<TrackModel> = [
  ]
  tracksRandom: Array<TrackModel> = []
  listObserver$: Array<Subscription> = []
  constructor(private trackService: TrackService) {

  }
  ngOnInit(): void {
    // const {data}: any=(dataRaw as any).default
    // this.mockTrackList=data
    const observer1$ = this.trackService.dataTracksTrending$
    .subscribe(response => {
      this.tracksTrending=response
      this.tracksRandom=response
      console.log("canciones trending -->", response);
    })


    const observer2$ = this.trackService.dataTracksRandom$
    .subscribe(response => {
      this.tracksRandom=[... this.tracksRandom, ...response]
      console.log("canciones random -->", response);
    })
    this.listObserver$ = [observer1$, observer2$]
  
  }
  ngOnDestroy(): void {
   
    this.listObserver$.forEach(u=> u.unsubscribe())
  }
}


