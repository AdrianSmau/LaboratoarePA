package com.pa_lab11.Relationship;

import com.pa_lab11.Exceptions.NameTakenException;
import com.pa_lab11.Exceptions.UnexistentPersonException;
import com.pa_lab11.Person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RelationshipService {
    private final RelationshipRepository relationshipRepository;

    @Autowired
    public RelationshipService(RelationshipRepository repo) {
        this.relationshipRepository = repo;
    }

    public List<Relationship> getFriendships() {
        return this.relationshipRepository.findAll();
    }

    public void addRelationship(Relationship r) {
        Optional<Relationship> relationshipOptional = this.relationshipRepository.findRelationshipByNames(r.getPerson1().getName(), r.getPerson2().getName());
        if (relationshipOptional.isPresent())
            throw new NameTakenException();
        Optional<Person> personOptional1 = this.relationshipRepository.findPersonByName(r.getPerson1().getName());
        Optional<Person> personOptional2 = this.relationshipRepository.findPersonByName(r.getPerson2().getName());
        if (personOptional1.isEmpty() || personOptional2.isEmpty()) {
            throw new UnexistentPersonException();
        }
        this.relationshipRepository.save(r);
    }
}
