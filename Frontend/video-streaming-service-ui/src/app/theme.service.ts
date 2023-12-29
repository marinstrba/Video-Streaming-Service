import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ThemeService {

  private isDarkMode: boolean = false;

  constructor() { }

  toggleDarkMode(): void {
  	this.isDarkMode = !this.isDarkMode;
  	document.body.classList.toggle('dark-mode', this.isDarkMode);
  }

  isDarkModeEnabled(): boolean {
  	return this.isDarkMode;
  }
}
