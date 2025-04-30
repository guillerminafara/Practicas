import { Component, OnInit } from '@angular/core';

import { MatTableDataSource } from '@angular/material/table';
import { PageEvent } from '@angular/material/paginator';
import { Pageable } from 'src/app/core/model/page/Pageable';
import { RentService } from '../rent.service';
import { Rent } from '../model/rent';
import { Game } from 'src/app/game/model/Game';
import { GameService } from '../../game/game.service';
import { CustomerService } from 'src/app/customer/customer.service';
import { Customer } from 'src/app/customer/model/Customer';



@Component({
  selector: 'app-rent-list',
  templateUrl: './rent-list.component.html',
  styleUrls: ['./rent-list.component.scss']
})
export class RentListComponent implements OnInit {

  dataSource = new MatTableDataSource<Rent>();
  pageNumber: number = 0;
  pageSize: number = 5;
  totalElements: number = 0;
  displayedColumns: string[] = ['id', 'name-game', 'name-client', 'initial-date', 'end-date', 'action'];
  // icon: any;
  // items = [{
  //   value: 'search', icon: 'home', label: 'Título'
  // }]
  games: Game[];
  customer:Customer[];
  filterGame:Game;
  filterCustomer:Customer;
  dateSelected: Date = new Date();


  constructor(
    private rentService: RentService,
    private gameService: GameService,
    private customerService: CustomerService
  ) {


  }
  ngOnInit(): void {
    this.loadPage();
    this.gameService.getGames().subscribe(
      games => this.games=games
    );
    this.customerService.getCustomer().subscribe(
      customers => this.customer=customers
    )
  }

  onCleanFilter():void{
    this.filterGame=null;
    this.filterCustomer=null;
    this.onSearch();
  }

  onSearch(): void{
   this.loadPage()
   
  }

  loadPage(event?: PageEvent) {
    let pageable: Pageable = {
      pageNumber: this.pageNumber,
      pageSize: this.pageSize,
      sort: [{
        property: 'id',
        direction: 'ASC'
      }]
    }

    if (event != null) {
      pageable.pageSize = event.pageSize
      pageable.pageNumber = event.pageIndex;
    }
   

    const request={
      pageable:pageable,
      gameId : this.filterGame != null ? this.filterGame.id : null,
      customerId : this.filterCustomer != null ? this.filterGame.id :null,
      
    };
    this.rentService.getRents(request).subscribe((data) => {
      this.dataSource.data = data.content;
      this.pageNumber = data.pageable.pageNumber;
      this.pageSize = data.pageable.pageSize;
      this.totalElements = data.totalElements
    }
    );
   
  }
  createRent() { }

  deleteRent() {

  }

}
