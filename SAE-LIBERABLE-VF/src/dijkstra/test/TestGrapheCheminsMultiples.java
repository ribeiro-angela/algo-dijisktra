package dijkstra.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import graph.GrapheHHAdj;
import dijkstra.Dijkstra;

class TestGrapheCheminsMultiples {
    @Test
    void testCheminsMultiples() {
        GrapheHHAdj graph = new GrapheHHAdj();
        graph.peupler("A-B(3), A-C(1), B-D(2), C-D(4)");
        Dijkstra<String> dijkstra = new Dijkstra<>();

        var result = dijkstra.compute(graph, "A");

        assertEquals(4, result.dist().size());
        assertEquals(0, result.dist().get("A"));
        assertEquals(3, result.dist().get("B"));
        assertEquals(1, result.dist().get("C"));
        assertEquals(5, result.dist().get("D")); // A->B->D
    }
}