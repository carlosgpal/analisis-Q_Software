import { Routes } from '@angular/router';
import { SkeletonComponent } from './layout/skeleton/skeleton.component';
import { TutorialComponent } from './welcome/tutorial/tutorial.component';
import { AnalysisComponent } from './components/analysis/analysis.component';
import { MappingBitsComponent } from './components/mapping-bits/mapping-bits.component';
import { MappingGatesComponent } from './components/mapping-gates/mapping-gates.component';
import { MappingMeasuresComponent } from './components/mapping-measures/mapping-measures.component';

export const routes: Routes = [
    {
        path: '',
        component: SkeletonComponent,
        children: [
            { path: '', component: TutorialComponent },
            { path: 'analysis', component: AnalysisComponent },
            { path: 'mappingBits', component: MappingBitsComponent },
            { path: 'mappingGates', component: MappingGatesComponent },
            { path: 'mappingMeasures', component: MappingMeasuresComponent },
        ]
    }
];