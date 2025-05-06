import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CategoryListComponent } from './category/category-list/category-list.component';
import { AuthorListComponent } from './author/author-list/author-list.component';
import { GameListComponent } from './game/game-list/game-list.component';
import { CustomerListComponent } from './customer/customer-list/customer-list.component';
import { RentListComponent } from './rent/rent-list/rent-list.component';

const routes: Routes = [
  { path: '', redirectTo: '/games', pathMatch: 'full'},
  {path: 'categories', component: CategoryListComponent },
  {path: 'customer', component: CustomerListComponent},
  {path: 'authors', component: AuthorListComponent},
  { path: 'games', component: GameListComponent },
  { path: 'rent', component: RentListComponent },

];

@NgModule({
  
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
