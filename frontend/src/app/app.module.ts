import { TableNovostComponent } from './table-novost/table-novost.component';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { HomeComponent } from './home/home.component';
import { FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { ToastrModule } from 'ngx-toastr';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { Interceptor } from './interceptors/intercept.service';
import { ConfirmRegistrationComponent } from './confirm-registration/confirm-registration.component';
import { ForgottenPasswordComponent } from './forgotten-password/forgotten-password.component';
import { KulturnaPonudaDetaljnoComponent } from './kulturna-ponuda-detaljno/kulturna-ponuda-detaljno.component';
import { ProfilComponent } from './profil/profil.component';
import { PaginationComponent } from './pagination/pagination.component';
import { TableComponent } from './subscriptions/table/table.component';
import { SubscriptionListComponent } from './subscriptions/subscription-list/subscription-list.component';
import { EditProfileComponent } from './edit-profile/edit-profile.component';
import { ReviewsComponent } from './reviews/reviews.component';
import { TableRecenzijaComponent } from './table-recenzija/table-recenzija.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegistrationComponent,
    HomeComponent,
    ConfirmRegistrationComponent,
    ForgottenPasswordComponent,
    KulturnaPonudaDetaljnoComponent,
    ProfilComponent,
    PaginationComponent,
    TableComponent,
    SubscriptionListComponent,
    EditProfileComponent,
    TableNovostComponent,
    ReviewsComponent,
    TableRecenzijaComponent
    
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    ReactiveFormsModule,
    BrowserAnimationsModule, // required animations module
		ToastrModule.forRoot(),
  ],
  providers: [{provide: HTTP_INTERCEPTORS, useClass: Interceptor, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
