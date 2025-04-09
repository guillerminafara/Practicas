import { NgModule, Component } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomePageComponent } from '@modules/home/pages/home-page/home-page.component';

//capaces de destruir componentes
const routes: Routes = [
  {
     path: '',// localhost:4200/
    loadChildren:() => import('./modules/auth/auth.module').then(m => m.AuthModule)

  },{
    path:'',
    component:HomePageComponent,
    loadChildren:() => import('./modules/home/home.module').then(m => m.HomeModule)
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
