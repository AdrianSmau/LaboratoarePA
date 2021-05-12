package com.pa_lab11.Person;

import javax.persistence.*;

@Entity
@Table(name = "persons")
public class Person {
    @Id
    @SequenceGenerator(
            name = "person_seq",
            sequenceName = "person_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "person_seq"
    )
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NAME")
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public Person() {
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
