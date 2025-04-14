import { Component, OnInit } from '@angular/core';
import { TrackModel } from '@core/models/tracks.model';
import { TrackService } from '@modules/tracks/services/track.service';

@Component({
  selector: 'app-favorites-pages',
  templateUrl: './favorites-pages.component.html',
  styleUrls: ['./favorites-pages.component.css']
})
export class FavoritesPagesComponent implements OnInit {
   tracksTrending: Array<TrackModel> = []

  // constructor(private trackService: TrackService) {

  // }
  ngOnInit(): void {
    // this.loadDataAll()
  }

  // async loadDataAll(): Promise<any> {
  //   this.tracksTrending = await this.trackService.getAllTracks$().toPromise()
  // }
}
