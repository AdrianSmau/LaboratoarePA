package Entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "genres")
@NamedQueries({@NamedQuery(name = "Genre.findByID", query = "SELECT x FROM Genre x WHERE x.id=:id"), @NamedQuery(name = "Genre.findByName", query = "SELECT x FROM Genre x WHERE x.name=:name")})
public class Genre implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "ID")
    private int id;
    @Column(name = "NAME")
    private String name;
    @JoinColumn(name = "GENRE_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "genreOf"))
    @ManyToOne
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
        return "\nGenre's ID: " + this.id + ", Genre's Name: " + this.name + "\n";
    }
}
