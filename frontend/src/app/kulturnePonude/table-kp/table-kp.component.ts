import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Subscription } from 'src/app/MODELS/subscription';
import { KulturnaPonudaService } from 'src/app/SERVICES/kulturnaPonuda.service';
import { ProfileService } from 'src/app/SERVICES/profile.service';

@Component({
  selector: 'app-table-kp',
  templateUrl: './table-kp.component.html',
  styleUrls: ['./table-kp.component.scss']
})
export class TableKpComponent implements OnInit {
  @Input() subscriptions: Subscription[] | undefined;
  uloga: any;

	constructor(
    private toastr: ToastrService,
    private router:Router,
    private kulturnaponudaService: KulturnaPonudaService,
  ) {
    this.router.routeReuseStrategy.shouldReuseRoute = () => {
      return false;
    };
    this.uloga = localStorage.getItem('uloga');
  }

  ngOnInit() {}

  Obrisi(sub: Subscription): void {
    console.log(sub);
    console.log(sub.opis);
    console.log(sub.id);

    this.kulturnaponudaService.deleteKP(sub.id).subscribe(
			res => {
        this.toastr.success("Succcessful deleted cultural offer.");
        //this.router.navigate(['']);
        this.router.navigate(['kulturne-ponude']);
      },
      error => {
				this.toastr.error('Unsucccessful deleted cultural offer.');
			}
    );
  }
}
