package dijkstra.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import graph.GrapheHHAdj;
import dijkstra.Dijkstra;

/**
 * Classe de test unitaire vérifiant que l’algorithme de Dijkstra
 * gère correctement les graphes contenant des cycles.
 */

class TestGrapheAvecBoucle {

    /**
     * Teste un graphe contenant un cycle : A → B → C → A.
     * Vérifie que les distances minimales sont correctement calculées
     * malgré la présence de cette boucle.
     */

    @Test
    void testGrapheAvecBoucle() {
        // Création du graphe avec une boucle A -> B -> C -> A
        GrapheHHAdj graph = new GrapheHHAdj();
        graph.peupler("A-B(1), B-C(2), C-A(3)");

        // Exécution de Dijkstra à partir du sommet A
        Dijkstra<String> dijkstra = new Dijkstra<>();
        var result = dijkstra.compute(graph, "A");

        // Vérification des distances minimales
        assertEquals(3, result.dist().size(), "Le graphe devrait contenir 3 sommets");
        assertEquals(0, result.dist().get("A"), "Distance de A à A doit être 0");
        assertEquals(1, result.dist().get("B"), "Distance de A à B doit être 1");
        assertEquals(3, result.dist().get("C"), "Distance de A à C doit être 3");
    }
}
