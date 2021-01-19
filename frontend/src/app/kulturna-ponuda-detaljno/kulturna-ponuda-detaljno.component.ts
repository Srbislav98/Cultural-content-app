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

  
  kulForm:FormGroup;
  kulturnaPonuda:KulturnaPonuda | undefined;

  constructor(private fBuilder:FormBuilder,
    private router:Router,
    private kulService:KulturnaPonudaService
    ) {
      this.kulturnaPonuda = undefined;
      this.kulForm = this.fBuilder.group({
        id:[""]
      });
  }

  ngOnInit(): void {
    this.ucitajKulturnuPonudu();
  }
  
  ucitajKulturnuPonudu(){
    this.kulService.get(100).subscribe(data => {this.kulturnaPonuda = data;});
   
  }
  //test = this.kulturnaPonuda?.Naziv;
}
