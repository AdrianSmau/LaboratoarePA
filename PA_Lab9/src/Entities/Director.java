package Entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "directors")
@NamedQueries({@NamedQuery(name = "Director.findByID", query = "SELECT x FROM Director x WHERE x.id=:id"),@NamedQuery(name="Director.findByName", query = "SELECT x FROM Director x WHERE x.name=:name")})
public class Director implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "ID")
    private int id;
    @Column(name = "NAME")
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DIRECTOR_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "directedBy"))
    private Movie movie;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "\nDirector's ID: " + this.id + ", Director's Name: " + this.name;
    }
}
