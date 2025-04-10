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
  listObserver$: Array<Subscription>=[]
  constructor(private tracksService: TrackService) { 

  }
  ngOnInit(): void {
    // const {data}: any=(dataRaw as any).default
    // this.mockTrackList=data
  }

}
