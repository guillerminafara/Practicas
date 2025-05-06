import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CoreModule } from "./core/core.module";
import { CategoryModule } from './category/category.module';
import { AuthorModule } from './author/author.module';
import { GameModule } from './game/game.module';
import { CustomerModule} from './customer/customer.module';
import { HttpClientModule } from '@angular/common/http';
import { RentModule } from './rent/rent.module';





@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    CoreModule,
    CategoryModule,
    AuthorModule,
    GameModule,
    CustomerModule,
    HttpClientModule,
    RentModule
],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
