import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Salas } from '../interfaces/salas';

@Injectable({
  providedIn: 'root'
})
export class SalasService {

  constructor(private http: HttpClient) { }


  getSalasByPelicula(id: number):  Observable<Salas[]>{
    return this.http.get<Salas[]>(`http://localhost:7070/api/salas/pelicula/${id}`);
  }


}
