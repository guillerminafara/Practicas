import { Component, ElementRef, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { TrackModel } from '@core/models/tracks.model';
import { MultimediaService } from '@shared/services/multimedia.service';
import { Subscription } from 'rxjs';//pragramacion reactiva

@Component({
  selector: 'app-media-player',
  templateUrl: './media-player.component.html',
  styleUrls: ['./media-player.component.css']
})
export class MediaPlayerComponent implements OnInit, OnDestroy {
@ViewChild('progressBar') progressBar:ElementRef = new ElementRef('')
  mockCover!: TrackModel;
  // ={
  //   cover:'https://th.bing.com/th/id/OIP.zDVVcbNh6llKyCJ7g90JnAHaEC?rs=1&pid=ImgDetMain',
  //   album:'elefante',
  //   name:'elefantee',
  //   url:'http://localhost/track.mp3',
  //   _id: 1
  // }
  listObservers$: Array<Subscription> = []
  state: string='paused'
  constructor(public multimediaService: MultimediaService) {
    const observer1$= this.multimediaService.playerStatus$.subscribe(status=> this.state = status )
    this.listObservers$=[observer1$]
  }

  ngOnInit(): void {
    // this.multimediaService.trackInfo$.subscribe( res => {
    //   console.log("cancion a reproducir", res);
    //   this.mockCover= res
    // })
    // const observer1:Subscription=this.multimediaService.callback.subscribe(
    //   // (response:TrackModel)=>{

    //   // }
    //   (responseOK)=> {
    //     console.log('flujo correcto');
    //   },
    //   (responseFail)=>{
    //     console.log('flujo atascado');
    //   }
    // )


    
  }
  ngOnDestroy(): void {
    this.listObservers$.forEach(u => u.unsubscribe())
  }
  handlePosition(event: MouseEvent):void{
    const elNative: HTMLElement = this.progressBar.nativeElement
    const {clientX} =event
    const {x, width} = elNative.getBoundingClientRect()
    const clickX = clientX - x
    const percentageFromX = (clickX * 100)/ width
    this.multimediaService.seekAudio(percentageFromX) 
  }
}
