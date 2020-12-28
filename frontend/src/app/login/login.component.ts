import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, MinLengthValidator, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationService } from '../SERVICES/authentication.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  logForm: FormGroup;
  

  constructor(
    private fBuilder:FormBuilder,
    private router:Router,
    private authenticationService: AuthenticationService,
    private toastr: ToastrService
  ) {
    this.logForm = this.fBuilder.group({
      email: ["", [Validators.required, Validators.email]],
      password: ["", [Validators.required,Validators.minLength(4)]]
    });
   }

  ngOnInit():void {}

  logIn(){
		const auth: any = {};
		auth.username = this.logForm.value.email;
		auth.password = this.logForm.value.password;

		this.authenticationService.login(auth).subscribe(
			result => {
        this.toastr.success('Successful login!');
        //OVAKO DOBIJEM TOKEN
        localStorage.setItem('user', JSON.stringify(result));
        //const item = localStorage.getItem('user');
		    //const decodedItem = JSON.parse(item!);
        //this.toastr.success(decodedItem.accessToken);
				this.router.navigate(['']);
			},
			error => {
				this.toastr.error('Unsuccessful login! Check email and password.');
			}
		);
	}

}
