import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Usuario } from '../interfaces/usuario';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  constructor(private http : HttpClient) { }

  getUsuario(username : string):Observable<Usuario>{
    return this.http.get<Usuario>(`http://localhost:7070/api/users/${username}`)
  }

  updateUsuario(usuario:Usuario):Observable<Usuario>{
    return this.http.put<Usuario>(`http://localhost:7070/api/users/me/${usuario.uuid}`, usuario)
  }
  
}

