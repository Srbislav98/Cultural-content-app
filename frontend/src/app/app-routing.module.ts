import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from './login/login.component';
import {RegistrationComponent} from './registration/registration.component';
import {HomeComponent} from './home/home.component';
import { LoginGuard } from './guards/login.service';
import { ConfirmRegistrationComponent } from './confirm-registration/confirm-registration.component';
import { ForgottenPasswordComponent } from './forgotten-password/forgotten-password.component';
import { RoleGuard } from './guards/role.service';
import { ProfilComponent } from './profil/profil.component';
import { SubscriptionListComponent } from './subscriptions/subscription-list/subscription-list.component';
import { EditProfileComponent } from './edit-profile/edit-profile.component';

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
    canActivate: [LoginGuard]
  },
  {
    path : 'forgotten-password',
    component : ForgottenPasswordComponent,
    canActivate: [LoginGuard]
  },
  {
    path : 'profil',
    component : ProfilComponent,
    canActivate: [RoleGuard],
		data: {expectedRoles: 'ROLE_USER'}
  },
  {
    path : 'edit-profile',
    component : EditProfileComponent,
    canActivate: [RoleGuard],
		data: {expectedRoles: 'ROLE_USER'}
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {onSameUrlNavigation: 'reload'})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
