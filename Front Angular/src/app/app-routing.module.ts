import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthComponent } from './auth/auth.component';


const routes: Routes = [
  { path: 'auth', component: AuthComponent,    
    children: [
      {
        path: '', // child route path
        component: AuthComponent, // child route component that the router renders
      }, ]
  },
  // otherwise redirect to home
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { relativeLinkResolution: 'legacy' })],
  exports: [RouterModule]
})
export class AppRoutingModule { }