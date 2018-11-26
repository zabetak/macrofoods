import { Injectable } from '@angular/core';
import { Food } from './food';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';
import { environment } from '../environments/environment';

@Injectable()

export class FoodsService {

  private baseUrl = environment.baseUrl;

  constructor(
  private http: HttpClient) { }

  searchFoods(term: string): Observable<Food[]> {
    if (!term.trim()) {
      // if not search term, return empty hero array.
      return of([]);
    }
    // Add safe, URL encoded search parameter
    const options = { params: new HttpParams().set('description', term) };

    return this.http.get<Food[]>(this.baseUrl+'/foods',options)
      .pipe(
        tap(foods => foods.forEach(food => food.qty = 100)),
        catchError(this.handleError('getHeroes', []))
      );
  }

  private log(message: string) {
    // Do nothing
  }

  /**
   * Handle Http operation that failed.
   * Let the app continue.
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}
