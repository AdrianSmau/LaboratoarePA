package com.pa_lab11.Relationship;

import com.pa_lab11.Person.Person;
import com.pa_lab11.Person.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "/api/v2/relationships")
public class RelationshipController {
    private final RelationshipService relationshipService;
    private final PersonService personService;
    int time = 0;

    @Autowired
    public RelationshipController(RelationshipService service, PersonService personService) {
        this.relationshipService = service;
        this.personService = personService;
    }

    @GetMapping
    public List<String> getFriendships() {
        List<String> displayRelationships = new ArrayList<>();
        List<Relationship> relationships = this.relationshipService.getFriendships();
        int index = 0;
        for (Relationship x : relationships) {
            index++;
            displayRelationships.add("[Friendship #" + index + "]: " + x.getPerson1().getName() + " - " + x.getPerson2().getName());
        }
        return displayRelationships;
    }

    @PostMapping
    public void registerRelationship(@RequestParam String name1, @RequestParam String name2) {
        Person p1 = this.personService.getPersons().stream()
                .filter(p -> p.getName().equals(name1)).findFirst().orElse(null);
        Person p2 = this.personService.getPersons().stream()
                .filter(p -> p.getName().equals(name2)).findFirst().orElse(null);
        if (p1 == null) {
            p1 = new Person(name1);
            this.personService.addPerson(p1);
        }
        if (p2 == null) {
            p2 = new Person(name2);
            this.personService.addPerson(p2);
        }
        Relationship relationship = new Relationship();
        relationship.setPerson1(p1);
        relationship.setPerson2(p2);
        this.relationshipService.addRelationship(relationship);
        relationship = new Relationship();
        relationship.setPerson2(p1);
        relationship.setPerson1(p2);
        this.relationshipService.addRelationship(relationship);
    }

    @PostMapping(value = "/relationshipObj", consumes = "application/json")
    public ResponseEntity<String> registerPersonBody(@RequestBody Map<String, String> json) {
        String n1 = json.get("n1");
        String n2 = json.get("n2");
        System.out.println("n1 - " + n1);
        System.out.println("n2 - " + n2);
        List<Person> persons = this.personService.getPersons();
        boolean b1 = false;
        boolean b2 = false;
        Person p1 = new Person();
        Person p2 = new Person();
        for (Person x : persons) {
            if (x.getName().equals(n1)) {
                p1 = x;
                b1 = true;
            }
            if (x.getName().equals(n2)) {
                p2 = x;
                b2 = true;
            }
        }
        if (!b1)
            p1 = new Person(n1);
        if (!b2)
            p2 = new Person(n2);
        this.personService.addPerson(p1);
        this.personService.addPerson(p2);
        Relationship relationship = new Relationship();
        relationship.setPerson1(p1);
        relationship.setPerson2(p2);
        this.relationshipService.addRelationship(relationship);
        relationship = new Relationship();
        relationship.setPerson2(p1);
        relationship.setPerson1(p2);
        this.relationshipService.addRelationship(relationship);
        return new ResponseEntity<>("Relationship registered successfully!...", HttpStatus.CREATED);
    }

    @GetMapping("/count")
    public int countPersons() {
        return this.relationshipService.getFriendships().size();
    }

    @GetMapping("/graph")
    // The algorithm has Complexity O(V+E) - so, in LINEAR time - using DFS, we follow nodes and mark each of their parent.
    // We conclude that a node is AP if either u is root of a DFS Tree and it has at least two children, or if u is not a root, however it hav a child v such that no node in the subtree with v as root has a back edge to a node above u (so, an ancestor)
    // We maintain a parent[] array. We sort of map each String to an integer, based of their position in the KeySet of the graph. u represents the index of the current element, while v is its child, in string format. tempIndex is the index of v.
    // For every node, we count children. If the current vertex u is a root (so, it has the parent set to 'None') and has more than two children, we mark it as AP.
    // For the second case from the two mentioned above, we keep an array disc[] to store the discovery time of the nodes. For every node u, we need to find the earliest visited node (with the least discovery time) reachable in the subtree of u.
    // The following implementation is based on Tarjan's algorithm for finding articulation points
    public List<String> findArticulationPoints() {
        Map<String, List<String>> graph = this.generateGraph();
        boolean[] visited = new boolean[graph.size()];
        int[] disc = new int[graph.size()];
        int[] low = new int[graph.size()];
        String[] parent = new String[graph.size()];
        boolean[] ap = new boolean[graph.size()];

        for (int i = 0; i < graph.size(); i++) {
            parent[i] = "None";
            visited[i] = false;
            ap[i] = false;
        }

        for (int i = 0; i < graph.size(); i++) {
            if (!visited[i])
                findArticulationPointsHelper(i, visited, disc, low, parent, ap, graph);
        }

        List<String> graphKeys = new ArrayList<>(graph.keySet());
        List<String> resultGraph = new ArrayList<>();

        for (int i = 0; i < graph.size(); i++) {
            if (ap[i])
                resultGraph.add("![AP FOUND]! - " + graphKeys.get(i) + " is an articulation point!...");
            else
                resultGraph.add(graphKeys.get(i) + " is NOT an articulation point!...");
        }
        return resultGraph;
    }

    private void findArticulationPointsHelper(int u, boolean[] visited, int[] disc, int[] low, String[] parent, boolean[] ap, Map<String, List<String>> graph) {
        List<String> graphKeys = new ArrayList<>(graph.keySet());
        int children = 0;
        visited[u] = true;
        disc[u] = low[u] = ++time;
        Iterator<String> i = graph.get(graphKeys.get(u)).iterator();
        while (i.hasNext()) {
            String v = i.next();
            int tempIndex = 0;
            for (String x : graphKeys) {
                if (x.equals(v))
                    break;
                tempIndex++;
            }
            if (!visited[tempIndex]) {
                children++;
                parent[tempIndex] = graphKeys.get(u);
                findArticulationPointsHelper(tempIndex, visited, disc, low, parent, ap, graph);
                low[u] = Math.min(low[u], low[tempIndex]);

                if (parent[u].equals("None") && children > 1)
                    ap[u] = true;
                if (!parent[u].equals("None") && low[tempIndex] >= disc[u])
                    ap[u] = true;
            } else {
                if (!v.equals(parent[u]))
                    low[u] = Math.min(low[u], disc[tempIndex]);
            }

        }
    }

    private Map<String, List<String>> generateGraph() {
        Map<String, List<String>> graph = new HashMap<>();
        List<Relationship> relationships = this.relationshipService.getFriendships();
        for (Relationship x : relationships) {
            if (!graph.containsKey(x.getPerson1().getName()))
                graph.put(x.getPerson1().getName(), new ArrayList<>());
            graph.get(x.getPerson1().getName()).add(x.getPerson2().getName());
        }
        return graph;
    }
}

/*
 --- EXAMPLES

 POST http://localhost:8088/api/v2/relationships/relationshipObj
Content-Type: application/json

{
  "n1": "Adrian",
  "n2": "Damian"
}

###
POST http://localhost:8088/api/v2/relationships/?name1=Johnny&name2=Branch

*/
