import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Category } from './model/Category';
import { HttpClient } from '@angular/common/http';

let url = 'http://localhost:8080/category'
@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  constructor(
    private http: HttpClient
  ) { }

  getCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(url);
  }

  saveCategory(category: Category): Observable<Category> {
    if (category.id != null) url += '/' + category.id;
    return this.http.put<Category>(url, category);
  }
  
  deleteCategory(idCategoriy: number): Observable<any> {
    return this.http.delete(url + '/' + idCategoriy);
  }
}
