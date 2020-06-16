import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RegisterUserService } from './service/register-user.service';
import { RegisterUser } from './register-user';
import { CommonServiceService } from '../services/common-service.service';

@Component({
  selector: 'app-register-user',
  templateUrl: './register-user.component.html',
  styleUrls: ['./register-user.component.css']
})
export class RegisterUserComponent implements OnInit {

  user: RegisterUser = new RegisterUser();
  constructor(private registerUserService: RegisterUserService, private router: Router,
              private commonService: CommonServiceService) { }

  ngOnInit(): void {
  }

  saveUser() {
    this.registerUserService.createUser(this.user)
      .subscribe(data => {
          console.log(data);
          this.user = new RegisterUser();
          this.commonService.message = 'User Created Successfully.';
          this.redirectToLoginPage();
      }, error => console.log(error));
  }

  redirectToLoginPage() {
    this.router.navigate(['login']);
  }

}
