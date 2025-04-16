import { Injectable } from '@angular/core';
import { Observable, ObservedValueOf, of } from 'rxjs';
import { Category } from './model/Category';
import { CATEGORY_DATA } from './model/mock-categories';
import { MatDialog } from '@angular/material/dialog';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  


  constructor(
    private http:HttpClient
  ) { }
  getCategories(): Observable<Category[]> {
    return of(CATEGORY_DATA);
  }
  saveCategory(category: Category): Observable<Category> {
    return of(null);
  }
  deleteCategory(idCategoriy: number): Observable<any> {
    return of(null);
  }
}
