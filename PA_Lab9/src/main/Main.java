package main;

import DatabaseConnection.DatabaseConnection;
import Entities.Actor;
import Entities.Director;
import Entities.Genre;
import Entities.Movie;
import EntityManagerFactory.EntityManagerSingleton;
import Repository.Create;
import Repository.FindByID;
import Repository.FindByName;

import javax.persistence.EntityManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
        System.out.println("\n ----- COMPULSORY ----- \n");

        EntityManagerSingleton singleton = EntityManagerSingleton.getInstance();

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

        Create persistManager = new Create();
        FindByID idFinder = new FindByID();
        FindByName nameFinder = new FindByName();

        //Persisting
        EntityManager entityManager = singleton.getEntityManagerFactory().createEntityManager();
        persistManager.setEntityManager(entityManager);
        idFinder.setEntityManager(entityManager);
        nameFinder.setEntityManager(entityManager);

        entityManager.getTransaction().begin();

        persistManager.persist(m);
        persistManager.persist(a1);
        persistManager.persist(a2);
        persistManager.persist(g1);
        persistManager.persist(g2);
        persistManager.persist(d);

        entityManager.getTransaction().commit();

        System.out.println("\nFinding the Entity with the ID = 1!...");
        Object findResult = idFinder.findByID(1);
        System.out.println(findResult);

        System.out.println("\nFinding the Entity with the ID = 4!...");
        findResult = idFinder.findByID(4);
        System.out.println(findResult);

        System.out.println("\nFinding the Entities with the name = 'Andrew Smith'!...");
        List<Object> nameResults = nameFinder.findByName("Andrew Smith");
        if (nameResults.size() != 0)
            nameResults.remove(nameResults.get(nameResults.size() - 1));
        System.out.println("\nFound " + nameResults.size() + " result(s)!...");
        // The result list always contains null as its last element...
        for (Object x : nameResults) {
            System.out.println(x);
        }

        entityManager.close();
        singleton.closeEntityManagerFactory();
    }
}
