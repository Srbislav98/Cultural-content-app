import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthenticationService } from '../SERVICES/authentication.service';

@Component({
  selector: 'app-forgotten-password',
  templateUrl: './forgotten-password.component.html',
  styleUrls: ['./forgotten-password.component.scss']
})
export class ForgottenPasswordComponent implements OnInit {
  resetForm: FormGroup;

  constructor(
    private fBuilder:FormBuilder,
    private router:Router,
    private authenticationService: AuthenticationService,
    private toastr: ToastrService
  ) {
    this.resetForm = this.fBuilder.group({
      email: ["", [Validators.required, Validators.email]]
    });
   }

   ngOnInit():void {}

   resetPassw(){
     const auth: any = {};
     auth.username = this.resetForm.value.email;
 
     this.authenticationService.resetPassw(auth).subscribe(
       result => {
         this.toastr.success('Please check your email for a new password!');
         this.router.navigate(['/login']);
       },
       error => {
         this.toastr.error('Unsuccessful password reseting.');
       }
     );
   }
 
 }
 
