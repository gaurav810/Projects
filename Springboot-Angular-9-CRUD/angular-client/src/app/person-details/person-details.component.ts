import { Component, OnInit } from '@angular/core';
import { Person } from '../person';
import { ActivatedRoute, Router } from '@angular/router';
import { PersonService } from '../person.service';
import { error } from 'protractor';

@Component({
  selector: 'app-person-details',
  templateUrl: './person-details.component.html',
  styleUrls: ['./person-details.component.css']
})
export class PersonDetailsComponent implements OnInit {

  id: number;
  person: Person;

  constructor(private route: ActivatedRoute, private router: Router, private personService: PersonService) { }

  ngOnInit() {
    this.person = new Person();
    this.id = this.route.snapshot.params['id'];

    this.personService.getPersonDetailsById(this.id)
      .subscribe(data => {
          console.log(data);
          this.person = data;
      }, error => console.log(error))
  }

  redirectToPersonList() {
    this.router.navigate(['persons']);
  }
}
