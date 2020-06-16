import { Component, OnInit } from '@angular/core';
import { Person } from '../person';
import { ActivatedRoute, Router } from '@angular/router';
import { PersonService } from '../person.service';

@Component({
  selector: 'app-update-person',
  templateUrl: './update-person.component.html',
  styleUrls: ['./update-person.component.css']
})
export class UpdatePersonComponent implements OnInit {

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

  updatePerson() {
    this.personService.updatePerson(this.person, this.id)
      .subscribe(data => {
        console.log(data);
        this.redirectToPersonList();      
      }, error => console.log(error));
  }

  redirectToPersonList() {
    this.router.navigate(['persons']);
  }

}
