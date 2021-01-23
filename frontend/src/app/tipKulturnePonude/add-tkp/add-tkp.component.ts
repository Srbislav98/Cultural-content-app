import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { TipKulturnePonude } from 'src/app/MODELS/tipKulturnePonude';
import { KulturnaPonudaService } from 'src/app/SERVICES/kulturnaPonuda.service';

@Component({
  selector: 'app-add-tkp',
  templateUrl: './add-tkp.component.html',
  styleUrls: ['./add-tkp.component.scss']
})
export class AddTkpComponent implements OnInit {
  sub= new TipKulturnePonude(0,"");
  regForm:FormGroup;

  constructor(
    private fBuilder: FormBuilder,
    private router: Router,
    private kulturnaPonudaService:KulturnaPonudaService,
    private toastr: ToastrService
    ) {
      this.regForm = this.fBuilder.group({
        ime: ["", [Validators.required,Validators.minLength(1)]],
      });
     }
    ngOnInit(): void {}
    regIn(){
      this.sub.naziv=this.regForm.value["ime"];
      this.kulturnaPonudaService.addTKP(this.sub).subscribe(
        data=>{
          this.toastr.success('Successful cultural offer type adding.');
          this.router.navigate(['tip-kulturne-ponude']);
        },
        error=>{
          this.toastr.error("Unsuccessful cultural offer type adding.");
        }
      )
    }
  }
