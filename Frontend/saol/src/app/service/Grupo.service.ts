import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { globalEnum } from '../globalEnum';
import { Equipo } from '../models/Equipo';
import { Grupo } from '../models/Grupo';

@Injectable({ providedIn: "root" })
export class GrupoService {


  constructor(private http: HttpClient) {

  }
  getByProfesorId(id: number): Observable<Grupo[]> {
    return this.http.post<Grupo[]>(globalEnum.url + "grupo/profesor", { "id": id }).pipe(catchError(this.handleError));

  }

  getByAlumnoId(id: number): Observable<Grupo[]> {
    return this.http.post<Grupo[]>(globalEnum.url + "grupo/alumno", { "id": id }).pipe(catchError(this.handleError));
  }

  getByNombreLike(nombre: string): Observable<Grupo[]> {
    return this.http.post<Grupo[]>(globalEnum.url + "grupo/nombre", {"nombre":nombre}).pipe(catchError(this.handleError));
  }

  save(grupo: Grupo): Observable<Grupo> {
    return this.http.post<Grupo>(globalEnum.url + "grupo/grupo", grupo).pipe(catchError(this.handleError));
  }

  inscribirAlumno(id: number, codigo: string): Observable<Grupo> {
    return this.http.post<Grupo>(globalEnum.url + "grupo/alumno/codigo", {"id":id,"codigo":codigo}).pipe(catchError(this.handleError));
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
