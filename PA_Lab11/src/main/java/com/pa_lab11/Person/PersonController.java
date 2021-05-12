package com.pa_lab11.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/persons")
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<Person> getPersons() {
        return this.personService.getPersons();
    }

    @GetMapping("/count")
    public int countPersons() {
        return this.personService.getPersons().size();
    }

    @GetMapping("/get/{name}")
    public Person getPerson(@PathVariable("name") String name) {
        return this.personService.getPersons().stream()
                .filter(p -> p.getName().equals(name)).findFirst().orElse(null);
    }

    @PostMapping
    public void registerPerson(@RequestParam String name) {
        this.personService.addPerson(new Person(name));
    }

    @PostMapping(value = "/obj", consumes = "application/json")
    public ResponseEntity<String> registerPersonBody(@RequestBody Person p) {
        this.personService.addPerson(p);
        return new ResponseEntity<>("Person registered successfully!...", HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete/{personName}")
    public void deletePerson(@PathVariable("personName") String name) {
        this.personService.deletePerson(name);
    }

    @PutMapping("/update/{id}")
    public void updatePerson(@PathVariable Integer id, @RequestParam String name) {
        this.personService.updatePerson(id, name);
    }

/*

 ----- EXAMPLES -----

###
POST http://localhost:8088/api/v1/persons/?name=john
name: John

###
POST http://localhost:8088/api/v1/persons/obj
Content-Type: application/json

{
"name": "John"
}

###
DELETE http://localhost:8088/api/v1/persons/delete/BeanPerson1

###
PUT http://localhost:8088/api/v1/persons/update/1/?name=AverageJoe
name: AverageJoe
*/
}
