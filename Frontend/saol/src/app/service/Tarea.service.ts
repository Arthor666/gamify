import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { globalEnum } from '../globalEnum';
import { Tarea } from '../models/Tarea';


@Injectable({ providedIn: "root" })
export class TareaService {
  updateStatus(t: Tarea):Observable<Tarea> {
    return this.http.put<Tarea>(globalEnum.url + "tarea/tarea/status", t).pipe(catchError(this.handleError));
  }  

  constructor(private http: HttpClient) {

  }

  getByEquipoId(id: number) : Observable<Tarea[]>{
    return this.http.post<Tarea[]>(globalEnum.url + "tarea/equipo", { "id": id }).pipe(catchError(this.handleError));
  }

  getTareasByUsuarioId(id: number): Observable<Tarea[]> {
    return this.http.post<Tarea[]>(globalEnum.url + "tarea/usuario" , { "id":id }).pipe(catchError(this.handleError));
  }

  getAll(): Observable<Tarea[]> {
    return this.http.post<Tarea[]>(globalEnum.url + "tarea/all", {}).pipe(catchError(this.handleError));
  }

  getByNombreLike(nombre: string): Observable<Tarea[]> {
    return this.http.post<Tarea[]>(globalEnum.url + "tarea/nombre", { "nombre": nombre }).pipe(catchError(this.handleError));
  }

  getTareasByAutorIdAndEquipoId(idUsuario: number, idEquipo: number): Observable<Tarea[]> {
    return this.http.post<Tarea[]>(globalEnum.url + "tarea/autor/equipo", { "idUsuario": idUsuario, "idEquipo": idEquipo }).pipe(catchError(this.handleError));
  }

  save(recompensa: Tarea): Observable<Tarea> {
    return this.http.post<Tarea>(globalEnum.url + "tarea/tarea", recompensa).pipe(catchError(this.handleError));
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
