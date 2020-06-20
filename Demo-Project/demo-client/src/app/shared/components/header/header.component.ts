import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CommonServiceService } from 'src/app/services/common-service.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private router: Router, private commonService: CommonServiceService) { }

  ngOnInit(): void {
  }

  logoutUser() {
    localStorage.removeItem('token');
    localStorage.removeItem('loggedInUser');
    this.commonService.message = 'User is logged out Successfully.';
    this.router.navigate(['/login']);
  }

}
