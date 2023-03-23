import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { globalEnum } from '../globalEnum';
import { Equipo } from '../models/Equipo';
import { Page } from '../models/Page';
import { Proyecto } from '../models/Proyecto';

@Injectable({ providedIn: "root" })
export class FileService {

  constructor(private http: HttpClient) {

  }

  save(files: any): Observable<string> {
    return this.http.post<string>(globalEnum.url + "file/file", files).pipe(catchError(this.handleError));
  }

  getFile(fname: string): Observable<Blob> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Accept': 'application/json'
    });

    return this.http.post<Blob>(globalEnum.url + "file/", { "nombre": fname }, { headers: headers, responseType: 'blob' as 'json' }).pipe(catchError(this.handleError));
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
