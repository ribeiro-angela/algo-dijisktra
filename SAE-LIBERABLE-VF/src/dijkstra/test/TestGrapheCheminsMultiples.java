package dijkstra.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import graph.GrapheHHAdj;
import dijkstra.Dijkstra;

/**
 * Classe de test pour vérifier que l'algorithme de Dijkstra
 * choisit le chemin le plus court lorsqu'il existe plusieurs chemins
 * menant à un même sommet.
 */

class TestGrapheCheminsMultiples {

    /**
     * Teste un graphe où plusieurs chemins mènent au même sommet.
     * Vérifie que Dijkstra choisit systématiquement le chemin ayant
     * la plus petite somme de valuations.
     *
     * Graphe testé :
     * A → B (3), A → C (1), B → D (2), C → D (4)
     *
     * Chemins possibles vers D :
     * - A → B → D : 3 + 2 = 5
     * - A → C → D : 1 + 4 = 5
     *
     * Les deux chemins ont une distance égale, Dijkstra peut choisir l’un ou l’autre.
     */

    @Test
    void testCheminsMultiples() {
        // Création du graphe avec des chemins multiples vers D
        GrapheHHAdj graph = new GrapheHHAdj();
        graph.peupler("A-B(3), A-C(1), B-D(2), C-D(4)");

        // Exécution de Dijkstra depuis A
        Dijkstra<String> dijkstra = new Dijkstra<>();
        var result = dijkstra.compute(graph, "A");

        // Vérification des distances calculées
        assertEquals(4, result.dist().size(), "Le graphe devrait contenir 4 sommets");
        assertEquals(0, result.dist().get("A"), "Distance de A à A doit être 0");
        assertEquals(3, result.dist().get("B"), "Distance de A à B doit être 3");
        assertEquals(1, result.dist().get("C"), "Distance de A à C doit être 1");
        assertEquals(5, result.dist().get("D"), "Distance de A à D doit être 5 par A->B->D ou A->C->D");
    }
}
