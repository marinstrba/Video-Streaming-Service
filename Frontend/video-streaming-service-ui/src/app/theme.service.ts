import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ThemeService {

  private isDarkMode: boolean = false;

  constructor() { }

  toggleDarkMode(): void {
    this.isDarkMode = !this.isDarkMode;
    const appRootElement = document.querySelector('app-root');
    const sideBarElement = document.querySelector('app-sidebar');

    if (appRootElement) {
      appRootElement.classList.toggle('dark-mode', this.isDarkMode);
    }

    if (sideBarElement) {
      sideBarElement.classList.toggle('dark-mode', this.isDarkMode);
    }

    document.body.classList.toggle('dark-mode', this.isDarkMode);
  }


  isDarkModeEnabled(): boolean {
  	return this.isDarkMode;
  }
}
