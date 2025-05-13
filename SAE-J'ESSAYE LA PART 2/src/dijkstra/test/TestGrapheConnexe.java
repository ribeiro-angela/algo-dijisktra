package dijkstra.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import graph.GrapheHHAdj;
import dijkstra.Dijkstra;

class TestGrapheConnexe {
    @Test
    void testGrapheConnexeSimple() {
        GrapheHHAdj graph = new GrapheHHAdj();
        graph.peupler("A-B(1), B-C(2), C-D(3)");
        Dijkstra<String> dijkstra = new Dijkstra<>();

        var result = dijkstra.compute(graph, "A");

        assertEquals(4, result.dist().size());
        assertEquals(0, result.dist().get("A"));
        assertEquals(1, result.dist().get("B"));
        assertEquals(3, result.dist().get("C"));
        assertEquals(6, result.dist().get("D"));
    }
}