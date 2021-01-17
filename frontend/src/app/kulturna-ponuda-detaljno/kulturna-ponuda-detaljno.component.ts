import { KulturnaPonuda } from './../MODELS/kulturnaPonuda';
import { KulturnaPonudaService } from './../SERVICES/kulturnaPonuda.service';
import { Router } from '@angular/router';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-kulturna-ponuda-detaljno',
  templateUrl: './kulturna-ponuda-detaljno.component.html',
  styleUrls: ['./kulturna-ponuda-detaljno.component.scss']
})
export class KulturnaPonudaDetaljnoComponent implements OnInit {

  ngOnInit(): void {
  }
  kulForm:FormGroup;

  constructor(private fBuilder:FormBuilder,
    private router:Router,
    private kulService:KulturnaPonudaService,
    private kulturnaPonuda:KulturnaPonuda) {
      this.kulForm = this.fBuilder.group({
        id:[""]
      });
      //this.kulturnaPonuda = kulService.getKulturnaPonuda();
     }

  
  opis = this.kulturnaPonuda.opis;

  
  ucitajKulturnuPonudu(){
    //this.kulturnaPonuda = this.kulService.getKulturnaPonuda();
   
  }
}
