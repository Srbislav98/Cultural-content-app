import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from './login/login.component';
import {RegistrationComponent} from './registration/registration.component';
import {HomeComponent} from './home/home.component';
import { LoginGuard } from './guards/login.service';
import { ConfirmRegistrationComponent } from './confirm-registration/confirm-registration.component';

const routes: Routes = [
  {
    path : '',
    component : HomeComponent
  },
  {
    path : 'login',
    component : LoginComponent,
		canActivate: [LoginGuard]
  },
  {
    path : 'registration',
    component : RegistrationComponent,
		canActivate: [LoginGuard]
  },
  {
    path : 'registrationConfirm/:token',
    component : ConfirmRegistrationComponent,
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
