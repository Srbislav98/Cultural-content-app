import { Observable } from 'rxjs';
import { Novost } from './../MODELS/novost';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from "@angular/core";

@Injectable({ providedIn:'root'})
export class NovostService{
    private readonly path = "/api/novosti";
    private headers = new HttpHeaders({'Content-Type':'application/json'})
    constructor(
        private http:HttpClient
    ){}

    public get(id:any):Observable<Novost>{
        return this.http.get<Novost>(this.path+"/get"+`/${id}`,{headers:this.headers});
    }

    public getAll():Observable<Novost[]>{
        return this.http.get<Novost[]>(this.path);
    }

    public getByPage(page:number):any{
        return this.http.get(this.path+"/by-page?page="+page+"&size=2",{headers:this.headers});
    }

    public update(recenzija:Novost, id:number){
        return this.http.put(this.path+"/update"+`/${id}`,recenzija,{headers:this.headers});
    }

    public create(recenzija:Novost){
        return this.http.post(this.path+"/create",recenzija,{headers:this.headers});
    }

    public delete(id:number){
        return this.http.delete(this.path+`${id}`);
    }

}