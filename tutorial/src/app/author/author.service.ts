import { Injectable } from '@angular/core';
import { Pageable } from '../core/model/page/Pageable';
import { Observable, of } from 'rxjs';
import { AuthorPage } from './model/AuthorPage';
import { AUTHOR_DATA } from './model/mock-authors';
import { Author } from './model/Author';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthorService {

  constructor(private http: HttpClient) { }
  getAuthors(pageable: Pageable): Observable<AuthorPage> {
    return this.http.post<AuthorPage>('http://localhost:8080/author', { pageable: pageable });

  }

  SaveAuthor(authos: Author): Observable<void> {
    let url = 'http://localhost:8080/author/' + authos.id;
    if(authos.id!=null) url+= '/'+ authos.id;
    return this.http.put<void>(url,authos);
  }

  deleteAuthor(idAuthor: number): Observable<void> {
    return this.http.delete<void>('http://localhost/8080/author/'+idAuthor);
  }
}
