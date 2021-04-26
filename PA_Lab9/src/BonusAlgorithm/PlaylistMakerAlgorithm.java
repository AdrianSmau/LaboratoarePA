package BonusAlgorithm;

import Entities.Director;
import Entities.Movie;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlaylistMakerAlgorithm {
    private final Map<String, List<Movie>> graphMapping = new HashMap<>();
    private final EntityManager entityManager;

    public PlaylistMakerAlgorithm(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Map<String, List<Movie>> generatePlaylist() {
        Map<String, List<Movie>> playlist = new HashMap<>();
        Map<String, Boolean> relatedPlayed = new HashMap<>();
        this.generateMap();
        for (String x : this.graphMapping.keySet()) {
            relatedPlayed.put(x, false);
        }
        for (String x : this.graphMapping.keySet()) {
            if (graphMapping.get(x).size() >= 2 && !relatedPlayed.get(x)) {
                List<Movie> playedMovies = new ArrayList<>();
                playedMovies.add(this.graphMapping.get(x).get(0));
                playedMovies.add(this.graphMapping.get(x).get(1));
                playlist.put(x, playedMovies);
                relatedPlayed.put(x, true);
            }
        }
        return playlist;
    }

    private void generateMap() {
        TypedQuery<Director> query = entityManager.createQuery("SELECT x FROM Director x", Director.class);
        List<Director> results = query.getResultList();
        for (Director x : results) {
            String tempName = x.getName();
            String[] tempNameComps = tempName.split(" ");
            if (tempNameComps.length > 0 && tempNameComps[0].equals("Bonus"))
                tempName = "Bonus Sample Director " + tempNameComps[3].split("\\(")[0];
            if (!this.graphMapping.containsKey(tempName))
                this.graphMapping.put(tempName, new ArrayList<>());
            this.graphMapping.get(tempName).add(x.getMovie());
        }
    }
}
