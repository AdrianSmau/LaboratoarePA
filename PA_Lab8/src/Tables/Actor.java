package Tables;

import java.sql.Date;

public class Actor {
    private int id;
    private String name;
    private float height;
    private Date birth_date;
    private Date death_date;

    public Actor() {
        this.id = -1;
        this.name = null;
        this.height = 0;
        this.birth_date = null;
        this.death_date = null;
    }

    public Actor(int id, String name, float height, Date birth_date, Date death_date) {
        this.id = id;
        this.name = name;
        this.height = height;
        this.birth_date = birth_date;
        this.death_date = death_date;
    }

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

    public Date getDeath_date() {
        return death_date;
    }

    public void setDeath_date(Date death_date) {
        this.death_date = death_date;
    }

    @Override
    public String toString() {
        return "\nActor's ID: " + this.id + ", Actor's Name: " + this.name + ", Actor's Height: " + this.height + ", Actor's Date of Birth: " + this.birth_date + ", Actor's Date of Death: " + this.death_date;
    }
}
