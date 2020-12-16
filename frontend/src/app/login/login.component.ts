import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, MinLengthValidator, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  logForm: FormGroup;
  

  constructor(
    private fBuilder:FormBuilder,
    private router:Router
  ) {
    this.logForm = this.fBuilder.group({
      email: ["", [Validators.required, Validators.email]],
      password: ["", [Validators.required,Validators.minLength(6)]]
    });
   }

  ngOnInit():void {}

  logIn(){
    this.logForm.reset();
		//this.router.navigate(['home']);
  }

}
