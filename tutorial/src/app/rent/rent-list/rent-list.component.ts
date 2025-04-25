import { Component, OnInit } from '@angular/core';

import { MatTableDataSource } from '@angular/material/table';
import { PageEvent } from '@angular/material/paginator';
import { Pageable } from 'src/app/core/model/page/Pageable';
import { RentService } from '../rent.service';
import { Rent } from '../model/rent';



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
  items = [{
    value: 'search', icon: 'home', label: 'Título'
  }]
  dateSelected: Date = new Date();
  selected: any;


  constructor(
    private rentService: RentService
  ) {
  }
  ngOnInit(): void {

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
    else {
      pageable.pageSize = 5;
      pageable.pageNumber = 0;
    }

    this.rentService.getRents(pageable).subscribe(data => {
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
