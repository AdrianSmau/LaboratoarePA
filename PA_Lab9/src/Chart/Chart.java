package Chart;

import Entities.Movie;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class Chart {
    private final Set<Movie> movies = new TreeSet<>(new chartComp());
    private final Date creationDate;
    private final String name;

    public Chart(String name) {
        this.name = name;
        this.creationDate = Date.valueOf(LocalDate.now().toString());
    }

    public void addMovie(Movie x) {
        movies.add(x);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int index = 0;
        for (Movie x : this.movies) {
            index++;
            builder.append("Place #" + index + ": ");
            builder.append(x.getTitle());
            builder.append(" - ");
            builder.append(x.getScore());
            builder.append("\n");
        }
        return "\nChart Name: " + this.name + ", Chart Creation Date: " + this.creationDate + "\nTop Movies: \n" + builder;
    }
}

class chartComp implements Comparator<Movie> {
    @Override
    public int compare(Movie o1, Movie o2) {
        return (int) (o2.getScore() - o1.getScore());
    }
}
