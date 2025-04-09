import { Component, OnInit } from '@angular/core';
import { TrackModel } from '@core/models/tracks.model';

@Component({
  selector: 'app-media-player',
  templateUrl: './media-player.component.html',
  styleUrls: ['./media-player.component.css']
})
export class MediaPlayerComponent implements OnInit {

  mockCover: TrackModel={
    cover:'https://th.bing.com/th/id/OIP.zDVVcbNh6llKyCJ7g90JnAHaEC?rs=1&pid=ImgDetMain',
    album:'elefante',
    name:'elefantee',
    url:'http://localhost/track.mp3',
    _id: 1
  }
  constructor(){

  }
  
  ngOnInit(): void {
    
  }

}
