import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { WebsocketServiceService } from './websocket-service.service';
import { Observable } from 'rxjs';
import { Butacas } from '../interfaces/butacas';

@Injectable({
  providedIn: 'root'
})
export class ButacasService {

  constructor(private http: HttpClient, 
    private websocketService: WebsocketServiceService) { 
      
  
    }


  getButacasBySala(id: number): Observable<Butacas[]>{
    return this.http.get<Butacas[]>(`http://localhost:7070/api/butacas/butacasPorSala/${id}`);
  }

  updateButaca(butaca: Butacas): Observable<Butacas>{
    return this.http.put<Butacas>(`http://localhost:7070/api/butacas/${butaca.id}`, butaca);
  }

  getButacaByFilaAndNumero(fila: number, numero: number): Observable<Butacas>{
    return this.http.get<Butacas>(`http://localhost:7070/api/butacas/especifica/${fila}/${numero}`);
  }



}

