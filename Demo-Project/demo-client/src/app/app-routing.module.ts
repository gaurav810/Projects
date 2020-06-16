import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DefaultComponent } from './layout/default/default.component';
import { DashboardComponent } from './modules/dashboard/dashboard.component';
import { EmployeesComponent } from './modules/employees/employees.component';
import { LoginComponent } from './login/login.component';
import { RegisterUserComponent } from './register-user/register-user.component';


const routes: Routes = [
  { 
    path: '', 
    component: DefaultComponent,
    children: [{
      path: '',
      component: DashboardComponent,
    }, {
      path: 'employees',
      component: EmployeesComponent
    }]
  }, {
    path: 'login',
    component: LoginComponent
  }, {
    path: 'register',
    component: RegisterUserComponent
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
