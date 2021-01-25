import { Lokacija } from 'src/app/MODELS/lokacija';
import { KulturnaPonuda } from './../MODELS/kulturnaPonuda';
import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from "rxjs";
import { TipKulturnePonude } from '../MODELS/tipKulturnePonude';
import { Subscription } from '../MODELS/subscription';
import { LokacijaNaMapi } from '../MODELS/LokacijaNaMapi';



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

	public getTKPByPage(page:number): Observable<any>{
        return this.http.get("http://localhost:8080/api/tipoviKP"+"/by-page?page="+page+"&size=2", {headers:this.headers})
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

	searchAllReviews(content:number,page: number, size: number, id:number): Observable<any> {
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
		return this.http.get(this.path+'/filter-by-grade'+`/${content}`+"/kulturna"+`/${id}`, queryParams);
	}

    public getProsecnaOcena(id:number):Observable<any>{
        return this.http.get<any>("http://localhost:8080/api/kulturnePonude/getProsecnaOcena"+`/${id}`,{headers:this.headers});
	}
	
	public getLokacija(id:number):Observable<Lokacija>{
		return this.http.get<Lokacija>("http://localhost:8080/api/lokacije/getById"+`/${id}`);
	}

    public getDaLiPostoji(idKul:number, idUser:number):Observable<any>{
        return this.http.get<any>(this.path+"/daLiSadrzi"+`/${idKul}`+"/registrovani"+`/${idUser}`, {headers:this.headers});
	}

	public getVecPostojiReview(idKul:number, idUser:number):Observable<any>{
        return this.http.get<any>(this.path+"/vecDaoReview"+`/${idKul}`+"/registrovani"+`/${idUser}`, {headers:this.headers});
    }

    deleteKP(id2: number): Observable<any> {
		const headeri=new HttpHeaders({
			'Content-Type': 'application/json'
		});
		return this.http.delete('http://localhost:8080/api/kulturnePonude/delete'+`/${id2}`, {headers:headeri});
	}
	deleteTKP(id2: number): Observable<any> {
		const headeri=new HttpHeaders({
			'Content-Type': 'application/json'
		});
		return this.http.delete('http://localhost:8080/api/tipoviKP/delete'+`/${id2}`, {headers:headeri});
    }
    searchAllByPage(content:string,page: number, size: number): Observable<any> {
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
		return this.http.get('http://localhost:8080/api/kulturnePonude/filter-by-content-page'+`/${content}`, queryParams);
  }
  searchAllByLocationPage(name:string,page: number, size: number): Observable<any> {
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
		return this.http.get('http://localhost:8080/api/kulturnePonude/filter-by-location-page'+`/${name}`, queryParams);
	}
	addKP(sub: Subscription): Observable<any> {
		const headeri=new HttpHeaders({
			'Content-Type': 'application/json'
		});
		return this.http.post('http://localhost:8080/api/kulturnePonude/create',sub);
	}
	addTKP(sub: TipKulturnePonude): Observable<any> {
		const headeri=new HttpHeaders({
			'Content-Type': 'application/json'
		});
		return this.http.post('http://localhost:8080/api/tipoviKP/create',sub);
	}
	editKP(sub: Subscription): Observable<any> {
		const headeri=new HttpHeaders({
			'Content-Type': 'application/json'
		});
		return this.http.put('http://localhost:8080/api/kulturnePonude/update'+`/${sub.id}`,sub);
	}
	editTKP(sub: TipKulturnePonude): Observable<any> {
		const headeri=new HttpHeaders({
			'Content-Type': 'application/json'
		});
		return this.http.put('http://localhost:8080/api/tipoviKP/update'+`/${sub.id}`,sub);
	}
	getTypes(): Observable<any> {
		const headeri=new HttpHeaders({
			'Content-Type': 'application/json'
		});
		return this.http.get('http://localhost:8080/api/tipoviKP');
	}
	getLocations(): Observable<any> {
		const headeri=new HttpHeaders({
			'Content-Type': 'application/json'
		});
		return this.http.get('http://localhost:8080/api/lokacije');
    }
  getLocationsIds(lokacije: Array<number>): Observable<Array<LokacijaNaMapi>> {
    const headeri=new HttpHeaders({
			'Content-Type': 'application/json'
    });
    return this.http.post<Array<LokacijaNaMapi>>('http://localhost:8080/api/lokacije/getLocationsIds', lokacije);
  }

}
