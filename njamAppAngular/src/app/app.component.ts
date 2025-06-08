import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { RestaurantListComponent } from './Components/restaurant-list/restaurant-list.component';
import { CommonModule } from '@angular/common';
import { RestaurantDetailsComponent } from './Components/restaurant-details/restaurant-details.component';
import { TranslateModule, TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    CommonModule,
    TranslateModule,
    RouterOutlet,
    RestaurantListComponent,
    RestaurantDetailsComponent,
  ],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
  selectedLang = 'hr';

  constructor(private translate: TranslateService) {
    translate.addLangs(['hr', 'en']);
    translate.setDefaultLang('hr');
  }

  ngOnInit() {
    if (typeof localStorage !== 'undefined') {
      const savedLang = localStorage.getItem('lang');
      if (savedLang === 'hr' || savedLang === 'en') {
        this.translate.use(savedLang);
        this.selectedLang = savedLang;
      } else {
        this.translate.use('hr');
        this.selectedLang = 'hr';
      }
    } else {
      this.translate.use('hr');
      this.selectedLang = 'hr';
    }
  }

  changeLanguage(lang: string) {
    this.translate.use(lang);
    this.selectedLang = lang;
    localStorage.setItem('lang', lang);
  }
}
