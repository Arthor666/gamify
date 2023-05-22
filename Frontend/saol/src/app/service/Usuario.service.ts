import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { globalEnum } from '../globalEnum';
import { Usuario } from '../models/Usuario';
import { MatSnackBar } from '@angular/material/snack-bar';


@Injectable({ providedIn:"root" })
export class UsuarioService {
  getById(id: number): Observable<Usuario> {
    return this.http.post<Usuario>(globalEnum.url + "user/id", {"id":id}).pipe(catchError(this.handleError));
  }
  constructor(private http: HttpClient,private snackBar: MatSnackBar) {

  }

  getUserByNameAndGrupoIdAndNotInEquipo(name: string, id: number): Observable<Usuario[]> {
    return this.http.post<Usuario[]>(globalEnum.url + "user/name/grupo", {"name":name,"id":id}).pipe(catchError(this.handleError));
  }

  getAll(): Observable<Usuario[]> {
    return this.http.post<Usuario[]>(globalEnum.url + "user/all", {}).pipe(catchError(this.handleError));
  }

  getUserByName(name: any): Observable<Usuario[]>{
    return this.http.post<Usuario[]>(globalEnum.url + "user/name", name).pipe(catchError(this.handleError));
  }
  getUserByNameAndAvailable(name: string): Observable<Usuario[]> {
    return this.http.post<Usuario[]>(globalEnum.url + "user/nameAvailable", {"nombre":name}).pipe(catchError(this.handleError));
  }
  getUserByEquipoId(id: number):Observable<Usuario[]> {
    return this.http.post<Usuario[]>(globalEnum.url + "user/equipo", {"id":id}).pipe(catchError(this.handleError));
  }

  getUserByProyectoId(id: number): Observable<Usuario[]> {
    return this.http.post<Usuario[]>(globalEnum.url + "user/proyecto", { "id": id }).pipe(catchError(this.handleError));
  }

  save(usuario: Usuario): Observable<Usuario> {
    return this.http.post<Usuario>(globalEnum.url + "user/user", usuario).pipe(catchError(this.handleError.bind(this)));
  }

  update(usuario: Usuario, newPassword: string, currentPassword: string): Observable<Usuario> {
    return this.http.put<Usuario>(globalEnum.url + "user/usuario", { usuario: usuario, newPassword: newPassword, currentPassword: currentPassword }).pipe(catchError(this.handleError));
  }

  getUserByEtiquetado(id: number): Observable<Usuario[]> {
    return this.http.post<Usuario[]>(globalEnum.url + "user/etiquetado", { "id": id }).pipe(catchError(this.handleError));
  }
  private handleError(error: HttpErrorResponse) {
    if(error.status == 409){
      this.snackBar.open("Ya existe un usuario con ese correo","Ok");
    }
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
