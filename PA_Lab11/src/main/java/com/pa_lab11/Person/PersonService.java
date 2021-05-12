package com.pa_lab11.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getPersons() {
        return this.personRepository.findAll();
    }

    public void addPerson(Person p) {
        Optional<Person> personOptional = this.personRepository.findPersonByName(p.getName());
        if (personOptional.isPresent())
            throw new IllegalStateException("[ERROR] Name already taken!...");
        this.personRepository.save(p);
    }

    public void deletePerson(String name) {
        Optional<Person> personOptional = this.personRepository.findPersonByName(name);
        if (personOptional.isEmpty())
            throw new IllegalStateException("[ERROR] Name does not exist!...");
        else
            this.personRepository.deleteById(personOptional.get().getId());
    }

    @Transactional
    public void updatePerson(Integer id, String name) {
        Person person = this.personRepository.findById(id).orElseThrow(() -> new IllegalStateException("[ERROR] Entry not found in DataBase!..."));
        if(name != null && name.length() > 0 && !Objects.equals(person.getName(), name)){
            person.setName(name);
        }
    }
}
