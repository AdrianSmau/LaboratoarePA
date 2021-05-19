package com.pa_lab11.Relationship;

import com.pa_lab11.Person.Person;

import javax.persistence.*;

@Entity
@Table(name = "relationships")
public class Relationship {
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

    @ManyToOne
    private Person person1;

    @ManyToOne
    private Person person2;

    public Person getPerson1() {
        return person1;
    }

    public void setPerson1(Person person1) {
        this.person1 = person1;
    }

    public Person getPerson2() {
        return person2;
    }

    public void setPerson2(Person person2) {
        this.person2 = person2;
    }
}
