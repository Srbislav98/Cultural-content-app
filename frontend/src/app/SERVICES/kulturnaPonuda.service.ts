import { KulturnaPonuda } from './../MODELS/kulturnaPonuda';
import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from "rxjs";
import { TipKulturnePonude } from '../MODELS/tipKulturnePonude';



@Injectable({ providedIn: 'root' })
export class KulturnaPonudaService{
    private headers = new HttpHeaders({'Content-Type': 'application/json'});
    
    private readonly path = "http://localhost:8080/api/kulturnePonude";
    
    constructor(
        private http:HttpClient
        ){}

    public get(id:any):Observable<KulturnaPonuda>{
        return this.http.get<KulturnaPonuda>("http://localhost:8080/api/kulturnePonude/get/"+`${id}` , {headers:this.headers});
    }

    public getTip(id:any):Observable<TipKulturnePonude>{
        return this.http.get<TipKulturnePonude>("http://localhost:8080/api/tipoviKP/get"+`/${id}`,{headers:this.headers})
    }

    public getAll():Observable<KulturnaPonuda[]>{
        return this.http.get<KulturnaPonuda[]>(this.path)
    }

    public getByPage(page:number): Observable<any>{
        return this.http.get(this.path+"/by-page?page="+page+"&size=2", {headers:this.headers})
    }

    public getNovostiPage(page:number,size:number, id:number):Observable<any>{
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
		return this.http.get(this.path+'/getNovosti/'+`${id}`, queryParams);

        //return this.http.get(this.path+"/getNovosti"+`/${id}`+"/by-page?page="+page+"&size=2",{headers:this.headers})
    }

    public getRecenzijePage(page:number,size:number, id:number):Observable<any>{
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
		return this.http.get(this.path+'/getRecenzije/'+`${id}`, queryParams);

        //return this.http.get(this.path+"/getNovosti"+`/${id}`+"/by-page?page="+page+"&size=2",{headers:this.headers})
    }

    public getProsecnaOcena(id:number):Observable<any>{
        return this.http.get<any>("http://localhost:8080/api/kulturnePonude/getProsecnaOcena"+`/${id}`);
    }

    public getDaLiPostoji(idKul:number, idUser:number):Observable<any>{
        return this.http.get<any>(this.path+"/daLiSadrzi"+`/${idKul}`+"/registrovani"+`/${idUser}`, {headers:this.headers});
    }
}