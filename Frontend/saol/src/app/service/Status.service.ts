import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { globalEnum } from '../globalEnum';
import { Equipo } from '../models/Equipo';
import { Page } from '../models/Page';
import { Proyecto } from '../models/Proyecto';
import { Recompensa } from '../models/Recompensa';
import { Status } from '../models/Status';

@Injectable({ providedIn: "root" })
export class StatusService {

  constructor(private http: HttpClient) {

  }

  getAll(): Observable<Status[]> {
    return this.http.post<Status[]>(globalEnum.url + "status/all", {}).pipe(catchError(this.handleError));
  }

  getByClase(clase: string): Observable<Status[]> {
    return this.http.post<Status[]>(globalEnum.url + "status/clase", { "clase": clase}).pipe(catchError(this.handleError));
  }

  save(recompensa: Status): Observable<Status> {
    return this.http.post<Status>(globalEnum.url + "status/status", recompensa).pipe(catchError(this.handleError));
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
