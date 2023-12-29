import { Component } from '@angular/core';
import { ThemeService } from '../theme.service'

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.css'
})
export class SidebarComponent {

constructor(public themeService: ThemeService) {}

}
