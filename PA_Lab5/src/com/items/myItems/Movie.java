package com.items.myItems;

import com.items.Item;

import java.net.URI;

public class Movie extends Item {
    private String director;
    private String leadRole;
    private double imdbScore;
    private int releaseYear;
    private URI imdbLink;

    public Movie(String id, String name, String location, String director, String leadRole, double imdbScore, int releaseYear, URI uri) {
        super(id, name, location);
        this.director = director;
        this.leadRole = leadRole;
        this.imdbScore = imdbScore;
        this.releaseYear = releaseYear;
        this.imdbLink = uri;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getLeadRole() {
        return leadRole;
    }

    public void setLeadRole(String leadRole) {
        this.leadRole = leadRole;
    }

    public double getImdbScore() {
        return imdbScore;
    }

    public void setImdbScore(double imdbScore) {
        this.imdbScore = imdbScore;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public URI getImdbLink() {
        return imdbLink;
    }

    public void setImdbLink(URI imdbLink) {
        this.imdbLink = imdbLink;
    }

    @Override
    public String toString() {
        return "Movie's ID: " + super.getId() + "\nMovie's Name: " + super.getName() + "\nMovie's Location in the Local System: " + super.getLocation()
                + "\nMovie's Director: " + this.director + "\nMovie's IMDB Score: " + this.imdbScore + "\nMovie's Lead Actor: " + this.leadRole + "\nMovie's Release Year: " + this.releaseYear
                + "\nMovie's IMDB Page Link: " + this.imdbLink;
    }
}
