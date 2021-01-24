import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthenticationService } from '../SERVICES/authentication.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  @Input() loggedIn: boolean|undefined;
  @Input() role: any;

  constructor(
    private router: Router,
    private authenticationService: AuthenticationService,
    private toastr: ToastrService) { }

  ngOnInit(): void {
  }

  goToHome() {
    this.router.navigate(['']);
  }

  goToLogIn() {
    //localStorage.removeItem('user');
    //localStorage.removeItem('uloga');
    this.router.navigate(['/login']);
  }

  goToRegistration() {
    this.router.navigate(['/registration']);
  }

  goToProfile() {
    this.router.navigateByUrl('/profil');
  }

  logOut() {
    this.authenticationService.logout().subscribe(
			result => {
        localStorage.removeItem('user');
        localStorage.removeItem('accessToken');
        localStorage.removeItem('uloga');
				this.toastr.success('You have been successfully logged out!');
				this.router.navigate(['login']);
			},
			error => {
				this.toastr.error('Some error. Try again.');
			}
		);
  }

}
