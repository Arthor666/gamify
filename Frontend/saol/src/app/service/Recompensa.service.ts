import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { globalEnum } from '../globalEnum';
import { Equipo } from '../models/Equipo';
import { Page } from '../models/Page';
import { Proyecto } from '../models/Proyecto';
import { Recompensa } from '../models/Recompensa';

@Injectable({ providedIn: "root" })
export class RecompensaService {

  constructor(private http: HttpClient) {

  }

  getCommonsAndProfesorId(id:number): Observable<Recompensa[]> {
    return this.http.post<Recompensa[]>(globalEnum.url + "recompensa/profesor/commons", { "id": id }).pipe(catchError(this.handleError));
  }

  getCommons(): Observable<Recompensa[]> {
    return this.http.post<Recompensa[]>(globalEnum.url + "recompensa/commons", {} ).pipe(catchError(this.handleError));
  }

  getByProfesorId(id: number): Observable<Recompensa[]> {
    return this.http.post<Recompensa[]>(globalEnum.url + "recompensa/profesor", { "id": id }).pipe(catchError(this.handleError));
  }

  getByEquipoId(id: number): Observable<Recompensa> {
    return this.http.post<Recompensa>(globalEnum.url + "recompensa/equipo", {"id":id}).pipe(catchError(this.handleError));
  }
  getAll(): Observable<Recompensa[]> {
    return this.http.post<Recompensa[]>(globalEnum.url + "recompensa/all", {}).pipe(catchError(this.handleError));
  }

  getByNombreLike(nombre: string): Observable<Recompensa[]> {
    return this.http.post<Recompensa[]>(globalEnum.url + "recompensa/nombre", {"nombre":nombre}).pipe(catchError(this.handleError));
  }

  save(recompensa: Recompensa): Observable<Recompensa> {
    return this.http.post<Recompensa>(globalEnum.url + "recompensa/recompensa", recompensa).pipe(catchError(this.handleError));
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
