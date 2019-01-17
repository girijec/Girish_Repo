import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import { Planet } from './planet';
import { AdjacentPlanet } from './adjacentPlanet';


@Injectable()
export class GraphDataService {

  private customersUrl = 'api/planet';  // URL to web API
  private headers = new Headers({'Content-Type': 'application/json'});

  constructor(private http: Http) {}

  // Get all Planets
    getPlanets(): Promise<Planet[]> {
        const url = `${this.customersUrl}/graph`;
        return this.http.get(url)
            .toPromise()
            .then(response => response.json() as Planet[])
            .catch(this.handleError);
    }

    createGraph(): Promise<void> {
        const url = `${this.customersUrl}/graph`;

        return this.http
            .get(url)
            .toPromise()
            .then(res => res.json() as string)
            .catch(this.handleError);
    }

    findPlanetByNode(node: string): Promise<Planet> {
        const url = `${this.customersUrl}/node/${node}`;
        return this.http
            .get(url, { headers: this.headers })
            .toPromise()
            .then(res => res.json() as Planet)
            .catch(this.handleError);
    }

  private handleError(error: any): Promise<any> {
    console.error('Error', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }

  

}
