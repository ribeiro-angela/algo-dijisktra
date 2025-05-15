package dijkstra.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import graph.GrapheHHAdj;
import dijkstra.Dijkstra;

class TestGrapheVide {
    @Test
    void testGrapheVide() {
        // Test avec un graphe vide
        GrapheHHAdj graph = new GrapheHHAdj();
        Dijkstra<String> dijkstra = new Dijkstra<>();

        var result = dijkstra.compute(graph, "A");

        // Vérifier que seul le sommet de départ est présent
        assertEquals(1, result.dist().size());
        assertEquals(0, result.dist().get("A"));
        assertEquals(null, result.pred().get("A"));
    }

    @Test
    void testSommetIsolé() {
        // Test avec un sommet isolé
        GrapheHHAdj graph = new GrapheHHAdj();
        graph.ajouterSommet("A");
        Dijkstra<String> dijkstra = new Dijkstra<>();

        var result = dijkstra.compute(graph, "A");

        // Vérifier que seul le sommet de départ est présent
        assertEquals(1, result.dist().size());
        assertEquals(0, result.dist().get("A"));
        assertEquals(null, result.pred().get("A"));
    }
}