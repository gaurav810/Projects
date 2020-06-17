import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  constructor(private router: Router) { }

  userName: string;
  ngOnInit(): void {
    var userObj = JSON.parse(localStorage.getItem('loggedInUser'));
    this.userName = userObj.name;
    console.log(this.userName);
  }

  
  
}
