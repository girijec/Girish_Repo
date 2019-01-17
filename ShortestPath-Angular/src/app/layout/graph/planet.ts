import { AdjacentPlanet } from './adjacentPlanet';

export class Planet {
    public planetId: number;
    public planetNode: string;
    public planetName: string;
    public distance: number;
    public totalTraffic: number;
    public distanceWithTraffic: number;
    public timeTakenWithTraffic: number;
    public timeTakenWithOutTraffic: number;
    public adjacentPlanets: AdjacentPlanet[];
    public shortestPath: Planet[];
}
