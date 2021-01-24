import { YourReviewComponent } from './your-review/your-review.component';
import { ReviewsComponent } from './reviews/reviews.component';
import { KulturnaPonudaDetaljnoComponent } from './kulturna-ponuda-detaljno/kulturna-ponuda-detaljno.component';
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
import { ListKpComponent } from './kulturnePonude/list-kp/list-kp.component';
import { EditKpComponent } from './kulturnePonude/edit-kp/edit-kp.component';
import { AddKpComponent } from './kulturnePonude/add-kp/add-kp.component';
import { ListTkpComponent } from './tipKulturnePonude/list-tkp/list-tkp.component';
import { EditTkpComponent } from './tipKulturnePonude/edit-tkp/edit-tkp.component';
import { AddTkpComponent } from './tipKulturnePonude/add-tkp/add-tkp.component';

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
    path : 'kulturna-ponuda-detaljno',
    component : KulturnaPonudaDetaljnoComponent,
    //canActivate: [RoleGuard],
		//data: {expectedRoles: 'ROLE_USER|ROLE_ADMIN'}
  },
  {
    path : 'reviews/:idKul',
    component : ReviewsComponent,
    //canActivate: [RoleGuard],
		//data: {expectedRoles: 'ROLE_USER|ROLE_ADMIN'}
  },
  {
    path : 'your-review/:idKul',
    component : YourReviewComponent,
    canActivate: [RoleGuard],
		data: {expectedRoles: 'ROLE_USER'}
  },
  {
    path : 'add-news/:idKul',
    component : YourReviewComponent,
    canActivate: [RoleGuard],
		data: {expectedRoles: 'ROLE_ADMIN'}
  },
  {
    path : 'edit-profile',
    component : EditProfileComponent,
    canActivate: [RoleGuard],
		data: {expectedRoles: 'ROLE_USER'}
  },
  {
    path : 'kulturne-ponude',
    component : ListKpComponent,
    canActivate: [RoleGuard],
    data: {expectedRoles: 'ROLE_ADMIN'},
    children: [
    ]
  },
  {
    path : 'tip-kulturne-ponude',
    component : ListTkpComponent,
    canActivate: [RoleGuard],
    data: {expectedRoles: 'ROLE_ADMIN'},
    children: [
    ]
  },
  {
    path : 'edit-kp/:token',
    component : EditKpComponent,
    canActivate: [RoleGuard],
		data: {expectedRoles: 'ROLE_ADMIN'}
  },
  {
    path : 'edit-tkp/:token',
    component : EditTkpComponent,
    canActivate: [RoleGuard],
		data: {expectedRoles: 'ROLE_ADMIN'}
  },
  {
    path : 'add-tkp',
    component : AddTkpComponent,
    canActivate: [RoleGuard],
		data: {expectedRoles: 'ROLE_ADMIN'}
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {onSameUrlNavigation: 'reload'})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
