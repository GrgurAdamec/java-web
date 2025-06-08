// src/app/translate.config.ts
import { importProvidersFrom, inject } from '@angular/core';
import { TranslateModule, TranslateLoader } from '@ngx-translate/core';
import { HttpClient } from '@angular/common/http';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';

export function httpLoaderFactory(): TranslateHttpLoader {
  const http = inject(HttpClient); // ← koristi Angular inject API
  return new TranslateHttpLoader(http, './assets/i18n/', '.json');
}

export function provideTranslate() {
  return importProvidersFrom(
    TranslateModule.forRoot({
      defaultLanguage: 'hr',
      loader: {
        provide: TranslateLoader,
        useFactory: httpLoaderFactory,
      },
    })
  );
}
