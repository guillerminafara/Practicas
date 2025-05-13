import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CategoryListComponent } from './category/category-list/category-list.component';
import { AuthorListComponent } from './author/author-list/author-list.component';
import { GameListComponent } from './game/game-list/game-list.component';
import { CustomerListComponent } from './customer/customer-list/customer-list.component';
import { RentListComponent } from './rent/rent-list/rent-list.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: '/games', pathMatch: 'full'
  },
  {
    path: 'categories',
    loadChildren: () => import('./category/category-routing.module').then(m => m.CategoryRoutingModule)
  },
  {
    path: 'customer',
    loadChildren: () => import('./customer/customer-routing.module').then(m => m.CustomerRoutingModule)
  },
  {
    path: 'authors',
    loadChildren: () => import('./author/author-routing.module').then(m => m.AuthorRoutingModule)
  },
  {
    path: 'games',
    loadChildren: () => import('./game/game-routing.module').then(m => m.GameRoutingModule)
  },
  { path: 'rent', component: RentListComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
