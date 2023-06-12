import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginService } from '../services/login.service';
import { Router } from '@angular/router';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  constructor(private loginService: LoginService, private router: Router) { }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {

    if (this.loginService.obtenerDatosToken(localStorage.getItem('token')!) != null) {

        const token = localStorage.getItem('token');
        if (token != null && token.length > 0) {
          const clone = request.clone({
            headers: request.headers.set("Authorization", "Bearer " + token)
          });
          console.log("TokenInterceptor: " + clone.headers.get("Authorization"));
          return next.handle(clone);

      }


    } else {
      this.router.navigateByUrl('')
    }
    return next.handle(request);
  }
}
