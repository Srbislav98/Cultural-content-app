import { KulturnaPonudaDetaljnoComponent } from './kulturna-ponuda-detaljno/kulturna-ponuda-detaljno.component';
import { TableRecenzijaComponent } from './table-recenzija/table-recenzija.component';
import { YourReviewComponent } from './your-review/your-review.component';
import { ReviewsComponent } from './reviews/reviews.component';
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
import { ProfilComponent } from './profil/profil.component';
import { PaginationComponent } from './pagination/pagination.component';
import { TableComponent } from './subscriptions/table/table.component';
import { SubscriptionListComponent } from './subscriptions/subscription-list/subscription-list.component';
import { EditProfileComponent } from './edit-profile/edit-profile.component';
import { TableKpComponent } from './kulturnePonude/table-kp/table-kp.component';
import { ListKpComponent } from './kulturnePonude/list-kp/list-kp.component';
import { AddKpComponent } from './kulturnePonude/add-kp/add-kp.component';
import { EditKpComponent } from './kulturnePonude/edit-kp/edit-kp.component';
import { NgSelectModule } from '@ng-select/ng-select';
import { AddTkpComponent } from './tipKulturnePonude/add-tkp/add-tkp.component';
import { EditTkpComponent } from './tipKulturnePonude/edit-tkp/edit-tkp.component';
import { TableTkpComponent } from './tipKulturnePonude/table-tkp/table-tkp.component';
import { ListTkpComponent } from './tipKulturnePonude/list-tkp/list-tkp.component';
import { MapaComponent } from './mapa/mapa.component';
import { AngularYandexMapsModule } from 'angular8-yandex-maps';
import { AddNewsComponent } from './add-news/add-news.component';

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
    TableKpComponent,
    ListKpComponent,
    AddKpComponent,
    EditKpComponent,
    AddTkpComponent,
    EditTkpComponent,
    TableTkpComponent,
    ListTkpComponent,
    MapaComponent,
    ReviewsComponent,
    YourReviewComponent,
    TableRecenzijaComponent,
    AddNewsComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    ReactiveFormsModule,
    NgSelectModule,
    BrowserAnimationsModule, // required animations module
    ToastrModule.forRoot(),
    AngularYandexMapsModule
  ],
  providers: [{provide: HTTP_INTERCEPTORS, useClass: Interceptor, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
