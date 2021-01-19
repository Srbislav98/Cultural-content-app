import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../MODELS/user';

@Injectable({
	providedIn: 'root'
})
export class ProfileService {
	private headers = new HttpHeaders({
		'Content-Type': 'application/json'
		//'Authorization': `Bearer ${localStorage.getItem('accessToken')}`
	});

	constructor(
		private http: HttpClient
    ) { }
    
    getAll(page: number, size: number): Observable<any> {
		let queryParams = {};

		queryParams = {
			headers:new HttpHeaders({
				'Content-Type': 'application/json'
			}),
			observe: 'response',
			params: new HttpParams()
				.set('page', String(page))
				.append('size', String(size)),
		};
		const headeri=new HttpHeaders({
			'Content-Type': 'application/json'
		});
		return this.http.get('http://localhost:8080/api/registrovaniKorisnici/allsubscriptions/1', queryParams);
	}

}
