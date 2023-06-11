import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Butacas } from '../interfaces/butacas';

@Injectable({
  providedIn: 'root'
})
export class ButacasService {

  constructor(private http: HttpClient) { }


  getButacasBySala(id: number): Observable<Butacas[]>{
    return this.http.get<Butacas[]>(`http://localhost:7070/api/butacas/butacasPorSala/${id}`);
  }

  updateButaca(butaca: Butacas): Observable<Butacas>{
    return this.http.put<Butacas>(`http://localhost:7070/api/butacas/${butaca.id}`, butaca);
  }



}
