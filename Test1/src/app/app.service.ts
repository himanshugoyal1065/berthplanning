import { Injectable } from '@angular/core';

export class CountryInfo {
    time: string;
    occd: number;
    freed: number;
    occr: number;
    freer: number;
}

const countriesInfo: CountryInfo[]  = [{
    time: '9:00AM DRY',
    occd: 30,
    freed: 70,
    occr: 0,
    freer: 0
}, {
    time: '9:00AM REEFER',
    occd: 0,
    freed: 0,
    occr: 60,
    freer: 40
}, {
    time: '10:00AM DRY',
    occd: 40,
    freed: 60,
    occr: 0,
    freer: 0
}, {
    time: '10:00AM REEFER',
    occd: 0,
    freed: 0,
    occr: 73,
    freer: 27
}, {
    time: '11:00AM DRY',
    occd: 19,
    freed: 79,
    occr: 0,
    freer: 0
}, {
    time: '11:00AM REEFER',
    occd: 0,
    freed: 0,
    occr: 41,
    freer: 59
}/*, {
    time: '12:00AM DRY',
    occd: 19,
    freed: 119.3,
    occr: 28.9,
    freer: 204.8
}, {
    time: '12:00AM REEFER',
    occd: 6.1,
    freed: 123.6,
    occr: 77.3,
    freer: 85.7
}, {
    time: '1:00PM DRY',
    occd: 59.8,
    freed: 937.6,
    occr: 582,
    freer: 564.3
}, {
    time: '1:00PM REEFER',
    occd: 74.2,
    freed: 308.6,
    occr: 35.1,
    freer: 956.9
}, {
    time: '2:00PM DRY',
    occd: 40,
    freed: 128.5,
    occr: 361.8,
    freer: 105
}, {
    time: '2:00PM REEFER',
    occd: 22.6,
    freed: 241.5,
    occr: 64.9,
    freer: 120.8
}, {
    time: '3:00PM DRY',
    occd: 19,
    freed: 119.3,
    occr: 28.9,
    freer: 204.8
}, {
    time: '3:00PM REEFER',
    occd: 6.1,
    freed: 123.6,
    occr: 77.3,
    freer: 85.7
}, {
    time: '4:00PM DRY',
    occd: 19,
    freed: 119.3,
    occr: 28.9,
    freer: 204.8
}, {
    time: '4:00PM REEFER',
    occd: 6.1,
    freed: 123.6,
    occr: 77.3,
    freer: 85.7
}*/];

@Injectable()
export class Service {
    getCountriesInfo(): CountryInfo[] {
        return countriesInfo;
    }
}
