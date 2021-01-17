import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from "rxjs";
import { User } from "../MODELS/user";

@Injectable({ providedIn: 'root' })
export class KulturnaPonudaService{
    private headers = new HttpHeaders({'Content-Type': 'application/json'});
    
    constructor(
        private http:HttpClient
        ){}

    
}