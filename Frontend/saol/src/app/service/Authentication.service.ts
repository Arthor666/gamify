import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { globalEnum } from '../globalEnum';
import { AuthenticationRequestObject } from '../models/AuthenticationRequestObject';
import { Clases } from '../models/Clases';
import { Equipo } from '../models/Equipo';
import { Page } from '../models/Page';
import { Proyecto } from '../models/Proyecto';
import { Recompensa } from '../models/Recompensa';
import { Status } from '../models/Status';

@Injectable({ providedIn: "root" })
export class AuthenticationService {

  constructor(private http: HttpClient, private snackBar: MatSnackBar) {

  }

  authenticate(authObject: AuthenticationRequestObject): Observable<any> {
    return this.http.post<any>(globalEnum.url + "auth/auth", authObject).pipe(catchError(this.handleError.bind(this)));
  }

  private handleError(error: HttpErrorResponse) {
    if (error.status === 403) {
      // A client-side or network error occurred. Handle it accordingly.
      this.snackBar.open("Correo o contraseña incorrectos","ok");
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong.
      console.error(`Backend returned code ${error.status}, body was: `, error.error);
    }
    // Return an observable with a user-facing error message.
    return throwError(() => new Error('Something bad happened; please try again later.'));
  }
}
