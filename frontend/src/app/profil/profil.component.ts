import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from '../MODELS/user';
import { AuthenticationService } from '../SERVICES/authentication.service';

@Component({
  selector: 'app-profil',
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.scss']
})
export class ProfilComponent implements OnInit {
  User= new User(1,"s","dff","dd","dd","ddd");

  constructor(
    private fBuilder:FormBuilder,
    private router:Router,
    private authenticationService: AuthenticationService
  ) { }

  ngOnInit(): void {
    this.authenticationService.profil().subscribe(
      result => {
        this.User=new User(Number(result.id),result.ime,result.prezime,result.korisnickoIme,
          result.email,result.lozinka)
			},
      (error: any) => {
				console.log(error);
      }
    );
  }

}
