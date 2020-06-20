import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CommonServiceService } from '../services/common-service.service';
import { LoginUser } from './login-user';
import { LoginService } from './service/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private router: Router, private commonService: CommonServiceService, 
              private loginService: LoginService) { }

  message: string;
  user: LoginUser = new LoginUser();

  ngOnInit(): void {
    this.message = this.commonService.message;
    this.commonService.message = undefined;
  }

  loginProcess() {
    
    this.loginService.loginUser(this.user)
        .subscribe(data => {
          console.log(data);
          console.log(data['token']);
          localStorage.setItem('token', data['token']);
          localStorage.setItem('loggedInUser', JSON.stringify(data['user']));
          this.router.navigate(['']);
        }, error => {
          console.log(error);
        });
  } 
}
