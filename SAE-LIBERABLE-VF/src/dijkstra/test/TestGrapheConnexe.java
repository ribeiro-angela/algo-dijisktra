package dijkstra.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import graph.GrapheHHAdj;
import dijkstra.Dijkstra;

/**
 * Classe de test unitaire pour vérifier le bon fonctionnement
 * de l'algorithme de Dijkstra sur un graphe connexe simple.
 */

class TestGrapheConnexe {

    /**
     * Teste un graphe connexe avec une chaîne linéaire de sommets :
     * A → B (1), B → C (2), C → D (3)
     *
     * Vérifie que les distances calculées depuis A sont correctes :
     * - A → A : 0
     * - A → B : 1
     * - A → C : 1 + 2 = 3
     * - A → D : 1 + 2 + 3 = 6
     */

    @Test
    void testGrapheConnexeSimple() {
        // Création du graphe
        GrapheHHAdj graph = new GrapheHHAdj();
        graph.peupler("A-B(1), B-C(2), C-D(3)");

        // Application de Dijkstra
        Dijkstra<String> dijkstra = new Dijkstra<>();
        var result = dijkstra.compute(graph, "A");

        // Vérification des distances attendues
        assertEquals(4, result.dist().size(), "Le graphe contient 4 sommets accessibles depuis A");
        assertEquals(0, result.dist().get("A"), "Distance de A à A doit être 0");
        assertEquals(1, result.dist().get("B"), "Distance de A à B doit être 1");
        assertEquals(3, result.dist().get("C"), "Distance de A à C doit être 3");
        assertEquals(6, result.dist().get("D"), "Distance de A à D doit être 6");
    }
}
