import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../interfaces/user';
import { Token } from '../interfaces/token';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) {

   }

   login(name: string, password: string){
      return this.http.post<User>('http://localhost:7070/api/users/login', {name, password})
   }

   register(name: string, password: string,email: string, dni: string, tarjeta: string){
    return this.http.post<User>('http://localhost:7070/api/users/register', {email, name,password, dni, tarjeta})
 }

   obtenerDatosToken(token: string): Token | null {
    if (token != null && token.length > 0) {
      return JSON.parse(atob(token.split('.')[1]));
    }
    return null;
  }

}
