import { RouterModule, Routes } from "@angular/router";
import { NgModule } from "@angular/core";
import { GameListComponent } from "./game-list/game-list.component";

const routes: Routes = [
    { path: '', component: GameListComponent }
];
@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class GameRoutingModule { }