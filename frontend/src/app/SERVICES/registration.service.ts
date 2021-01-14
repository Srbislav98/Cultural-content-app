import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from "rxjs";
import { User } from "../MODELS/user";

@Injectable({ providedIn: 'root' })
export class RegistrationService {
    private headers = new HttpHeaders({'Content-Type': 'application/json'});
    
    constructor(
        private http:HttpClient
        ){}

    public registerUser(user:User):Observable<any> {
        return this.http.post<any>("http://localhost:8080/auth/sign-up",user)
    }
    public confirmRegistration(token:any): Observable<any> {
		return this.http.get('http://localhost:8080/auth/regitrationConfirm/'+token, {headers: this.headers, responseType: 'json'});
	}
}