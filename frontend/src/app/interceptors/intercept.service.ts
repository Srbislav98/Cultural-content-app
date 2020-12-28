import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthenticationService } from '../SERVICES/authentication.service';

@Injectable()
export class Interceptor implements HttpInterceptor {

    constructor(private authService: AuthenticationService) {}

	intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

		//const item = localStorage.getItem('user');
		//const decodedItem = JSON.parse(item);

		if (this.authService.isLoggedIn()) {
			const cloned = req.clone({
				setHeaders: {
                    Authorization: `Bearer ${JSON.parse(localStorage.getItem('user')!).token}`
                }
			});

			return next.handle(cloned);
		} else {
			return next.handle(req);
		}
	}
}
