import { Injectable } from '@angular/core';
import {
  HttpEvent, HttpInterceptor, HttpHandler, HttpRequest
} from '@angular/common/http';

import { SessionService } from '../session.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private ses: SessionService) {}

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    // Get the auth token from the service.
    const authToken = this.ses.getValidTokenOrNull();

    const authReq = req.clone({
      headers: req.headers.set('Authorization', authToken)
    });
    
    // send cloned request with header to the next handler.
    return next.handle(authReq);
  }
}


/*
Copyright 2017-2018 Google Inc. All Rights Reserved.
Use of this source code is governed by an MIT-style license that
can be found in the LICENSE file at http://angular.io/license
*/
