import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { MatDialog, MatDialogRef, MatSnackBar } from '@angular/material'

import { Planet } from './planet';
import { GraphDataService } from './graph.data.service';






@Component({
    selector: 'app-graph',
    templateUrl: './graph.component.html',
    styleUrls: ['./graph.component.scss']
})
export class GraphComponent implements OnInit {

    planets: Planet[] = [];
    planet = new Planet();
    submitted = false;
    searchText: string;
    selectedPlanetId: number;
    selectedRowIndex: number;


    constructor(private graphDataService: GraphDataService,
        private location: Location,
        public mdDialog: MatDialog,
        public snackBar: MatSnackBar) {

    }


    getPlanets() {
        this.graphDataService.getPlanets().then(planets => this.planets = planets);
    }


    ngOnInit(): void {
        this.getPlanets();

    }

    onSelect(): void {
        this.planet = this.planets.find(planet => planet.planetId == this.selectedPlanetId);
    }


    openSnackBar(message: string, action: string) {
        this.snackBar.open(message, action, {
            duration: 2000,
        });
    }
}
