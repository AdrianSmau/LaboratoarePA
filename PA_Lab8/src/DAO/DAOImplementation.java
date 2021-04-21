package DAO;

import Tables.Actor;
import Tables.Director;
import Tables.Genre;
import Tables.Movie;
import freemarker.core.ParseException;
import freemarker.template.*;

import java.awt.*;
import java.io.*;
import java.sql.Date;
import java.sql.*;
import java.util.List;
import java.util.*;
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
            ps.setString(2, actor.getName());
            ps.setFloat(3, actor.getHeight());
            ps.setDate(4, actor.getBirth_date());
            ps.setDate(5, actor.getDeath_date());
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
            ps.setString(2, movie.getTitle());
            ps.setDate(3, movie.getRelease_date());
            ps.setInt(4, movie.getDuration());
            ps.setFloat(5, movie.getScore());
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
        String query = "SELECT * FROM directors WHERE id_director = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        Director tempDirector = new Director();
        ResultSet rs = ps.executeQuery();
        boolean check = false;

        while (rs.next()) {
            check = true;
            tempDirector.setId(rs.getInt("id_director"));
            tempDirector.setName(rs.getString("name"));
        }
        if (check)
            return tempDirector;
        else
            return null;
    }

    @Override
    public Actor findActorByID(int id) throws SQLException {
        String query = "SELECT * FROM actors WHERE id_actor = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        Actor tempActor = new Actor();
        ResultSet rs = ps.executeQuery();
        boolean check = false;

        while (rs.next()) {
            check = true;
            tempActor.setId(rs.getInt("id_movie"));
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
            tempDirector.setId(rs.getInt("id_director"));
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
            tempActor.setId(rs.getInt("id_movie"));
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
            tempMovie.setScore(rs.getFloat("score"));
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
            tempGenre.setId(rs.getInt("id_genre"));
            tempGenre.setName(rs.getString("name"));
        }
        if (check)
            return tempGenre;
        else
            return null;
    }

    @Override
    public List<Movie> importMovies(String CSV_Path, int n) {
        List<Movie> moviesList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_Path))) {
            int currIndex = 0;
            String currLine = null;
            try {
                currLine = br.readLine();
            } catch (IOException ex) {
                System.err.println("IOException when trying to read from CSV file!...");
            }
            while (currLine != null) {
                if (currIndex == 0) {
                    currIndex++;
                    currLine = br.readLine();
                    continue;
                }
                if (currIndex == n) // Luam primiele n de entry-uri (am putea lua mai multe)
                    break;
                String[] currValues = currLine.split(",");
                String tempTitle = currValues[1];
                Date tempDate;
                try {
                    tempDate = Date.valueOf(currValues[4]);
                } catch (IllegalArgumentException ex) {
                    tempDate = null;
                }
                int tempDuration = -1;
                int colNrMovedBy = 0;
                while (tempDuration == -1) { // iteram pana gasim un numar valid (se poate sa dam peste cuvinte despartite prin virgula, asa ca trecem peste ele)
                    try {
                        tempDuration = Integer.parseInt(currValues[6 + colNrMovedBy]);
                    } catch (NumberFormatException ex) {
                        tempDuration = -1;
                    }
                    colNrMovedBy++;
                }
                float tempScore = -1;
                while (tempScore == -1) {
                    try {
                        tempScore = Float.parseFloat(currValues[14 + colNrMovedBy]);
                    } catch (NumberFormatException ex) {
                        tempScore = -1;
                    }
                    colNrMovedBy++;
                }
                moviesList.add(new Movie(currIndex, tempTitle, tempDate, tempDuration, tempScore));
                currIndex++;

                currLine = br.readLine();
            }
        } catch (IOException ex) {
            System.err.println("IOException when trying to read from CSV file!...");
        }
        return moviesList;
    }

    @Override
    public List<Actor> importActors(String CSV_Path, int n) {
        List<Actor> actorsList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_Path))) {
            int currIndex = 0;
            String currLine = null;
            try {
                currLine = br.readLine();
            } catch (IOException ex) {
                System.err.println("IOException when trying to read from CSV file!...");
            }
            while (currLine != null) {
                if (currIndex == 0) {
                    currIndex++;
                    currLine = br.readLine();
                    continue;
                }
                if (currIndex == n) // Luam primele n entry-uri
                    break;
                String[] currValues = currLine.split(",");

                String tempName = null;
                if (currValues.length > 1) {
                    tempName = currValues[1];
                }
                boolean isAlreadyImported = false;
                if (tempName == null || Character.isDigit(tempName.charAt(0))) {
                    currIndex++;
                    currLine = br.readLine();
                    continue;
                } else {
                    for (Actor x : actorsList) {
                        if (x.getName().equals(tempName)) {
                            isAlreadyImported = true;
                            break;
                        }
                    }
                }

                int tempHeight = -1;
                if (currValues.length >= 3) {
                    try {
                        tempHeight = Integer.parseInt(currValues[3]);
                    } catch (NumberFormatException ex) {
                        tempHeight = -1;
                    }
                }

                Date tempBirthDate = null;
                int colMovedBy = 0;
                while (tempBirthDate == null) {
                    if (6 + colMovedBy >= currValues.length)
                        break;
                    try {
                        tempBirthDate = Date.valueOf(currValues[6 + colMovedBy]);
                    } catch (IllegalArgumentException ex) {
                        tempBirthDate = null;
                    }
                    colMovedBy++;
                }

                Date tempDeathDate = null;
                while (tempDeathDate == null) {
                    if (9 + colMovedBy >= currValues.length)
                        break;
                    try {
                        tempDeathDate = Date.valueOf(currValues[9 + colMovedBy]);
                    } catch (IllegalArgumentException ex) {
                        tempDeathDate = null;
                    }
                    colMovedBy++;
                }
                if (!isAlreadyImported)
                    actorsList.add(new Actor(currIndex, tempName, tempHeight, tempBirthDate, tempDeathDate));
                currIndex++;
                currLine = br.readLine();
            }
        } catch (IOException ex) {
            System.err.println("IOException when trying to read from CSV file!...");
        }
        return actorsList;
    }

    @Override
    public List<Director> importDirectors(String CSV_Path, int n) {
        //Dupa ce trecem de durata, avem coloaele cu tarile si limbile. Pentru a le sari, creem aceste liste pentru a evita erorile
        List<String> allCountries = new ArrayList<>();
        List<String> allLanguages = new ArrayList<>();
        List<String> allGenresNames = new ArrayList<>();
        List<Genre> allGenres = importGenres(CSV_Path, n);
        for (Genre x : allGenres) {
            allGenresNames.add(x.getName());
        }
        String[] locales = Locale.getISOCountries();
        for (String countryCode : locales) {
            Locale x = new Locale("", countryCode);
            allCountries.add(x.getDisplayCountry());
        }
        allCountries.add("USA");
        allCountries.add("UK");
        String[] languages = Locale.getISOLanguages();
        for (String languageCode : languages) {
            Locale x = new Locale(languageCode);
            allLanguages.add(x.getDisplayLanguage());
        }
        allLanguages.add("None");
        allLanguages.add("Mandarin");

        List<Director> directorList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_Path))) {
            int currIndex = 0;
            String currLine = null;
            try {
                currLine = br.readLine();
            } catch (IOException ex) {
                System.err.println("IOException when trying to read from CSV file!...");
            }
            while (currLine != null) {
                if (currIndex == 0) {
                    currIndex++;
                    currLine = br.readLine();
                    continue;
                }
                if (currIndex == n) // Luam primele n entry-uri
                    break;
                String[] currValues = currLine.split(",");

                int tempDuration = -1;
                int colNrMovedBy = 0;
                while (tempDuration == -1) { // ne intereseaza variabila colNrMovedBy
                    try {
                        tempDuration = Integer.parseInt(currValues[5 + colNrMovedBy]);
                    } catch (NumberFormatException ex) {
                        tempDuration = -1;
                    }
                    colNrMovedBy++;
                }
                //Ne orientam dupa coloana cu durata pentru a ajunge la coloana cu directori!

                String tempName = null;
                boolean isAlreadyImported = false;
                while (tempName == null) {
                    String parsedTempName = currValues[5 + colNrMovedBy].replace(" ", "");
                    parsedTempName = parsedTempName.replace("\"", "");
                    if (parsedTempName.length() > 0 && !allGenresNames.contains(parsedTempName) && !allCountries.contains(parsedTempName) && !allLanguages.contains(parsedTempName) && !Character.isDigit(parsedTempName.charAt(0)) && currValues[5 + colNrMovedBy].charAt(0) != ' ') {
                        tempName = currValues[5 + colNrMovedBy].replace("\"", "");
                        for (Director x : directorList) {
                            if (x.getName() != null && x.getName().equals(tempName)) {
                                isAlreadyImported = true;
                                break;
                            }
                        }
                    }
                    colNrMovedBy++;
                }
                if (!isAlreadyImported)
                    directorList.add(new Director(currIndex, tempName));
                currIndex++;
                currLine = br.readLine();
            }
        } catch (IOException ex) {
            System.err.println("IOException when trying to read from CSV file!...");
        }
        return directorList;
    }

    @Override
    public List<Genre> importGenres(String CSV_Path, int n) {
        List<Genre> genreList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_Path))) {
            int currIndex = 0;
            int currLineIndex = 0;
            String currLine = null;
            try {
                currLine = br.readLine();
            } catch (IOException ex) {
                System.err.println("IOException when trying to read from CSV file!...");
            }
            while (currLine != null) {
                if (currIndex == 0) {
                    currIndex++;
                    currLine = br.readLine();
                    continue;
                }
                if (currLineIndex == n) // Luam primele n entry-uri
                    break;
                String[] currValues = currLine.split(",");

                boolean integerReached = false;
                boolean isAlreadyImported = false;
                int colNrMovedBy = 0;
                while (!integerReached) {
                    String formattedEntry = currValues[5 + colNrMovedBy].replace(" ", "");
                    formattedEntry = formattedEntry.replace("\"", "");
                    if (!Character.isDigit(formattedEntry.charAt(0))) {
                        for (Genre x : genreList) {
                            if (x.getName() != null && x.getName().equals(formattedEntry)) {
                                isAlreadyImported = true;
                                break;
                            }
                        }
                        if (!isAlreadyImported) {
                            genreList.add(new Genre(currIndex, formattedEntry));
                            currIndex++;
                        }
                    } else {
                        integerReached = true;
                    }
                    colNrMovedBy++;
                }
                currLineIndex++;
                currLine = br.readLine();
            }
        } catch (IOException ex) {
            System.err.println("IOException when trying to read from CSV file!...");
        }
        return genreList;
    }

    @Override
    public void generateReport(List<Movie> movies, List<Actor> actors, List<Director> directors, List<Genre> genres, int n) {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setClassForTemplateLoading(DAOImplementation.class, "Template");
        cfg.setIncompatibleImprovements(new Version(2, 3, 31));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.US);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        Map<String, Object> input = new HashMap<>();
        input.put("n", n);

        input.put("CSV1Path", "C:\\Users\\adria\\Desktop\\IMDb_movies.csv");
        input.put("CSV2Path", "C:\\Users\\adria\\Desktop\\IMDb_names.csv");

        input.put("movies", movies);
        input.put("actors", actors);
        input.put("directors", directors);
        input.put("genres", genres);

        Template template = null;
        try {
            template = cfg.getTemplate("report.ftl");
        } catch (TemplateNotFoundException ex) {
            System.err.println("Template not found!...");
        } catch (MalformedTemplateNameException ex) {
            System.err.println("Malformed template name!...");
        } catch (ParseException ex) {
            System.err.println("Parse exception!...");
        } catch (IOException ex) {
            System.err.println("IO exception!...");
        }

        Writer htmlWriter = null;
        try {
            htmlWriter = new FileWriter("report.html");
        } catch (IOException ex) {
            System.err.println("IO exception caught! Couldn't instantiate the HTML Report Writer!...");
        }
        try {
            template.process(input, htmlWriter);
        } catch (TemplateException ex) {
            System.err.println("Template exception caught when trying to process the template!...");
        } catch (IOException ex) {
            System.err.println("IO exception caught when trying to process the template!...");
        }
        File htmlFile = new File("report.html");
        Desktop desktop = Desktop.getDesktop();
        if (htmlFile.exists()) {
            try {
                desktop.open(htmlFile);
            } catch (IOException ex) {
                System.err.println("IO exception caught when trying to open the generated HTML report!...");
            }
        }
    }
}
