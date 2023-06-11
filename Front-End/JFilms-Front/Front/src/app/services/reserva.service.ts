import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Reserva } from '../interfaces/reserva';

@Injectable({
  providedIn: 'root'
})
export class ReservaService {

  constructor(private http: HttpClient) {  }

  postReserva(reserva:  Reserva):Observable<Reserva>{
    return this.http.post<Reserva>('http://localhost:7070/api/reserva', reserva);
  }

}
