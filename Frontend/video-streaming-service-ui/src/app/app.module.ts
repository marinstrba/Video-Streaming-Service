import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { AuthConfigModule } from './auth/auth-config.module';
import { AuthInterceptor } from 'angular-auth-oidc-client';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FeaturedComponent } from './featured/featured.component';
import { FlexLayoutModule } from '@angular/flex-layout';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { HeaderComponent } from './header/header.component';
import { HistoryComponent } from './history/history.component';
import { HomeComponent } from './home/home.component';
import { HttpClientModule } from '@angular/common/http';
import { LikedVideosComponent } from './liked-videos/liked-videos.component';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatChipsModule } from '@angular/material/chips';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatListModule } from '@angular/material/list';
import { MatOptionModule } from '@angular/material/core';
import { MatSelectModule } from '@angular/material/select';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';
import { NgxFileDropModule } from 'ngx-file-drop';
import { RouterModule } from '@angular/router';
import { SaveVideoDetailsComponent } from './save-video-details/save-video-details.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { SubscriptionsComponent } from './subscriptions/subscriptions.component';
import { UploadVideoComponent } from './upload-video/upload-video.component';
import { VgBufferingModule } from '@videogular/ngx-videogular/buffering';
import { VgControlsModule } from '@videogular/ngx-videogular/controls';
import { VgCoreModule} from '@videogular/ngx-videogular/core';
import { VgOverlayPlayModule } from '@videogular/ngx-videogular/overlay-play';
import { VideoCardComponent } from './video-card/video-card.component';
import { VideoDetailComponent } from './video-detail/video-detail.component';
import { VideoPlayerComponent } from './video-player/video-player.component';
import { CallbackComponent } from './callback/callback.component';
import { CommentsComponent } from './comments/comments.component';

@NgModule({
  declarations: [
	AppComponent,
	FeaturedComponent,
	HeaderComponent,
	HistoryComponent,
	HomeComponent,
	LikedVideosComponent,
	SaveVideoDetailsComponent,
	SidebarComponent,
	SubscriptionsComponent,
	UploadVideoComponent,
	VideoCardComponent,
	VideoDetailComponent,
	VideoPlayerComponent,
 CallbackComponent,
 CommentsComponent
  ],
  imports: [
     AppRoutingModule,
     AuthConfigModule,
     BrowserAnimationsModule,
     BrowserModule,
     FlexLayoutModule,
     FormsModule,
     HttpClientModule,
     MatButtonModule,
     MatCardModule,
     MatChipsModule,
     MatFormFieldModule,
     MatIconModule,
     MatInputModule,
     MatListModule,
     MatOptionModule,
     MatSelectModule,
     MatSidenavModule,
     MatToolbarModule,
     NgxFileDropModule,
     ReactiveFormsModule,
     RouterModule.forRoot([]),
     VgBufferingModule,
     VgControlsModule,
     VgCoreModule,
     VgOverlayPlayModule

  ],
  providers: [
  {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
