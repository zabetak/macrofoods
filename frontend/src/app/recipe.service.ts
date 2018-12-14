import { Injectable } from '@angular/core';
import { Recipe } from './recipe';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';
import { environment } from '../environments/environment';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class RecipeService {
  private baseUrl = environment.baseUrl;

  constructor(private http: HttpClient) { }

  save(recipe: Recipe){
    this.http.post<Recipe>(this.baseUrl+'/recipes/save', recipe, httpOptions)
      .pipe(
        catchError(
          (error: any, caught:Observable<Recipe>) => {
            console.log(error);
            return of([]);
          })
    ).subscribe( u => {console.log(u);});
  }
}
