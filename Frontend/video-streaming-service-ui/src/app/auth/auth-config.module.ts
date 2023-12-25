import { NgModule } from '@angular/core';
import { AuthModule } from 'angular-auth-oidc-client';


@NgModule({
    imports: [AuthModule.forRoot({
        config: {
            authority: 'https://dev-qrbuum6sjg7e1t30.us.auth0.com',
            redirectUrl: window.location.origin,
            clientId: '91UKL9447qNrXaCssviqgwKJdiPW4zMR',
            scope: 'openid profile offline_access',
            responseType: 'code',
            silentRenew: true,
            useRefreshToken: true,
            secureRoutes: ['http://localhost:8080'],
            customParamsAuthRequest: {
            	audience: 'http://localhost:8080'
            }
        }
      })],
    exports: [AuthModule],
})
export class AuthConfigModule {}
