package DAO;

import Entities.Actor;
import Entities.Director;
import Entities.Genre;
import Entities.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.locks.ReentrantLock;

public class DAOImplementation implements DAOInterface {
    private final ReentrantLock lock = new ReentrantLock(true);
    private final Connection conn;

    public DAOImplementation(Connection conn) {
        this.conn = conn;
    }

    @Override
    public synchronized int addDirector(Director director) throws SQLException {
        lock.lock();
        try {
            String query = "INSERT INTO directors VALUES (?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, director.getId());
            ps.setString(2, director.getName());
            return ps.executeUpdate();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public synchronized int addActor(Actor actor) throws SQLException {
        lock.lock();
        try {
            String query = "INSERT INTO actors VALUES (?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, actor.getId());
            ps.setString(5, actor.getName());
            ps.setFloat(4, actor.getHeight());
            ps.setDate(2, actor.getBirth_date());
            ps.setDate(3, actor.getDeath_date());
            return ps.executeUpdate();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public synchronized int addMovie(Movie movie) throws SQLException {
        lock.lock();
        try {
            String query = "INSERT INTO movies VALUES (?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, movie.getId());
            ps.setString(5, movie.getTitle());
            ps.setDate(3, movie.getRelease_date());
            ps.setInt(2, movie.getDuration());
            ps.setFloat(4, movie.getScore());
            return ps.executeUpdate();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public synchronized int addGenre(Genre genre) throws SQLException {
        lock.lock();
        try {
            String query = "INSERT INTO genres VALUES (?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, genre.getId());
            ps.setString(2, genre.getName());
            return ps.executeUpdate();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public Director findDirectorByID(int id) throws SQLException {
        String query = "SELECT * FROM directors WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        Director tempDirector = new Director();
        ResultSet rs = ps.executeQuery();
        boolean check = false;

        while (rs.next()) {
            check = true;
            tempDirector.setId(rs.getInt("id"));
            tempDirector.setName(rs.getString("name"));
        }
        if (check)
            return tempDirector;
        else
            return null;
    }

    @Override
    public Actor findActorByID(int id) throws SQLException {
        String query = "SELECT * FROM actors WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        Actor tempActor = new Actor();
        ResultSet rs = ps.executeQuery();
        boolean check = false;

        while (rs.next()) {
            check = true;
            tempActor.setId(rs.getInt("id"));
            tempActor.setName(rs.getString("name"));
            tempActor.setHeight(rs.getFloat("height"));
            tempActor.setBirth_date(rs.getDate("birth_date"));
            tempActor.setDeath_date(rs.getDate("death_date"));
        }
        if (check)
            return tempActor;
        else
            return null;
    }

    @Override
    public Director findDirectorByName(String name) throws SQLException {
        String query = "SELECT * FROM directors WHERE name = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, name);
        Director tempDirector = new Director();
        ResultSet rs = ps.executeQuery();
        boolean check = false;

        while (rs.next()) {
            check = true;
            tempDirector.setId(rs.getInt("id"));
            tempDirector.setName(rs.getString("name"));
        }
        if (check)
            return tempDirector;
        else
            return null;
    }

    @Override
    public Actor findActorByName(String name) throws SQLException {
        String query = "SELECT * FROM actors WHERE name = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, name);
        Actor tempActor = new Actor();
        ResultSet rs = ps.executeQuery();
        boolean check = false;

        while (rs.next()) {
            check = true;
            tempActor.setId(rs.getInt("id"));
            tempActor.setName(rs.getString("name"));
            tempActor.setHeight(rs.getFloat("height"));
            tempActor.setBirth_date(rs.getDate("birth_date"));
            tempActor.setDeath_date(rs.getDate("death_date"));
        }
        if (check)
            return tempActor;
        else
            return null;
    }

    @Override
    public Movie findMovieByID(int id) throws SQLException {
        String query = "SELECT * FROM movies WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        Movie tempMovie = new Movie();
        ResultSet rs = ps.executeQuery();
        boolean check = false;

        while (rs.next()) {
            check = true;
            tempMovie.setId(rs.getInt("id"));
            tempMovie.setTitle(rs.getString("title"));
            tempMovie.setRelease_date(rs.getDate("release_date"));
            tempMovie.setDuration(rs.getInt("duration"));
            tempMovie.setScore(rs.getFloat("score"));
        }
        if (check)
            return tempMovie;
        else
            return null;
    }

    @Override
    public Genre findGenreByID(int id) throws SQLException {
        String query = "SELECT * FROM genres WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        Genre tempGenre = new Genre();
        ResultSet rs = ps.executeQuery();
        boolean check = false;

        while (rs.next()) {
            check = true;
            tempGenre.setId(rs.getInt("id"));
            tempGenre.setName(rs.getString("name"));
        }
        if (check)
            return tempGenre;
        else
            return null;
    }

    @Override
    public Movie findMovieByName(String name) throws SQLException {
        String query = "SELECT * FROM movies WHERE title = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, name);
        Movie tempMovie = new Movie();
        ResultSet rs = ps.executeQuery();
        boolean check = false;

        while (rs.next()) {
            check = true;
            tempMovie.setId(rs.getInt("id"));
            tempMovie.setTitle(rs.getString("title"));
            tempMovie.setRelease_date(rs.getDate("release_date"));
            tempMovie.setDuration(rs.getInt("duration"));
            tempMovie.setScore(rs.getFloat("score"));
        }
        if (check)
            return tempMovie;
        else
            return null;
    }

    @Override
    public Genre findGenreByName(String name) throws SQLException {
        String query = "SELECT * FROM genres WHERE name = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, name);
        Genre tempGenre = new Genre();
        ResultSet rs = ps.executeQuery();
        boolean check = false;

        while (rs.next()) {
            check = true;
            tempGenre.setId(rs.getInt("id"));
            tempGenre.setName(rs.getString("name"));
        }
        if (check)
            return tempGenre;
        else
            return null;
    }
}
