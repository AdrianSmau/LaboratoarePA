package Tables;

public class Director {
    private int id;
    private String name;

    public Director() {
        this.id = -1;
        this.name = null;
    }

    public Director(int id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "\nDirector's ID: " + this.id + ", Director's Name: " + this.name;
    }
}
