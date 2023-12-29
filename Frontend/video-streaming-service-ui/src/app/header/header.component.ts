import { Component, OnInit } from '@angular/core';
import { OidcSecurityService } from "angular-auth-oidc-client";
import { ThemeService } from '../theme.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent implements OnInit {

  constructor(private oidcSecurityService: OidcSecurityService,
  				private themeService: ThemeService) {}

	isAuthenticated: boolean = false;

	ngOnInit(): void {
		this.oidcSecurityService.isAuthenticated$
							.subscribe(({isAuthenticated}) => {
								this.isAuthenticated = isAuthenticated;
							})
	}

	login(){
		this.oidcSecurityService.authorize();
	}

	logout() {
        this.oidcSecurityService
          .logoff()
          .subscribe((result) => console.log(result));
          this.oidcSecurityService.logoffLocal();
      }

      toggleDarkMode(){
      	this.themeService.toggleDarkMode();
      }

      get isDarkMode(): boolean {
      	return this.themeService.isDarkModeEnabled();
      }
}
