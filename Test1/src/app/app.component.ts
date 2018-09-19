import { NgModule, Component, enableProdMode } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';

import { DxChartModule } from 'devextreme-angular';

import { CountryInfo, Service } from './app.service';

if (!/localhost/.test(document.location.host)) {
    enableProdMode();
}

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css'],
    providers: [Service]
})
export class AppComponent {
    countriesInfos: CountryInfo[];

    constructor(service: Service) {
        this.countriesInfos = this.chunkBy(service.getCountriesInfo(), 2);
    }

    chunkBy(arrAny: any[], value: number) {
        // tslint:disable-next-line:prefer-const
        let result = [];
        for (let i = 0; i < arrAny.length; i = i + value) {
            result.push(arrAny.slice(i, i + value));
            console.log();
        }
        return result;
    }

    customizeTooltip(arg: any) {
        return {
            text: arg.percentText + ' - ' + arg.valueText
        };
    }
}

@NgModule({
    imports: [
        BrowserModule,
        DxChartModule
    ],
    declarations: [AppComponent],
    bootstrap: [AppComponent]
})
export class AppModule { }

platformBrowserDynamic().bootstrapModule(AppModule);
