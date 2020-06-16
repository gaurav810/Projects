import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PersonListComponent } from './person-list/person-list.component';
import { PersonDetailsComponent } from './person-details/person-details.component';
import { CreatePersonComponent } from './create-person/create-person.component';
import { UpdatePersonComponent } from './update-person/update-person.component';


const routes: Routes = [
  {path: '', redirectTo: 'persons', pathMatch: 'full'},
  {path: 'persons', component: PersonListComponent},
  {path: 'persons/details/:id', component: PersonDetailsComponent},
  {path: 'persons/create', component: CreatePersonComponent},
  {path: 'persons/update/:id', component: UpdatePersonComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
