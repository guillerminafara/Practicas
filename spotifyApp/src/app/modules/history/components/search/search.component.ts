import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { catchError } from 'rxjs/operators';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  @Output() callbackData: EventEmitter<any> = new EventEmitter()

  src: string = ''
  constructor() { }
  ngOnInit(): void {

  }
  callSearch(term: string): void {
    if (term.length >= 2) {
      this.callbackData.emit(term)
      console.log("", term);
    }

  }
}
