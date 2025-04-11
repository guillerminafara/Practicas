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
    this.loadDataAll()
    // this.loadDataRandom()

  }
  async loadDataAll(): Promise<any> {
    // this.trackService.getAllTracks$()
    //   .subscribe((response: TrackModel[] )=> {
    //     console.log("----->>", response)
    //     this.tracksTrending= response
    //   }
    //   )

    this.tracksTrending= await this.trackService.getAllTracks$()
      .toPromise()
     this.tracksRandom = await this.trackService.getAllRandom$()
      .toPromise()
    // .then(res => { })
    // .catch(err => {
    //   console.log('error en conexion');
    // })

  }
  loadDataRandom():void {
    // this.trackService.getAllRandom$()
    //   .subscribe((response: TrackModel[]) => {
    //     console.log("----->>", response)
    //     this.tracksRandom = response
    //   }, err => {
    //alert('error de conexiones')
    //     console.log('error en conexion');
    //   }
    //   )
  }
  ngOnDestroy(): void {

  }
}


