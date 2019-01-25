import { Injectable } from '@angular/core';
import { User } from './user';
import { JWT } from './jwt';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { catchError, map, tap, shareReplay } from 'rxjs/operators';
import { environment } from '../environments/environment';


@Injectable()
export class SessionService {
  private baseUrl = environment.baseUrl;

  constructor(private http: HttpClient) { }

  login(user:User): Observable<JWT> {
    // Send a request to the server and try to authenticate
    // If valid user store the user and its token here
    let options = {
      headers: new HttpHeaders({'Content-Type':'application/json; charset=utf-8'})
    };
    return this.http.post<any>(this.baseUrl+'/tokens', user, options)
      .pipe(
        tap(res => this.setSession(res, user)),
        shareReplay(3)
      );
  }

  private setSession(response, user:User){
    if(response != null){
      let jwt:JWT = new JWT();
      jwt.token = response.token;
      jwt.expiresAt = new Date().getTime()+Number(response.expiresIn);
      user.jwt=jwt;
      localStorage.setItem('currentUser', JSON.stringify(user));
      console.log("Authentication success for user "+user.username);
    } else {
      console.log("Authentication failure for user "+user.username);
    }
  }

  getValidTokenOrNull(): string {
    let u:User = JSON.parse(localStorage.getItem('currentUser'));
    if(u != null)
      return u.jwt.token;
    return null;
  }

  isTokenValid(): boolean {
    // Server side verification? Nahh probably doesn't worth it
    let u:User = JSON.parse(localStorage.getItem('currentUser'));
    if(u==null || u.jwt==null)
      return false;
    return (new Date().getTime()) < u.jwt.expiresAt;
  }


  logout(){
    localStorage.removeItem('currentUser');
  }

}
