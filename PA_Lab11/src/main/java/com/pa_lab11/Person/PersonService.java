package com.pa_lab11.Person;

import com.pa_lab11.Exceptions.NameTakenException;
import com.pa_lab11.Exceptions.UnexistentPersonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
            throw new NameTakenException();
        this.personRepository.save(p);
    }

    public List<Person> getKMostPopular(Integer k) {
        return this.personRepository.findKMostPopular().stream().limit(k).collect(Collectors.toList());
    }

    public List<Person> getKLeastPopular(Integer k) {
        return this.personRepository.findKLeastPopular().stream().limit(k).collect(Collectors.toList());
    }

    public void deletePerson(String name) {
        Optional<Person> personOptional = this.personRepository.findPersonByName(name);
        if (personOptional.isEmpty())
            throw new UnexistentPersonException();
        else
            this.personRepository.deleteById(personOptional.get().getId());
    }

    public void updatePerson(Integer id, String name) {
        Person person = this.personRepository.findById(id).orElseThrow(() -> new UnexistentPersonException());
        if (name != null && name.length() > 0 && !Objects.equals(person.getName(), name)) {
            this.personRepository.updateById(id, name);
        }
    }
}
