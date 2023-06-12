import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Reserva, ReservaDto } from '../interfaces/reserva';

@Injectable({
  providedIn: 'root'
})
export class ReservaService {

  constructor(private http: HttpClient) {  }

  
  getReservas(uuid: string):Observable<ReservaDto[]>{
    return this.http.get<ReservaDto[]>(`http://localhost:7070/api/reserva/lista/${uuid}`);
  }



  postReserva(reserva:  Reserva):Observable<Reserva>{
    return this.http.post<Reserva>('http://localhost:7070/api/reserva', reserva);
  }

}
