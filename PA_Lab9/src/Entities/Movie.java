package Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movies")
@NamedQueries({@NamedQuery(name = "Movie.findByID", query = "SELECT x FROM Movie x WHERE x.id=:id"),@NamedQuery(name="Movie.findByName", query = "SELECT x FROM Movie x WHERE x.title=:title")})
public class Movie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "ID")
    private int id;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "RELEASE_DATE")
    private Date release_date;
    @Column(name = "DURATION")
    private int duration;
    @Column(name = "SCORE")
    private float score;
    @OneToOne(mappedBy = "movie")
    private Director director;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movie")
    private List<Genre> genres = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movie")
    private List<Actor> actors = new ArrayList<>();

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "\nMovie ID: " + this.id + ", Movie Title: " + this.title + ", Movie Release Date: " + this.release_date + ", Movie's Duration in minutes: " + this.duration + ", Movie Score: " + this.score + "\n";
    }
}
