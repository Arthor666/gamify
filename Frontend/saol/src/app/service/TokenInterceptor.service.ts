import { HttpErrorResponse, HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { catchError, Observable, throwError } from "rxjs";
import { globalEnum } from "../globalEnum";

export class TokenInterceptor implements HttpInterceptor {
  constructor() { }
  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = localStorage.getItem(globalEnum.token);    
    if (token) {
      // If we have a token, we set it to the header
      request = request.clone({
        setHeaders: {
          Authorization: "Bearer " + token
        }          
      });
    }    
    return next.handle(request);
  }
}
