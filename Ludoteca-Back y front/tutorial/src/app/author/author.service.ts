import { Injectable } from '@angular/core';
import { Pageable } from '../core/model/page/Pageable';
import { Observable, of } from 'rxjs';
import { AuthorPage } from './model/AuthorPage';
import { Author } from './model/Author';
import { HttpClient } from '@angular/common/http';

let url = 'http://localhost:8080/author';
@Injectable({
  providedIn: 'root'
})
export class AuthorService {

  constructor(private http: HttpClient) { }
  getAuthors(pageable: Pageable): Observable<AuthorPage> {
    return this.http.post<AuthorPage>(url, { pageable: pageable });

  }

  getAllAuthors(): Observable<Author[]> {
    return this.http.get<Author[]>(url);
  }

  saveAuthor(authos: Author): Observable<void> {
    if (authos.id != null) url += '/' + authos.id;
    return this.http.put<void>(url, authos);
  }

  deleteAuthor(idAuthor: number): Observable<void> {
    return this.http.delete<void>(url + idAuthor);
  }
}
