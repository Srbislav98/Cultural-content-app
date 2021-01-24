import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../MODELS/user';

@Injectable({
	providedIn: 'root'
})
export class AuthenticationService {
  private headers = new HttpHeaders({'Content-Type': 'application/json'});

	constructor(
		private http: HttpClient
	) { }

	login(auth: any): Observable<any> {
		return this.http.post('http://localhost:8080/auth/log-in', {username: auth.username, password: auth.password}, {headers: this.headers, responseType: 'json'});
	}
	profil(): Observable<any> {
		return this.http.post('http://localhost:8080/api/registrovaniKorisnici/user', {username:  localStorage.getItem('email'), password: "ssss"}, {headers: this.headers, responseType: 'json'});
	}
	resetPassw(auth: any): Observable<any>  {
		return this.http.post('http://localhost:8080/recover/password', {email: auth.username}, {headers: this.headers, responseType: 'text'});
	}

	logout(): Observable<any> {
		return this.http.get('http://localhost:8080/auth/log-out', {headers: this.headers, responseType: 'text'});
	}
	isLoggedIn(): boolean {
		if (!localStorage.getItem('user')) {
				return false;
		}
		return true;
    }

}
