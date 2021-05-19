package com.pa_lab11.Relationship;

import com.pa_lab11.Person.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RelationshipRepository extends JpaRepository<Relationship, Integer> {
    @Query("SELECT s FROM Relationship s WHERE s.person1.name = ?1 and s.person2.name = ?2")
    Optional<Relationship> findRelationshipByNames(String name1, String name2);

    @Query("SELECT s FROM Person s WHERE s.name = ?1")
    Optional<Person> findPersonByName(String name);
}
