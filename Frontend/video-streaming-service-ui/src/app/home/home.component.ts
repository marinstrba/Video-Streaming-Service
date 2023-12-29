import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { ThemeService } from '../theme.service'

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {

	constructor(private router: Router,
				public themeService: ThemeService){
		this.router.navigateByUrl('/feature');
	}
	ngOnInit(): void {

	}
}
