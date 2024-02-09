import { Routes } from '@angular/router';
import { SkeletonComponent } from './layout/skeleton/skeleton.component';
import { TutorialComponent } from './welcome/tutorial/tutorial.component';
import { AnalysisComponent } from './modules/analysis/analysis.component';
import { PruebaComponent } from './prueba/prueba.component';

export const routes: Routes = [
    {
        path: '',
        component: SkeletonComponent,
        children: [
            { path: '', component: TutorialComponent },
            { path: 'analysis', component: AnalysisComponent },
            { path: 'prueba', component: PruebaComponent }
        ]
    }
];