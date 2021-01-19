import { KulturnaPonuda } from './../MODELS/kulturnaPonuda';
import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from "rxjs";



@Injectable({ providedIn: 'root' })
export class KulturnaPonudaService{
    private headers = new HttpHeaders({'Content-Type': 'application/json'});
    
    
    constructor(
        private http:HttpClient
        ){}

    public getKulturnaPonuda():Observable<KulturnaPonuda>{
        return this.http.get<KulturnaPonuda>("http://localhost:8080/api/kulturnePonude/get/100", {headers:this.headers});
    }

    public getProsecnaOcena():Observable<any>{
        return this.http.get<any>("http://localhost:8080/api/kulturnePonude/getProsecnaOcena/100");
    }
}