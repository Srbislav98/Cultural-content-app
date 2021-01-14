import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { RegistrationService } from '../SERVICES/registration.service';

@Component({
  selector: 'app-confirm-registration',
  templateUrl: './confirm-registration.component.html',
  styleUrls: ['./confirm-registration.component.scss']
})
export class ConfirmRegistrationComponent implements OnInit {

  constructor(
    private fBuilder: FormBuilder,
    private router: Router,
    private route:ActivatedRoute,
    private regService:RegistrationService,
    private toastr: ToastrService
  ) { }

  ngOnInit(): void {
  }

  confirmReg(){
    this.regService.confirmRegistration(this.route.snapshot.paramMap.get('token')).subscribe(
      data=>{
        this.toastr.success('Successful activated profile. You can now sign in.');
        this.router.navigate(['/login']);
      },
      error=>{
        this.toastr.error("Activation link is not valid.");
      }
    )
  }

}
