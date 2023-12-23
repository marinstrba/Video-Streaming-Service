import { Component, OnInit } from '@angular/core';
import { OidcSecurityService, LoginResponse } from "angular-auth-oidc-client";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  title = 'video-streaming-service-ui';

  constructor(private oidcSecurityService: OidcSecurityService) {}

  ngOnInit(): void {
  	 this.oidcSecurityService
          .checkAuth()
          .subscribe((loginResponse: LoginResponse) => {
            const { isAuthenticated } =
              loginResponse;
              console.log("APP IS AUTHENTICATED!!!", isAuthenticated);
          });
  }

}
