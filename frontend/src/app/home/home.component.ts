import { Component, OnInit } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { AngularYandexMapsModule } from 'angular8-yandex-maps';
import { LokacijaNaMapi } from '../MODELS/LokacijaNaMapi';
import { KulturnaPonudaService } from '../SERVICES/kulturnaPonuda.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  role: any;
  loggedIn:boolean|undefined;
  lokacije: Array<LokacijaNaMapi> = [];

  constructor(private kulturnaPonudaService: KulturnaPonudaService) {
  }

  ngOnInit(): void {
    const item = localStorage.getItem('user');
    this.loggedIn =  localStorage.getItem('accessToken') ? true : false;

		if (!item) {
			this.role = undefined;
			return;
		}
    const jwt: JwtHelperService = new JwtHelperService();
    const decodedItem = JSON.parse(item!);
    const info = jwt.decodeToken(decodedItem.accessToken);
    this.role=info['uloga'];
  }

  getLocations(event: any) {
    console.log(event);
    this.kulturnaPonudaService.getLocationsIds(event).subscribe(
      response => {
        console.log(response);
        this.lokacije = response;
      }
    );
  }

}
