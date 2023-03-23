import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { globalEnum } from '../globalEnum';
import { Usuario } from '../models/Usuario';
import { UsuarioRecompensa } from '../models/UsuarioRecompensa';


@Injectable({ providedIn: "root" })
export class UsuarioRecompensaService {
  constructor(private http: HttpClient) {

  }
  save(ur: UsuarioRecompensa): Observable<UsuarioRecompensa> {
    return this.http.post<UsuarioRecompensa>(globalEnum.url + "usuarioRecompensa/usuarioRecompensa", ur).pipe(catchError(this.handleError));
  }

  getAll(): Observable<UsuarioRecompensa[]> {
    return this.http.post<UsuarioRecompensa[]>(globalEnum.url + "usuarioRecompensa/all", {}).pipe(catchError(this.handleError));
  }

  getAllByUsuarioId(id: number): Observable<UsuarioRecompensa[]> {
    return this.http.post<UsuarioRecompensa[]>(globalEnum.url + "usuarioRecompensa/usuario" , {"id":id}).pipe(catchError(this.handleError));
  }

  getByNombreLike(nombre: string): Observable<UsuarioRecompensa[]> {
    return this.http.post<UsuarioRecompensa[]>(globalEnum.url + "usuarioRecompensa/nombre", {"nombre":nombre}).pipe(catchError(this.handleError));
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
