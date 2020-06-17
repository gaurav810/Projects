import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DefaultComponent } from './layout/default/default.component';
import { DashboardComponent } from './modules/dashboard/dashboard.component';
import { LoginComponent } from './login/login.component';
import { RegisterUserComponent } from './register-user/register-user.component';
import { MyProfileComponent } from './modules/my-profile/my-profile.component';
import { ResetPasswordComponent } from './modules/reset-password/reset-password.component';
import { UserComponent } from './modules/user/user.component';


const routes: Routes = [
  { 
    path: '', 
    component: DefaultComponent,
    children: [{
      path: '',
      component: DashboardComponent,
    }, {
      path: 'user',
      component: UserComponent
    }, {
      path: 'my-profile',
      component: MyProfileComponent
    }, {
      path: 'reset-password',
      component: ResetPasswordComponent
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
