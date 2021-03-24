package com.items.myItems;

import com.exceptions.InvalidYearException;
import com.items.Item;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class Song extends Item {
    private List<String> artist;
    private int releaseYear;
    private boolean onSpotify;
    private URI youtubeLink;

    public Song(String id, String name, String location, List<String> artist, int releaseYear, boolean onSpotify, URI uri) throws InvalidYearException {
        super(id, name, location);
        if (releaseYear < 0)
            throw new InvalidYearException(releaseYear);
        this.artist = artist;
        this.releaseYear = releaseYear;
        this.onSpotify = onSpotify;
        this.youtubeLink = uri;
    }

    public Song() {
        this.artist = new ArrayList<>();
        this.releaseYear = 0;
        this.onSpotify = false;
        this.youtubeLink = null;
    }

    public List<String> getArtist() {
        return artist;
    }

    public void setArtist(List<String> artist) {
        this.artist = artist;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) throws InvalidYearException {
        if (releaseYear < 0)
            throw new InvalidYearException(releaseYear);
        this.releaseYear = releaseYear;
    }

    public boolean isOnSpotify() {
        return onSpotify;
    }

    public void setOnSpotify(boolean onSpotify) {
        this.onSpotify = onSpotify;
    }

    public URI getYoutubeLink() {
        return youtubeLink;
    }

    public void setYoutubeLink(URI youtubeLink) {
        this.youtubeLink = youtubeLink;
    }

    @Override
    public String toString() {
        return "Song's ID: " + super.getId() + "\nSong's Name: " + super.getName() + "\nSong's Location in the Local System: " + super.getLocation()
                + "\nSong belongs to: " + this.artist.toString() + "\nSong's Release Year: " + this.releaseYear + "\nIs it on Spotify?: " + this.isOnSpotify()
                + "\nSong's Youtube Link: " + this.youtubeLink;
    }
}
