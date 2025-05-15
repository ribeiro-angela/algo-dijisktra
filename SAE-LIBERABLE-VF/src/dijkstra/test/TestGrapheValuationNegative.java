package dijkstra.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import graph.GrapheHHAdj;
import dijkstra.Dijkstra;


class TestGrapheValuationNegative {
    @Test
    void testValuationNegative() {
        GrapheHHAdj graph = new GrapheHHAdj();
        graph.peupler("A-B(-1)");
        Dijkstra<String> dijkstra = new Dijkstra<>();

        assertThrows(IllegalArgumentException.class,
                () -> dijkstra.compute(graph, "A"));
    }
}