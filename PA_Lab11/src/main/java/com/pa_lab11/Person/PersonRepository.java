package com.pa_lab11.Person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    @Transactional
    @Modifying
    @Query("UPDATE Person s SET s.name = ?2 WHERE s.id=?1")
    void updateById(Integer id, String name);

    @Transactional
    @Modifying
    @Query("DELETE FROM Person s WHERE s.id = ?1")
    void deleteById(Integer id);

    @Query("SELECT s FROM Person s WHERE s.name = ?1")
    Optional<Person> findPersonByName(String name);

    @Query(value = "SELECT s FROM Person s ORDER BY s.friends.size DESC")
    List<Person> findKMostPopular();

    @Query(value = "SELECT s FROM Person s ORDER BY s.friends.size ASC")
    List<Person> findKLeastPopular();
}
