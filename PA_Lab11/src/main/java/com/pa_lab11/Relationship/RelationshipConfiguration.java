package com.pa_lab11.Relationship;

import com.pa_lab11.Person.Person;
import com.pa_lab11.Person.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class RelationshipConfiguration {
    @Bean
    CommandLineRunner commandLineRunnerRelation(RelationshipRepository repositoryRelation, PersonRepository repositoryPerson) {
        return args -> {
            List<Person> personList = repositoryPerson.findAll();
            Person p1 = personList.get(0);
            Person p2 = personList.get(1);
            Relationship r1 = new Relationship();
            r1.setPerson1(p1);
            r1.setPerson2(p2);
            Relationship r2 = new Relationship();
            r2.setPerson1(p2);
            r2.setPerson2(p1);

            p1.addFriend(r1);
            p1.addFriendOf(r2);
            p2.addFriend(r2);
            p2.addFriendOf(r1);

            repositoryRelation.saveAll(List.of(r1, r2));
        };
    }
}
