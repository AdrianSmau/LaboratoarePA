package main;

import ConnectionPooling.C3PODataSource;
import DAO.DAOImplementation;
import DatabaseConnection.DatabaseConnection;
import Tables.Actor;
import Tables.Director;
import Tables.Genre;
import Tables.Movie;
import ThreadPooling.RejectedExecutionHandler;
import ThreadPooling.ThreadMonitor;
import ThreadPooling.WorkerThread;

import java.sql.*;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Connecting to DataBase!...");
            DatabaseConnection DBCon = DatabaseConnection.getInstance();
            Connection conn = DBCon.getConnection();
            System.out.println("Connected successfully!...\n");
            Statement stmt = conn.createStatement();

            System.out.println("\n ----- COMPULSORY ----- \n");

            System.out.println("Wiping the database!...");

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
                System.out.println("Database wiped clean!...");
            }
            System.out.println("Attempting to create table 'movies'!...");
            sql = "CREATE TABLE movies " +
                    "(id_movie INT NOT NULL PRIMARY KEY," +
                    "title VARCHAR2(50) NOT NULL," +
                    "release_date DATE," +
                    "duration INT," +
                    "score NUMBER(*,1))";
            try {
                stmt.executeUpdate(sql);
            } catch (SQLSyntaxErrorException ex) {
                System.out.println("Table 'movies' already exists!...");
            } finally {
                System.out.println("Table 'movies' created!...");
            }
            System.out.println("\nAttempting to create table 'genres'!...");
            sql = "CREATE TABLE genres " +
                    "(id_genre INT NOT NULL PRIMARY KEY," +
                    "name VARCHAR2(20) NOT NULL)";
            try {
                stmt.executeUpdate(sql);
            } catch (SQLSyntaxErrorException ex) {
                System.out.println("Table 'genres' already exists!...");
            } finally {
                System.out.println("Table 'genres' created!...");
            }

            System.out.println("\nAttempting to create associative table 'references'!...");
            sql = "CREATE TABLE references " +
                    "(id_movie INT NOT NULL," +
                    "id_genre INT NOT NULL," +
                    "CONSTRAINT references_movie FOREIGN KEY (id_movie) REFERENCES movies(id_movie)," +
                    "CONSTRAINT references_genre FOREIGN KEY (id_genre) REFERENCES genres(id_genre))";
            try {
                stmt.executeUpdate(sql);
            } catch (SQLSyntaxErrorException ex) {
                System.out.println("Table 'references' already exists!...");
            } finally {
                System.out.println("Table 'references' created!...");
            }

            System.out.println();

            DAOImplementation dao = new DAOImplementation(conn);
            Movie[] moviess = new Movie[2];
            moviess[0] = new Movie(1, "Fight Club", Date.valueOf("1999-10-15"), 139, 8.8f);
            moviess[1] = new Movie(2, "Interstellar", Date.valueOf("2014-11-07"), 169, 8.8f);

            if (dao.findMovieByID(1) == null)
                dao.addMovie(moviess[0]);
            if (dao.findMovieByID(2) == null)
                dao.addMovie(moviess[1]);

            Movie tempMovie1 = dao.findMovieByID(1);
            Movie tempMovie2 = dao.findMovieByName("Interstellar");

            System.out.println(tempMovie1);
            System.out.println(tempMovie2);

            Genre[] genress = new Genre[2];
            genress[0] = new Genre(1, "Action");
            genress[1] = new Genre(2, "Drama");

            if (dao.findGenreByID(1) == null)
                dao.addGenre(genress[0]);
            if (dao.findGenreByID(2) == null)
                dao.addGenre(genress[1]);

            Genre tempGenre1 = dao.findGenreByID(2);
            Genre tempGenre2 = dao.findGenreByName("Action");

            System.out.println(tempGenre2);
            System.out.println(tempGenre1);

            System.out.println("\n ----- OPTIONAL ----- \n");

            System.out.println("Attempting to create table 'directors'!...");
            sql = "CREATE TABLE directors " +
                    "(id_director INT NOT NULL PRIMARY KEY," +
                    "name VARCHAR2(20) NOT NULL)";
            try {
                stmt.executeUpdate(sql);
            } catch (SQLSyntaxErrorException ex) {
                System.out.println("Table 'directors' already exists!...");
            } finally {
                System.out.println("Table 'directors' created!...");
            }

            System.out.println("\nAttempting to create table 'actors'!...");
            sql = "CREATE TABLE actors " +
                    "(id_actor INT NOT NULL PRIMARY KEY," +
                    "name VARCHAR2(20) NOT NULL," +
                    "height INT," +
                    "birth_date DATE NOT NULL," +
                    "death_date DATE)";
            try {
                stmt.executeUpdate(sql);
            } catch (SQLSyntaxErrorException ex) {
                System.out.println("Table 'actors' already exists!...");
            } finally {
                System.out.println("Table 'actors' created!...");
            }

            System.out.println("\nAttempting to create table 'actingIn'!...");
            sql = "CREATE TABLE actingIn " +
                    "(id_movie INT NOT NULL," +
                    "id_actor INT NOT NULL," +
                    "CONSTRAINT actingIn_movie FOREIGN KEY (id_movie) REFERENCES movies(id_movie)," +
                    "CONSTRAINT actingIn_actor FOREIGN KEY (id_actor) REFERENCES actors(id_actor))";
            try {
                stmt.executeUpdate(sql);
            } catch (SQLSyntaxErrorException ex) {
                System.out.println("Table 'actingIn' already exists!...");
            } finally {
                System.out.println("Table 'actingIn' created!...");
            }

            System.out.println("\nAttempting to create table 'directedBy'!...");
            sql = "CREATE TABLE directedBy " +
                    "(id_movie INT NOT NULL," +
                    "id_director INT NOT NULL," +
                    "CONSTRAINT directedBy_movie FOREIGN KEY (id_movie) REFERENCES movies(id_movie)," +
                    "CONSTRAINT directedBy_director FOREIGN KEY (id_actor) REFERENCES directors(id_director))";
            try {
                stmt.executeUpdate(sql);
            } catch (SQLSyntaxErrorException ex) {
                System.out.println("Table 'directedBy' already exists!...");
            } finally {
                System.out.println("Table 'directedBy' created!...");
            }

            int n = 50; // from here, we can change the amount of entries we want to take into consideration

            System.out.println("\n ----- List of Movies, searching within the first n entries in the CSV file!... ----- \n");
            List<Movie> movies = dao.importMovies("C:\\Users\\adria\\Desktop\\IMDb_movies.csv", n);
            System.out.println(movies);

            System.out.println("\n ----- List of Actors, searching within the first n entries in the CSV file!... ----- \n");
            List<Actor> actors = dao.importActors("C:\\Users\\adria\\Desktop\\IMDb_names.csv", n);
            System.out.println(actors);

            System.out.println("\n ----- List of Directors, searching within the first n entries in the CSV file!... ----- \n");
            List<Director> directors = dao.importDirectors("C:\\Users\\adria\\Desktop\\IMDb_movies.csv", n);
            System.out.println(directors);

            System.out.println("\n ----- List of Genres, searching within the first n entries in the CSV file!... ----- \n");
            List<Genre> genres = dao.importGenres("C:\\Users\\adria\\Desktop\\IMDb_movies.csv", n);
            System.out.println(genres);

            System.out.println("\n ----- Displaying the HTML Report!... ----- \n");
            dao.generateReport(movies, actors, directors, genres, n);

            conn.close();

            System.out.println("\n ----- BONUS ----- \n");

            RejectedExecutionHandler rejectionHandler = new RejectedExecutionHandler();
            ThreadFactory threadFactory = Executors.defaultThreadFactory();
            ThreadPoolExecutor executorPool = new ThreadPoolExecutor(10, C3PODataSource.getDataSource().getMaxPoolSize(), 30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(50), threadFactory, rejectionHandler);
            ThreadMonitor monitor = new ThreadMonitor(executorPool, 1);
            Thread monitorThread = new Thread(monitor);
            monitorThread.start();
            Connection[] conns = new Connection[movies.size()];
            for (int i = 0; i < movies.size(); i++) {
                conns[i] = C3PODataSource.getConn();
                executorPool.execute(new WorkerThread(conns[i], movies.get(i), null, null, null));
            }
            executorPool.shutdown();
            try {
                while (!executorPool.awaitTermination(24L, TimeUnit.HOURS)) {
                    System.out.println("Waiting for the termination of the Threads!...");
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            monitor.shutdown();
            System.out.println("All the Threads have terminated, now let's close all the connections in the C3PO Pool!...");
            System.out.println("There are " + C3PODataSource.getDataSource().getNumBusyConnections() + " connections open, including the main thread. Let's close them all!...");
            for (Connection x : conns) {
                x.close();
            }
            try {
                System.out.println("\n...Waiting 2 seconds in order to let all the connections close!...");
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            System.out.println("\n...Now, there are " + C3PODataSource.getDataSource().getNumBusyConnections() + " connection(s) open!...");
        } catch (SQLException ex) {
            System.err.println("Error on DataBase: " + ex);
        } catch (ClassNotFoundException ex) {
            System.err.println("Class not found!");
        }
    }
}
