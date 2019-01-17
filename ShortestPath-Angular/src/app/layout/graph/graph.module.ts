import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GraphRoutingModule } from './graph-routing.module';
import { GraphComponent } from './graph.component';

import { SharedModule } from './../../shared';



@NgModule({
    imports: [
        CommonModule,
        GraphRoutingModule,
        SharedModule,
        
    ],
    declarations: [GraphComponent],
    entryComponents: [ ]
})
export class GraphModule { }

