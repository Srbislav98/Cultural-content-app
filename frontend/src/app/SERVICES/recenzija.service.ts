import { Observable } from 'rxjs';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from "@angular/core";
import { Recenzija } from '../MODELS/recenzija';

@Injectable({ providedIn:'root'})
export class RecenzijaService{
    private headers = new HttpHeaders({'Content-Type':'application/json'})

    private readonly path = "http://localhost:8080/api/recenzije";

    constructor(
        private http:HttpClient
    ){}

    public get(id:any):Observable<Recenzija>{
        return this.http.get<Recenzija>(this.path+"/get"+`/${id}`,{headers:this.headers});
    }

    public getAll():Observable<Recenzija[]>{
        return this.http.get<Recenzija[]>(this.path);
    }

    public getByPage(page:number):any{
        return this.http.get(this.path+"/by-page?page="+page+"&size=2",{headers:this.headers});
    }

    public update(recenzija:Recenzija, id:number){
        return this.http.put(this.path+"/update"+`/${id}`,recenzija);
    }

    public create(recenzija:Recenzija){
        return this.http.post(this.path+"/create",recenzija,{headers:this.headers});
    }

    public delete(id:number){
        return this.http.delete(this.path+`${id}`);
    }

}