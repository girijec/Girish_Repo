import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
   {
        path: 'admin',
        loadChildren: './layout/layout.module#LayoutModule',
        //canActivate: [AuthGuard]
    },
    { path: '', loadChildren: './layout/graph/graph.module#GraphModule', data: { reuse: true } },
    { path: 'graph', loadChildren: './layout/graph/graph.module#GraphModule', data: { reuse: true } },


    { path: 'not-found', loadChildren: './not-found/not-found.module#NotFoundModule' },
    { path: '**', redirectTo: 'not-found' }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }
