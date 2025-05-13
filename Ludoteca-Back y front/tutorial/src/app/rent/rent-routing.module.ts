import { RouterModule, Routes } from "@angular/router";
import { NgModule } from "@angular/core";
import { RentListComponent } from "./rent-list/rent-list.component";

const routes: Routes = [
    { path: '', component: RentListComponent }
];
@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class RentRoutingModule { }