package dijkstra.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import graph.GrapheHHAdj;
import dijkstra.Dijkstra;

class TestGrapheNonConnexe {
    @Test
    void testGrapheNonConnexe() {
        GrapheHHAdj graph = new GrapheHHAdj();
        graph.peupler("A-B(1), C-D(1)");
        Dijkstra<String> dijkstra = new Dijkstra<>();

        var result = dijkstra.compute(graph, "A");

        assertEquals(2, result.dist().size());
        assertEquals(0, result.dist().get("A"));
        assertEquals(1, result.dist().get("B"));
        assertFalse(result.dist().containsKey("C"));
        assertFalse(result.dist().containsKey("D"));
    }
}