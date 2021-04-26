package Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "actors")
@NamedQueries({@NamedQuery(name = "Actor.findByID", query = "SELECT x FROM Actor x WHERE x.id=:id"), @NamedQuery(name = "Actor.findByName", query = "SELECT x FROM Actor x WHERE x.name=:name")})
public class Actor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "ID")
    private int id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "HEIGHT")
    private float height;
    @Column(name = "BIRTH_DATE")
    private Date birth_date;
    @Column(name = "DEATH_DATE")
    private Date death_date;
    @JoinColumn(name = "ACTOR_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "actingIn"))
    @ManyToOne
    private Movie movie;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDeath_date() {
        return death_date;
    }

    public void setDeath_date(Date death_date) {
        this.death_date = death_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "\nActor's ID: " + this.id + ", Actor's Name: " + this.name + ", Actor's Height: " + this.height + ", Actor's Date of Birth: " + this.birth_date + ", Actor's Date of Death: " + this.death_date;
    }
}
