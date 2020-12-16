import { Injectable } from "@angular/core";
import { HttpClient} from '@angular/common/http';
import { Observable } from "rxjs";
import { User } from "../MODELS/user";

@Injectable({ providedIn: 'root' })
export class RegistrationService {

    constructor(
        private htppC:HttpClient
        ){}

    public registerUser(user:User):Observable<any> {
        return this.htppC.post<any>("http://localhost:8080/auth/sign-up",user)
    }
}