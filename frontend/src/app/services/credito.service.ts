import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Credito } from '../models/credito.model';
import { environment } from 'environments/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class CreditoService {
  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) { }

  getCreditosByNumeroNfse(numeroNfse: string): Observable<Credito[]> {
    return this.http.get<Credito[]>(`${this.apiUrl}/api/creditos/${numeroNfse}`);
  }

  getCreditoByNumeroCredito(numeroCredito: string): Observable<Credito> {
    return this.http.get<Credito>(`${this.apiUrl}/api/creditos/credito/${numeroCredito}`);
  }

  getAllCreditos(): Observable<Credito[]> {
    return this.http.get<Credito[]>(`${this.apiUrl}/api/creditos`);
  }
}

