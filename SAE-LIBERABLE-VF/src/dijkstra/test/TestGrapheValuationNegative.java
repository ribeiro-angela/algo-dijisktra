package dijkstra.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import graph.GrapheHHAdj;
import dijkstra.Dijkstra;

/**
 * Classe de test pour vérifier le comportement de l'algorithme de Dijkstra
 * lorsque les valeurs des arêtes du graphe sont négatives.
 *
 * Ce test utilise la bibliothèque JUnit 5 pour effectuer les assertions.
 * Le test s'assure qu'une exception de type IllegalArgumentException} est lancée
 * lorsque le graphe contient une arête avec une valeur négative, ce qui est
 * incompatible avec l'algorithme de Dijkstra.
 *
 */
class TestGrapheValuationNegative {

    /**
     * Test pour vérifier qu'une exception de type IllegalArgumentException}
     * est lancée lorsque le graphe contient une arête avec une valeur négative.
     *
     * Ce test crée un graphe avec une arête ayant une évaluation négative et tente
     * de calculer le plus court chemin à partir du sommet "A". L'algorithme de Dijkstra
     * devrait lancer une exception IllegalArgumentException} en raison de la présence
     * de la valeur négative dans l'arête.
     */
    @Test
    void testValuationNegative() {
        GrapheHHAdj graph = new GrapheHHAdj();
        graph.peupler("A-B(-1)"); // Peupler le graphe avec une arête de poids -1
        Dijkstra<String> dijkstra = new Dijkstra<>();

        // Vérification que l'exception IllegalArgumentException est lancée
        assertThrows(IllegalArgumentException.class,
                () -> dijkstra.compute(graph, "A"));
    }
}
