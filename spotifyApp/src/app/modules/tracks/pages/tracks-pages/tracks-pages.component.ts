import { Component, OnInit } from '@angular/core';
import { MediaPlayerComponent } from '../../../../shared/components/media-player/media-player.component';
import * as dataRaw from '../../../../data/tracks.json';
import { TrackModel } from '@core/models/tracks.model';
@Component({
  selector: 'app-tracks-pages',
  templateUrl: './tracks-pages.component.html',
  styleUrls: ['./tracks-pages.component.css']
})
export class TracksPagesComponent implements OnInit {
  mockTrackList:Array<TrackModel> = [


  ]

  ngOnInit(): void {
    const {data}: any=(dataRaw as any).default
    this.mockTrackList=data
  }

}
