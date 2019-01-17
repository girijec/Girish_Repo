import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Http, HttpModule } from '@angular/http';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { TranslateLoader, TranslateModule } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { GraphDataService } from './layout/graph/graph.data.service';
import { RouteReuseStrategy, RouterModule } from '@angular/router';
import { MCTOReuseStrategy } from './mcto-reuse-strategy';

export function HttpLoaderFactory(http: Http) {
    // for development
    return new TranslateHttpLoader(http, '/assets/i18n/', '.json');
}
@NgModule({
    declarations: [
        AppComponent,
    ],
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        FormsModule,
        HttpModule,
        AppRoutingModule,
        TranslateModule.forRoot({
            loader: {
                provide: TranslateLoader,
                useFactory: HttpLoaderFactory,
                deps: [Http]
            }
        }),

    ],
    providers: [

        GraphDataService,
        { provide: RouteReuseStrategy, useClass: MCTOReuseStrategy }
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
