import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ExampleComponent } from './example/example.component';

@NgModule({
  declarations: [//Declaraciones, componentes, directivas, pipes
    AppComponent,
    ExampleComponent
  ],
  imports: [// solo se importan modulos
    BrowserModule,
    AppRoutingModule
  ],
  
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
