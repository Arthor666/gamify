import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { globalEnum } from '../globalEnum';
import { Equipo } from '../models/Equipo';
import { Grupo } from '../models/Grupo';
import { HistoriaUsuario } from '../models/HistoriaUsuario';

@Injectable({ providedIn: "root" })
export class HistoriaUsuarioService {


  getByPuntosByEquipoId(id: number):Observable<any> {
    return this.http.post<any>(globalEnum.url + "historia/equipo/conteoPuntos", { id: id }).pipe(catchError(this.handleError));
  }

  getByTareaId(id: number): Observable<HistoriaUsuario> {
    return this.http.post<HistoriaUsuario>(globalEnum.url + "historia/tarea", { id: id }).pipe(catchError(this.handleError));
  }


  constructor(private http: HttpClient) {

  }

  getByEquipoId(id: number): Observable<HistoriaUsuario[]> {
    return this.http.post<HistoriaUsuario[]>(globalEnum.url + "historia/equipo", { id: id}).pipe(catchError(this.handleError));
  }

  getByNombreLike(nombre: string): Observable<HistoriaUsuario[]> {
    return this.http.post<HistoriaUsuario[]>(globalEnum.url + "historia/nombre", { nombre: nombre }).pipe(catchError(this.handleError));
  }

  save(historia: HistoriaUsuario): Observable<HistoriaUsuario> {
    return this.http.post<HistoriaUsuario>(globalEnum.url + "historia/historia", historia).pipe(catchError(this.handleError));
  }

  private handleError(error: HttpErrorResponse) {
    if (error.status === 0) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong.
      console.error(
        `Backend returned code ${error.status}, body was: `, error.error);
    }
    // Return an observable with a user-facing error message.
    return throwError(() => new Error('Something bad happened; please try again later.'));
  }

}
