import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { User } from '../MODELS/user';
import { AuthenticationService } from '../SERVICES/authentication.service';
import { ProfileService } from '../SERVICES/profile.service';
import { RegistrationService } from '../SERVICES/registration.service';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.scss']
})
export class EditProfileComponent implements OnInit {
  regForm:FormGroup;
  User= new User(0,"[name]","[surname]","[username]","[e-mail]","[password]");
  userId:number|null;

  constructor(
    private fBuilder: FormBuilder,
    private router: Router,
    private regService:RegistrationService,
    private authenticationService:AuthenticationService,
    private profilService:ProfileService,
    private toastr: ToastrService
    ) {
      this.regForm = this.fBuilder.group({
        email: ["", [Validators.email]],
        username: ["",[Validators.minLength(1)]],
        ime: ["",[Validators.minLength(1)]],
        prezime: ["",[Validators.minLength(1)]],
        password: ["", [Validators.required,Validators.minLength(4)]]
      });
      this.userId=0;
     }
  
     ngOnInit(): void {
      /*this.authenticationService.profil().subscribe(
        result => {
          this.User=new User(Number(result.id),result.ime,result.prezime,result.korisnickoIme,
            result.email,result.lozinka)
        },
        (error: any) => {
          console.log(error);
        }
      );*/
      this.profilService.getId().subscribe(
        res=>{
          this.profilService.getKorisnika(res).subscribe(
            result=>{
              this.User=new User(Number(result.id),result.ime,result.prezime,result.korisnickoIme,
            result.email,result.lozinka)
            }
          )
        }
      )
    }
    regIn(){
      //if(this.regForm.value["email"].length!=0) this.User.email=this.regForm.value["email"];
     // if(this.regForm.value["username"].length!=0) this.User.korisnickoIme=this.regForm.value["username"];
      if(this.regForm.value["ime"].length!=0) this.User.ime=this.regForm.value["ime"];
      if(this.regForm.value["prezime"].length!=0) this.User.prezime=this.regForm.value["prezime"];
      this.User.lozinka=this.regForm.value["password"];
      this.profilService.editProfile(this.User).subscribe(
        async data=>{
          this.toastr.success('Successful profile editing.');
          //NE BI TREBALO OVAKO
          const auth: any = {};
          await this.delay(300);
          this.authenticationService.logout();
		      auth.username = this.User.email;
          auth.password = this.User.lozinka;
          await this.delay(300);
          this.authenticationService.login(auth).subscribe(
            async result => {
              //OVAKO DOBIJEM TOKEN
              localStorage.setItem('email',auth.username);
              localStorage.setItem('user', JSON.stringify(result));
              const item = localStorage.getItem('user');
              const decodedItem = JSON.parse(item!);
              localStorage.setItem('accessToken', decodedItem.accessToken);
              await this.delay(300);
              this.router.navigate(['profil']);
            },
            error => {
              this.toastr.error('Unsuccessful profile editing');
            });
        },
        error=>{
          this.toastr.error("Unsuccessful profile editing.");
        }
      )
    }

    delay(ms: number) {
      return new Promise( resolve => setTimeout(resolve, ms) );
  }
  }
