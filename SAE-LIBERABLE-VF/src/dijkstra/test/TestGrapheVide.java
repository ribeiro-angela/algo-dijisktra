/**
 * Classe de test pour vérifier le comportement de l'algorithme de Dijkstra sur un graphe vide ou avec un sommet isolé.
 */
package dijkstra.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import graph.GrapheHHAdj;
import dijkstra.Dijkstra;

class TestGrapheVide {

    /**
     * Teste le comportement de l'algorithme de Dijkstra sur un graphe vide.
     * Vérifie que seul le sommet de départ est présent dans les résultats,
     * avec une distance de 0 et aucun prédécesseur.
     */
    @Test
    void testGrapheVide() {
        GrapheHHAdj graph = new GrapheHHAdj();
        test(graph);
    }

    private void test(GrapheHHAdj graph) {
        Dijkstra<String> dijkstra = new Dijkstra<>();

        var result = dijkstra.compute(graph, "A");

        assertEquals(1, result.dist().size());
        assertEquals(0, result.dist().get("A"));
        assertNull(result.pred().get("A"));
    }

    /**
     * Teste le comportement de l'algorithme de Dijkstra sur un graphe contenant un sommet isolé.
     * Vérifie que seul le sommet de départ est présent dans les résultats,
     * avec une distance de 0 et aucun prédécesseur.
     */
    @Test
    void testSommetIsole() {
        GrapheHHAdj graph = new GrapheHHAdj();
        graph.ajouterSommet("A");
        test(graph);
    }
}