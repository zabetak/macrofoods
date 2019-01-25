import { Component, OnInit } from '@angular/core';
import { SessionService } from '../session.service';
import { User } from '../user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user:User = new User();
  constructor(private sessionService:SessionService) { }

  ngOnInit() {

  }

  isLoggedIn(): boolean {
    return this.sessionService.isTokenValid();
  }

  login(){
    this.sessionService.login(this.user).subscribe();
  }


  logout(){
    this.sessionService.logout();
  }

}
