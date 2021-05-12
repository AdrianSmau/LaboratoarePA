package com.pa_lab11.Person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    @Query("SELECT s FROM Person s WHERE s.name = ?1")
    Optional<Person> findPersonByName(String name);
    @Query("SELECT s FROM Person s WHERE s.id=?1")
    Optional<Person> findPersonById(Integer id);
}
