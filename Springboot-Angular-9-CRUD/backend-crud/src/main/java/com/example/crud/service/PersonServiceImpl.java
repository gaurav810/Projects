package com.example.crud.service;

import com.example.crud.entity.Person;
import com.example.crud.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<Person> listOfPerson() {

        return personRepository.findAll();
    }

    @Override
    public Person save(Person person) {

        return personRepository.save(person);
    }

    @Override
    public Person update(Long id, Person requestedPerson) {

        Optional<Person> personDB = personRepository.findById(id);
        Person person = personDB.get();
        person.setName(requestedPerson.getName());
        person.setEmailId(requestedPerson.getEmailId());
        person.setContactNo(requestedPerson.getContactNo());
        return personRepository.save(person);
    }

    @Override
    public Person getDetailsById(Long id) {

        Optional<Person> personDB = personRepository.findById(id);
        return personDB.get();
    }

    @Override
    public void deletePerson(Long id) {

        personRepository.delete(this.getDetailsById(id));
    }
}
