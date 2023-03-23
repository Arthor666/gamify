import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { globalEnum } from '../globalEnum';
import { Equipo } from '../models/Equipo';

@Injectable({ providedIn: "root" })
export class EquipoService {
  

  constructor(private http: HttpClient) {

  }

  saveEquipo(equipo: Equipo): Observable<Equipo>{
    return this.http.post<Equipo>(globalEnum.url + "equipo/equipo", equipo).pipe(catchError(this.handleError));
  }

  getCountProyectos(id: number): Observable<any> {
    return this.http.post<Equipo>(globalEnum.url + "equipo/numProyectos", {"id":id}).pipe(catchError(this.handleError));
  }

  getByUsuarioId(id: number): Observable<Equipo[]> {
    return this.http.post<Equipo[]>(globalEnum.url + "equipo/user", {"id":id}).pipe(catchError(this.handleError));
  }

  getByNombreLike(nombre: string): Observable<Equipo[]> {
    return this.http.post<Equipo[]>(globalEnum.url + "equipo/nombre", {"nombre":nombre}).pipe(catchError(this.handleError));
  }

  getAll(): Observable<Equipo[]> {
    return this.http.post<Equipo[]>(globalEnum.url + "equipo/all", {}).pipe(catchError(this.handleError));
  }

  getById(id: number): Observable<Equipo> {
    return this.http.post<Equipo>(globalEnum.url + "equipo/id", {"id":id}).pipe(catchError(this.handleError));
  }

  getByProyectoId(id: number): Observable<Equipo> {
    return this.http.post<Equipo>(globalEnum.url + "equipo/poryecto", {"id":id}).pipe(catchError(this.handleError));
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
