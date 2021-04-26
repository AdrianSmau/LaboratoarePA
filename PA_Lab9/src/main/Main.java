package main;

import AbstractFactoryImpl.*;
import BonusAlgorithm.InputGenerator;
import BonusAlgorithm.PlaylistMakerAlgorithm;
import Chart.Chart;
import DatabaseConnection.DatabaseConnection;
import Entities.Actor;
import Entities.Director;
import Entities.Genre;
import Entities.Movie;
import EntityManagerFactory.EntityManagerSingleton;
import Repository.Create;
import Repository.FindByID;
import Repository.FindByName;
import RepositoryPattern.ActorRepoImpl;
import RepositoryPattern.DirectorRepoImpl;
import RepositoryPattern.GenreRepoImpl;
import RepositoryPattern.MovieRepoImpl;

import javax.persistence.EntityManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            DatabaseConnection DBConn = DatabaseConnection.getInstance();
            Connection conn = DBConn.getConnection();
            Statement stmt = conn.createStatement();
            String sql = "BEGIN\n" +
                    "   FOR cur_rec IN (SELECT object_name, object_type\n" +
                    "                     FROM user_objects\n" +
                    "                    WHERE object_type IN\n" +
                    "                             ('TABLE',\n" +
                    "                              'VIEW',\n" +
                    "                              'PACKAGE',\n" +
                    "                              'PROCEDURE',\n" +
                    "                              'FUNCTION',\n" +
                    "                              'SEQUENCE',\n" +
                    "                              'SYNONYM',\n" +
                    "                              'PACKAGE BODY'\n" +
                    "                             ))\n" +
                    "   LOOP\n" +
                    "      BEGIN\n" +
                    "         IF cur_rec.object_type = 'TABLE'\n" +
                    "         THEN\n" +
                    "            EXECUTE IMMEDIATE    'DROP '\n" +
                    "                              || cur_rec.object_type\n" +
                    "                              || ' \"'\n" +
                    "                              || cur_rec.object_name\n" +
                    "                              || '\" CASCADE CONSTRAINTS';\n" +
                    "         ELSE\n" +
                    "            EXECUTE IMMEDIATE    'DROP '\n" +
                    "                              || cur_rec.object_type\n" +
                    "                              || ' \"'\n" +
                    "                              || cur_rec.object_name\n" +
                    "                              || '\"';\n" +
                    "         END IF;\n" +
                    "      EXCEPTION\n" +
                    "         WHEN OTHERS\n" +
                    "         THEN\n" +
                    "            DBMS_OUTPUT.put_line (   'FAILED: DROP '\n" +
                    "                                  || cur_rec.object_type\n" +
                    "                                  || ' \"'\n" +
                    "                                  || cur_rec.object_name\n" +
                    "                                  || '\"'\n" +
                    "                                 );\n" +
                    "      END;\n" +
                    "   END LOOP;\n" +
                    "END;";
            try {
                stmt.executeUpdate(sql);
            } catch (SQLSyntaxErrorException ex) {
                ex.printStackTrace();
            } finally {
                System.out.println("\n ----- [NOTIFICATION] Database wiped clean!... ----- \n");
            }
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        Create persistManager = new Create();
        FindByID idFinder = new FindByID();
        FindByName nameFinder = new FindByName();

        EntityManagerSingleton singleton = EntityManagerSingleton.getInstance();

        //Persisting
        EntityManager entityManager = singleton.getEntityManagerFactory().createEntityManager();
        persistManager.setEntityManager(entityManager);
        idFinder.setEntityManager(entityManager);
        nameFinder.setEntityManager(entityManager);

        entityManager.getTransaction().begin();

        System.out.println("\n ----- COMPULSORY ----- \n");

        //Creating the Actors
        Actor a1 = new Actor();
        a1.setName("Andrew Smith");
        a1.setHeight(1.87f);
        a1.setBirth_date(Date.valueOf("2001-11-21"));
        a1.setDeath_date(null);

        Actor a2 = new Actor();
        a2.setName("Manuela Lopez");
        a2.setHeight(1.67f);
        a2.setBirth_date(Date.valueOf("1945-02-04"));
        a2.setDeath_date(Date.valueOf("2009-05-11"));

        List<Actor> actorList = new ArrayList<>();
        actorList.add(a1);
        actorList.add(a2);

        //Creating the Genres
        Genre g1 = new Genre();
        g1.setName("Crime");

        Genre g2 = new Genre();
        g2.setName("Drama");

        List<Genre> genreList = new ArrayList<>();
        genreList.add(g1);
        genreList.add(g2);

        //Creating the Director
        Director d = new Director();
        d.setName("Johnny West");

        //Creating the Movie
        Movie m = new Movie();
        m.setTitle("Sample Movie");
        m.setDuration(100);
        m.setScore(8.8f);
        m.setRelease_date(Date.valueOf("2000-10-15"));

        //Setting the Foreign Keys
        m.setActors(actorList);
        m.setGenres(genreList);
        m.setDirector(d);

        g1.setMovie(m);
        g2.setMovie(m);
        d.setMovie(m);
        a1.setMovie(m);
        a2.setMovie(m);

        Movie m1 = new Movie();
        m1.setTitle("Sample Movie 2");
        m1.setDuration(120);
        m1.setScore(6.3f);
        m1.setRelease_date(Date.valueOf("2001-11-15"));

        Movie m2 = new Movie();
        m2.setTitle("Sample Movie 3");
        m2.setDuration(102);
        m2.setScore(7.7f);
        m2.setRelease_date(Date.valueOf("2002-05-15"));

        persistManager.persist(m);
        persistManager.persist(a1);
        persistManager.persist(a2);
        persistManager.persist(g1);
        persistManager.persist(g2);
        persistManager.persist(d);

        System.out.println("\nFinding the Entity with the ID = 1!...");
        Object findResult = idFinder.findByID(1);
        System.out.println(findResult);

        System.out.println("\nFinding the Entity with the ID = 4!...");
        findResult = idFinder.findByID(4);
        System.out.println(findResult);

        System.out.println("\nFinding the Entities with the name = 'Andrew Smith'!...");
        List<Object> nameResults = nameFinder.findByName("Andrew Smith");
        List<Object> nameResultsFiltered = new ArrayList<>();
        for (int i = 0; i < nameResults.size(); i++) {
            if (nameResults.get(i) != null) {
                if (nameResults.get(i) instanceof Movie)
                    nameResultsFiltered.add(nameResults.get(i));
                if (nameResults.get(i) instanceof Actor)
                    nameResultsFiltered.add(nameResults.get(i));
                if (nameResults.get(i) instanceof Genre)
                    nameResultsFiltered.add(nameResults.get(i));
                if (nameResults.get(i) instanceof Director)
                    nameResultsFiltered.add(nameResults.get(i));
            }
        }
        System.out.println("\nFound " + nameResultsFiltered.size() + " result(s)!...");
        System.out.println(nameResultsFiltered);

        System.out.println("\n ----- OPTIONAL ----- \n");

        Chart c = new Chart("Top Movies");
        c.addMovie(m);
        c.addMovie(m1);
        c.addMovie(m2);

        System.out.println("Generating the Chart!...");
        System.out.println(c);

        System.out.println("\nI will show how my RepositoryPattern implementation works!...\n");

        //Creating the Actors
        Actor aa1 = new Actor();
        aa1.setName("Jon Deere");
        aa1.setHeight(1.91f);
        aa1.setBirth_date(Date.valueOf("1992-10-11"));
        aa1.setDeath_date(null);

        Actor aa2 = new Actor();
        aa2.setName("Jennifer Roman");
        aa2.setHeight(1.57f);
        aa2.setBirth_date(Date.valueOf("1921-02-04"));
        aa2.setDeath_date(Date.valueOf("1978-05-11"));

        List<Actor> actorList0 = new ArrayList<>();
        actorList0.add(aa1);
        actorList0.add(aa2);

        //Creating the Genres
        Genre gg1 = new Genre();
        gg1.setName("Sci-Fi");

        Genre gg2 = new Genre();
        gg2.setName("Noir");

        List<Genre> genreList0 = new ArrayList<>();
        genreList0.add(gg1);
        genreList0.add(gg2);

        //Creating the Director
        Director dd = new Director();
        dd.setName("William Peer");

        //Creating the Movie
        Movie mm = new Movie();
        mm.setTitle("Optional Sample Movie");
        mm.setDuration(85);
        mm.setScore(9.2f);
        mm.setRelease_date(Date.valueOf("2005-08-01"));

        //Setting the Foreign Keys
        mm.setActors(actorList0);
        mm.setGenres(genreList0);
        mm.setDirector(dd);

        gg1.setMovie(mm);
        gg2.setMovie(mm);
        dd.setMovie(mm);
        aa1.setMovie(mm);
        aa2.setMovie(mm);

        MovieRepoImpl movieRepo = new MovieRepoImpl();
        ActorRepoImpl actorRepo = new ActorRepoImpl();
        GenreRepoImpl genreRepo = new GenreRepoImpl();
        DirectorRepoImpl directorRepo = new DirectorRepoImpl();

        System.out.println("Persisting the Objects in the Database!...\n");

        movieRepo.createInDatabase(entityManager, mm);
        actorRepo.createInDatabase(entityManager, aa1);
        actorRepo.createInDatabase(entityManager, aa2);
        genreRepo.createInDatabase(entityManager, gg1);
        genreRepo.createInDatabase(entityManager, gg2);
        directorRepo.createInDatabase(entityManager, dd);

        System.out.println("Finding the Movie with the name 'Optional Sample Movie'!...");
        System.out.println(movieRepo.getByName(entityManager, "Optional Sample Movie"));

        System.out.println("Finding the Actor with the ID = 8!...");
        System.out.println(actorRepo.getByID(entityManager, 8));

        System.out.println("\nLet's open a connection in order to show our AbstractFactory implementations!...\n");
        System.out.println("\n ----- Firstly, let's show the JPA Implementations!... ----- \n");
        MovieFactoryJPA movieFactoryJPA = (MovieFactoryJPA) FactoryProviderJPA.getFactory("Movie");
        ActorFactoryJPA actorFactoryJPA = (ActorFactoryJPA) FactoryProviderJPA.getFactory("Actor");
        System.out.println("\n ----- [JPA] Getting the Movie with the ID = 1!... ----- \n");
        System.out.println(movieFactoryJPA.getByID(entityManager, 1));
        Actor JPAActor = new Actor();
        JPAActor.setName("JPAActor");
        JPAActor.setHeight(1.78f);
        JPAActor.setBirth_date(Date.valueOf("1970-01-01"));
        System.out.println("\n ----- [JPA] Persisting the JPAActor into the DataBase!... ----- ");
        actorFactoryJPA.create(entityManager, JPAActor);
        System.out.println(JPAActor);
        System.out.println("\n ----- Secondly, let's show the JDBC Implementations!... ----- \n");
        try {
            DatabaseConnection DBConn = DatabaseConnection.getInstance();
            Connection conn = DBConn.getConnection();
            MovieFactoryJDBC movieFactoryJDBC = (MovieFactoryJDBC) FactoryProviderJDBC.getFactory("Movie");
            Movie JDBCMovie = new Movie();
            JDBCMovie.setId(6270);
            JDBCMovie.setTitle("JDBCMovie");
            JDBCMovie.setScore(5.8f);
            JDBCMovie.setRelease_date(Date.valueOf("2021-01-01"));
            JDBCMovie.setDuration(76);
            System.out.println("\n ----- [JDBC] Inserting the JDBCMovie into the DataBase!... ----- \n");
            movieFactoryJDBC.create(conn, JDBCMovie);
            System.out.println(JDBCMovie);
            System.out.println("\n ----- [JDBC] Getting the JDBCMovie based on its Name!... ----- \n");
            System.out.println(movieFactoryJDBC.getByName(conn, "JDBCMovie"));
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        System.out.println("\n ----- BONUS ----- \n");

        System.out.println(" ----- Generating algorithm input!... ----- \n");

        InputGenerator inputGenerator = new InputGenerator(300, 1, 5, entityManager);
        long startTime = System.currentTimeMillis();
        inputGenerator.generateInput();
        long inputGeneratingTime = System.currentTimeMillis() - startTime;

        System.out.println("\n ----- Generating Movie Playlist!... ----- \n");

        PlaylistMakerAlgorithm playlistMaker = new PlaylistMakerAlgorithm(entityManager);
        startTime = System.currentTimeMillis();
        Map<String, List<Movie>> resultPlaylist = playlistMaker.generatePlaylist();
        long playlistGeneratingTime = System.currentTimeMillis() - startTime;
        int index = 1;
        for (String x : resultPlaylist.keySet()) {
            System.out.println("Day #" + index + " - related by Director: '" + x + "': \n\tMovie #1: '" + resultPlaylist.get(x).get(0).getTitle() + "'\n\tMovie #2: '" + resultPlaylist.get(x).get(1).getTitle() + "'");
            index++;
        }

        System.out.println("\n ----- Time taken by generating the Input: " + inputGeneratingTime + " millisecond(s), tested on an input of " + inputGenerator.getDirectorNum() + " Director(s), each with between " + inputGenerator.getMoviePerDirectorMinBound() + " and " + inputGenerator.getGetMoviePerDirectorMaxBound() + " Movie(s) generated additionally!... ----- \n");
        System.out.println(" ----- Time taken by generating the Playlist: " + playlistGeneratingTime + " millisecond(s), tested on an input of " + inputGenerator.getDirectorNum() + " Director(s), each with between " + inputGenerator.getMoviePerDirectorMinBound() + " and " + inputGenerator.getGetMoviePerDirectorMaxBound() + " Movie(s) generated additionally!... ----- \n");

        entityManager.getTransaction().commit();
        entityManager.close();
        singleton.closeEntityManagerFactory();
    }
}
