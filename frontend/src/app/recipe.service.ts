import { Injectable } from '@angular/core';
import { Recipe } from './recipe';
import { Image } from './image';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';
import { environment } from '../environments/environment';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json; charset=utf-8' })
};

@Injectable()
export class RecipeService {
  private baseUrl = environment.baseUrl;

  constructor(private http: HttpClient) { }

  getRecipe(id: number): Observable<Recipe> {
    return this.http.get<Recipe>(this.baseUrl+'/recipes/'+id, httpOptions)
      .pipe(
        map(data => Recipe.fromJSON(data)),
        catchError((error: any, caught:Observable<Recipe>) => {
          console.log(error);
          return of(new Recipe());
        })
      );
  }

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

  loadImage(image: Image): Observable<Image> {
    return this.http.get<Image>(this.baseUrl+'/images/'+image.id, httpOptions)
      .pipe(
        catchError(
          (error: any, caught:Observable<Image>) => {
            console.log(error);
            return of(new Image());
          }
        )
      );
  }

  topRecipes(): Observable<Recipe[]> {
    // Add safe, URL encoded search parameter
    return this.http.get<Recipe[]>(this.baseUrl+'/recipes/top', httpOptions)
      .pipe(
        map(recipes => recipes.map(data => Recipe.fromJSON(data))),
        catchError((error: any, caught:Observable<Recipe[]>) => {
          console.log(error);
          return of([]);
        })
      );
  }
}
