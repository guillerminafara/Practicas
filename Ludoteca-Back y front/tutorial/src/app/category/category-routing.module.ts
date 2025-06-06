import { RouterModule, Routes } from "@angular/router";
import { NgModule } from "@angular/core";
import { CategoryListComponent } from "./category-list/category-list.component";

const routes: Routes = [
    { path: '', component: CategoryListComponent }
];
@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class CategoryRoutingModule { }