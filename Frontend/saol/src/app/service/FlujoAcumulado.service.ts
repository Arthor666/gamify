import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { catchError, Observable, throwError } from "rxjs";
import { globalEnum } from "../globalEnum";
import { FlujoAcumulado } from "../models/FlujoAcumulado";

@Injectable({ providedIn: "root" })
export class FlujoAcumuladoService {
  constructor(private http: HttpClient) {

  }

  getByEquipoIdAndStatusId(idProyecto: number, idStatus: number): Observable<FlujoAcumulado[]> {
    return this.http.post<FlujoAcumulado[]>(globalEnum.url + "flujoAcumulado/equipo", { "idProyecto": idProyecto, "idStatus": idStatus }).pipe(catchError(this.handleError));
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
