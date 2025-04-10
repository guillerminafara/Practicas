import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TracksRoutingModule } from './tracks-routing.module';
import { TracksPagesComponent } from './pages/tracks-pages/tracks-pages.component';
import { SharedModule } from '@shared/shared.module';



@NgModule({
  declarations: [
    TracksPagesComponent
  ],
  imports: [
    CommonModule,
    TracksRoutingModule,
    SharedModule,
 
  ]
})
export class TracksModule { }
