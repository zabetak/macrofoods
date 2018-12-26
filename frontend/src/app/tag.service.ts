import { Injectable } from '@angular/core';
import { Tag } from './tag';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';
import { environment } from '../environments/environment';

@Injectable()
export class TagService {

  private baseUrl = environment.baseUrl;

  constructor(private http: HttpClient) { }

  searchTags(term: string): Observable<Tag[]> {
    if(!term.trim()){
      return of([]);
    }
    const options = { params: new HttpParams().set('tagName', term) };

    return this.http.get<Tag[]>(this.baseUrl+'/tags',options)
      .pipe(
        map(tags => tags.map(data => Tag.fromJSON(data))),
        catchError(
          (error: any, caught:Observable<Tag[]>) => {
            console.log(error);
            return of([]);
          })
      );
  }
}
