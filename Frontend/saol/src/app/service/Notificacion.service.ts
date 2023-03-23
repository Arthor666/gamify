import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { catchError, Observable, throwError } from "rxjs";
import { globalEnum } from "../globalEnum";
import { Notificacion } from "../models/Notificacion";

@Injectable({ providedIn: "root" })
export class NotificacionService {

  constructor(private http: HttpClient) { }

  getByUsuarioId(id: number): Observable<Notificacion[]> {
    return this.http.post<Notificacion[]>(globalEnum.url + "notificacion/user", {"id":id}).pipe(catchError(this.handleError));
  }

  countByUsuarioId(id: number): Observable<any> {
    return this.http.post<any>(globalEnum.url+"notificacion/count", { "id": id }).pipe(catchError(this.handleError));
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

