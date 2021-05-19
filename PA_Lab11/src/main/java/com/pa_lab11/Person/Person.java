package com.pa_lab11.Person;

import com.pa_lab11.Relationship.Relationship;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "person1", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Relationship> friends = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "person2", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Relationship> friendsOf = new ArrayList<>();

    public Person(String name) {
        this.name = name;
    }

    public Person() {
    }

    public void addFriend(Relationship r) {
        this.friends.add(r);
    }

    public void addFriendOf(Relationship r) {
        this.friendsOf.add(r);
    }

    public List<Relationship> getFriends() {
        return friends;
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
