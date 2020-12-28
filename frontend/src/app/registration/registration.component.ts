import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { User } from '../MODELS/user';
import { RegistrationService } from '../SERVICES/registration.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit {
  regForm:FormGroup;
  user:User=new User(1,"","","","","");

  constructor(
    private fBuilder: FormBuilder,
    private router: Router,
    private regService:RegistrationService,
    private toastr: ToastrService
    ) {
      this.regForm = this.fBuilder.group({
        email: ["", [Validators.required, Validators.email]],
        username: ["", [Validators.required]],
        ime: ["", [Validators.required]],
        prezime: ["", [Validators.required]],
        password: ["", [Validators.required,Validators.minLength(6)]]
      });
     }
  
    ngOnInit():void {}
  
    regIn(){
      this.user.email=this.regForm.value["email"];
      this.user.korisnickoIme=this.regForm.value["username"];
      this.user.ime=this.regForm.value["ime"];
      this.user.prezime=this.regForm.value["prezime"];
      this.user.lozinka=this.regForm.value["password"];
      this.regService.registerUser(this.user).subscribe(
        data=>{
          this.toastr.success('Successful registration. Check your email for activation link.');
          this.regForm.reset();
          this.router.navigate(['/login']);
        },
        error=>{
          this.toastr.error("Unsuccessful registration. Email/Username is already in use.");
        }
      )
    }
  }
