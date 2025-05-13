import { LOCALE_ID, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {MatPaginatorIntl, PageEvent} from "@angular/material/paginator";
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CoreModule } from "./core/core.module";
import { CategoryModule } from './category/category.module';
import { AuthorModule } from './author/author.module';
import { GameModule } from './game/game.module';
import { CustomerModule } from './customer/customer.module';
import { HttpClientModule } from '@angular/common/http';
import { RentModule } from './rent/rent.module';
import {registerLocaleData } from '@angular/common';
import es from '@angular/common/locales/es';

registerLocaleData(es);
@NgModule({
  declarations: [
    AppComponent

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
  providers: [{provide: LOCALE_ID, useValue:'es-ES'}],
  bootstrap: [AppComponent]
})
export class AppModule { }
