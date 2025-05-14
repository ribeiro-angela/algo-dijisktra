package dijkstra.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import graph.GrapheHHAdj;
import dijkstra.Dijkstra;

/**
 * Classe de test unitaire pour vérifier le comportement de l'algorithme
 * de Dijkstra sur un graphe non connexe.
 */
class TestGrapheNonConnexe {

    /**
     * Teste un graphe non connexe :
     * - Composante 1 : A → B (1)
     * - Composante 2 : C → D (1)
     *
     * À partir de A, seuls A et B doivent être accessibles.
     * C et D ne doivent pas apparaître dans les résultats.
     */
    @Test
    void testGrapheNonConnexe() {
        // Création du graphe
        GrapheHHAdj graph = new GrapheHHAdj();
        graph.peupler("A-B(1), C-D(1)");

        // Exécution de l'algorithme de Dijkstra depuis A
        Dijkstra<String> dijkstra = new Dijkstra<>();
        var result = dijkstra.compute(graph, "A");

        // Vérifications : seuls A et B accessibles
        assertEquals(2, result.dist().size(), "Seuls A et B doivent être accessibles depuis A");
        assertEquals(0, result.dist().get("A"), "Distance de A à A doit être 0");
        assertEquals(1, result.dist().get("B"), "Distance de A à B doit être 1");

        // Vérification de l'inaccessibilité de C et D
        assertFalse(result.dist().containsKey("C"), "C ne doit pas être accessible depuis A");
        assertFalse(result.dist().containsKey("D"), "D ne doit pas être accessible depuis A");
    }
}
