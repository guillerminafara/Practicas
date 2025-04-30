import { Component, Inject, OnInit } from '@angular/core';
import { GameService } from '../../game/game.service';
import { RentService } from '../rent.service';
import { CustomerService } from 'src/app/customer/customer.service';
import { Game } from 'src/app/game/model/Game';
import { Customer } from 'src/app/customer/model/Customer';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Rent } from '../model/rent';

@Component({
  selector: 'app-rent-edit',
  templateUrl: './rent-edit.component.html',
  styleUrls: ['./rent-edit.component.scss']
})
export class RentEditComponent implements OnInit {
  games: Game[];
  customer: Customer[];
  rent: Rent;
  constructor(private rentService: RentService,
    private gameService: GameService,
    private customerService: CustomerService,
    @Inject(MAT_DIALOG_DATA) public data: any,
  ) {

  }
  ngOnInit(): void {
    // if (this.data.rent !=null) {
    //   this.rent = Object.assign({}, this.data.rent);
    // } else {
    //   this.rent = new Rent();
    // }

    // this.gameService.getGames().subscribe(
    //   games => {
    //     this.games = games
    //     if (this.rent.game != null) {
    //       let gameFilter: Game[] = games.filter(ga => ga.id == this.data.rent.game.id);
    //       if(gameFilter != null){
    //         this.rent.game= gameFilter[0];
    //       }
    //     }

    //   }
    // );
    // this.customerService.getCustomer().subscribe(
    //   customers => { this.customer = customers


    //    }
    //)
  }

}
