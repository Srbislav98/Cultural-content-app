import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient, HttpParams } from '@angular/common/http';
import { User } from '../MODELS/user';
import { AuthenticationService } from './authentication.service';

@Injectable({
	providedIn: 'root'
})
export class ProfileService {
	User= new User(1,"s","dff","dd","dd","ddd");
	private headers = new HttpHeaders({
		'Content-Type': 'application/json'
		//'Authorization': `Bearer ${localStorage.getItem('accessToken')}`
	});

	constructor(
		private http: HttpClient,
		private authenticationService:AuthenticationService
    ) { }
	searchAll(content:string,page: number, size: number): Observable<any> {
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
		return this.http.get('http://localhost:8080/api/registrovaniKorisnici/filter-by-location'+`/${content}`, queryParams);
	}
    getAll(page: number, size: number): Observable<any> {
		this.authenticationService.profil().subscribe(
			result => {
			  this.User=new User(Number(result.id),result.ime,result.prezime,result.korisnickoIme,
				result.email,result.lozinka)
			},
			(error: any) => {
			  console.log(error);
			}
		  );
		const id=this.User.id;
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
		return this.http.get('http://localhost:8080/api/registrovaniKorisnici/allsubscriptions'+`/${id}`, queryParams);
	}
	addSub(idKul:number, idUser:number):Observable<any>{
		const headeri=new HttpHeaders({
			'Content-Type': 'application/json'
		});
		return this.http.put("http://localhost:8080/api/registrovaniKorisnici/subscribe"+`/${idUser}`+"/kulturnaPonuda"+`/${idKul}`,{headers:headeri});
	}

	deleteSub2(idKul:number, idUser:number): Observable<any> {
		const headeri=new HttpHeaders({
			'Content-Type': 'application/json'
		});
		
		return this.http.delete('http://localhost:8080/api/registrovaniKorisnici/unsubscribe'+`/${idUser}`+'/kulturnaPonuda'+`/${idKul}`, {headers:headeri});
	}

	deleteSub(id2: number): Observable<any> {
		const headeri=new HttpHeaders({
			'Content-Type': 'application/json'
		});
		const id1=1;
		console.log(id1);
		console.log(id2);
		return this.http.delete('http://localhost:8080/api/registrovaniKorisnici/unsubscribe'+`/${id1}`+'/kulturnaPonuda'+`/${id2}`, {headers:headeri});
	}
	editProfile(user: User): Observable<any> {
		const headeri=new HttpHeaders({
			'Content-Type': 'application/json'
		});
		const id1=user.id;
		//console.log(id1);
		//console.log(id2);
		console.log(user);
		return this.http.put('http://localhost:8080/api/registrovaniKorisnici'+`/${id1}`,user, {headers:headeri});
	}

}
