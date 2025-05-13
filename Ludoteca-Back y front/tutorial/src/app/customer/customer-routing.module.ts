import { RouterModule, Routes } from "@angular/router";
import { NgModule } from "@angular/core";
import { CustomerListComponent } from "./customer-list/customer-list.component";

const routes: Routes = [
    { path: '', component: CustomerListComponent }
];
@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class CustomerRoutingModule { }