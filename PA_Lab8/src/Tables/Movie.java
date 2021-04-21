package Tables;

import java.sql.Date;

public class Movie {
    private int id;
    private String title;
    private Date release_date;
    private int duration;
    private float score;

    public Movie() {
        this.id = -1;
        this.title = null;
        this.release_date = null;
        this.duration = 0;
        this.score = 0;
    }

    public Movie(int id, String title, Date release_date, int duration, float score) {
        this.id = id;
        this.title = title;
        this.release_date = release_date;
        this.duration = duration;
        this.score = score;
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
