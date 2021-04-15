package DAO;

import Tables.Genre;
import Tables.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOImplementation implements DAOInterface {
    private final Connection conn;

    public DAOImplementation(Connection conn) {
        this.conn = conn;
    }

    @Override
    public int addMovie(Movie movie) throws SQLException {
        String query = "INSERT INTO movies VALUES (?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, movie.getId());
        ps.setString(2, movie.getTitle());
        ps.setDate(3, movie.getRelease_date());
        ps.setInt(4, movie.getDuration());
        ps.setDouble(5, movie.getScore());
        return ps.executeUpdate();
    }

    @Override
    public int addGenre(Genre genre) throws SQLException {
        String query = "INSERT INTO genres VALUES (?,?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, genre.getId());
        ps.setString(2, genre.getName());
        return ps.executeUpdate();
    }

    @Override
    public Movie findMovieByID(int id) throws SQLException {
        String query = "SELECT * FROM movies WHERE id_movie = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        Movie tempMovie = new Movie();
        ResultSet rs = ps.executeQuery();
        boolean check = false;

        while (rs.next()) {
            check = true;
            tempMovie.setId(rs.getInt("id_movie"));
            tempMovie.setTitle(rs.getString("title"));
            tempMovie.setRelease_date(rs.getDate("release_date"));
            tempMovie.setDuration(rs.getInt("duration"));
            tempMovie.setScore(rs.getDouble("score"));
        }
        if (check)
            return tempMovie;
        else
            return null;
    }

    @Override
    public Genre findGenreByID(int id) throws SQLException {
        String query = "SELECT * FROM genres WHERE id_genre = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        Genre tempGenre = new Genre();
        ResultSet rs = ps.executeQuery();
        boolean check = false;

        while (rs.next()) {
            check = true;
            tempGenre.setId(rs.getInt("id_genre"));
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
            tempMovie.setId(rs.getInt("id_movie"));
            tempMovie.setTitle(rs.getString("title"));
            tempMovie.setRelease_date(rs.getDate("release_date"));
            tempMovie.setDuration(rs.getInt("duration"));
            tempMovie.setScore(rs.getDouble("score"));
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
            tempGenre.setId(rs.getInt("id_genre"));
            tempGenre.setName(rs.getString("name"));
        }
        if (check)
            return tempGenre;
        else
            return null;
    }
}
