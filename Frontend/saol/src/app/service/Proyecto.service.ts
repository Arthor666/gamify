import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { globalEnum } from '../globalEnum';
import { Equipo } from '../models/Equipo';
import { Page } from '../models/Page';
import { Proyecto } from '../models/Proyecto';

@Injectable({ providedIn: "root" })
export class ProyectoService {

  constructor(private http: HttpClient) {

  }

  getByProfesorId(id: number): Observable<Proyecto[]> {
    return this.http.post<Proyecto[]>(globalEnum.url + "proyecto/profesor", { "id": id }).pipe(catchError(this.handleError));
  }

  getByEquipoId(id: number): Observable<Proyecto> {
    return this.http.post<Proyecto>(globalEnum.url + "proyecto/equipo", { "id": id }).pipe(catchError(this.handleError));
  }

  getById(id: number): Observable<Proyecto> {
    return this.http.post<Proyecto>(globalEnum.url + "proyecto/id", {"id":id}).pipe(catchError(this.handleError));
  }

  getByNombreLike(nombre: string): Observable<Proyecto[]> {
    return this.http.post<Proyecto[]>(globalEnum.url + "equipo/nombre", { "nombre": nombre }).pipe(catchError(this.handleError));
  }

  getAll(): Observable<Proyecto[]> {
    return this.http.post<Proyecto[]>(globalEnum.url + "equipo/all", {} ).pipe(catchError(this.handleError));
  }

  getPageProyecto(page: number, size: number): Observable<Page<Proyecto>> {
    return this.http.post<Page<Proyecto>>(globalEnum.url + "equipo/proyectoAdmin", { "page": page, "size": size }).pipe(catchError(this.handleError));
  }

  save(proyecto: Proyecto): Observable<Proyecto> {
    return this.http.post<Proyecto>(globalEnum.url + "proyecto/proyecto", proyecto).pipe(catchError(this.handleError));
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
