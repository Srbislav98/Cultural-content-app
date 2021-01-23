import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { TipKulturnePonude } from 'src/app/MODELS/tipKulturnePonude';
import { KulturnaPonudaService } from 'src/app/SERVICES/kulturnaPonuda.service';

@Component({
  selector: 'app-edit-tkp',
  templateUrl: './edit-tkp.component.html',
  styleUrls: ['./edit-tkp.component.scss']
})
export class EditTkpComponent implements OnInit {
  regForm:FormGroup;
  sub= new TipKulturnePonude(1,"");

  constructor(
    private route:ActivatedRoute,
    private fBuilder: FormBuilder,
    private router: Router,
    private kulturnaPonudaService:KulturnaPonudaService,
    private toastr: ToastrService
    ) {
      this.regForm = this.fBuilder.group({
        //tip: ["",[Validators.minLength(1)]],
        //lokacija: ["",[Validators.minLength(1)]],
        ime: ["", [Validators.minLength(1)]],
      });
     }

  ngOnInit(): void {
    this.kulturnaPonudaService.getTip(this.route.snapshot.paramMap.get('token')).subscribe(
      result => {
        //this.selectedLocation="Lokacija";
        //this.type="s";//new TipKulturnePonude(1,"dsd");
        this.sub = new TipKulturnePonude(Number(result.id), result.naziv);
      },
      (error:any)=>{
        console.log(error);
      });
  }
  regIn(){
    if(this.regForm.value["ime"].length!=0) this.sub.naziv=this.regForm.value["ime"];
    this.kulturnaPonudaService.editTKP(this.sub).subscribe(
      data=>{
        this.toastr.success('Successful type cultural offer editing.');
        this.router.navigate(['tip-kulturne-ponude']);
        //NE BI TREBALO OVAKO
        /*
        const auth: any = {};
        this.authenticationService.logout();
        auth.username = this.User.email;
        auth.password = this.User.lozinka;
        this.authenticationService.login(auth).subscribe(
          result => {
            //OVAKO DOBIJEM TOKEN
            localStorage.setItem('email',auth.username);
            localStorage.setItem('user', JSON.stringify(result));
            const item = localStorage.getItem('user');
            const decodedItem = JSON.parse(item!);
            localStorage.setItem('accessToken', decodedItem.accessToken);
            this.router.navigate(['profil']);
          },
          error => {
            this.toastr.error('Unsuccessful profile editing');
          });
          */
      },
      error=>{
        this.toastr.error("Unsuccessful type cultural offer editing.");
      }
    )
  }
}
