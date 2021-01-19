import { Observable } from 'rxjs';
import { Fotografija } from './../MODELS/fotografija';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
@Injectable({ providedIn:'root'})
export class FotografijaService{
    private readonly path = "/api/fotografije";
    private headers = new HttpHeaders({'Content-Type':'application/json'})
    constructor(
        private http:HttpClient
    ){}

    public get(id:any):Observable<Fotografija>{
        return this.http.get<Fotografija>(this.path+"/get"+`/${id}`,{headers:this.headers});
    }

    public getAll():Observable<Fotografija[]>{
        return this.http.get<Fotografija[]>(this.path);
    }

    public getByPage(page:number):any{
        return this.http.get(this.path+"/by-page?page="+page+"&size=2",{headers:this.headers});
    }

    public update(recenzija:Fotografija, id:number){
        return this.http.put(this.path+"/update"+`/${id}`,recenzija,{headers:this.headers});
    }

    public create(recenzija:Fotografija){
        return this.http.post(this.path+"/create",recenzija,{headers:this.headers});
    }

    public delete(id:number){
        return this.http.delete(this.path+`${id}`);
    }
}