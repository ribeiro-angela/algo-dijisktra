package dijkstra.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import graph.Graph;
import graph.GrapheHHAdj;
import graph.ShortestPath.Distances;
import graph.VarGraph;
import org.junit.jupiter.api.Test;

import dijkstra.Dijkstra;

/**
 * Classe de test unitaire pour l’algorithme de Dijkstra.
 * Vérifie le bon fonctionnement du calcul de plus courts chemins,
 * la détection des arcs à valuation négative, ainsi que l’utilisation
 * correcte des résultats retournés.
 */
class DijkstraTest {

	/** Graphe de test sans valuation négative. */
	private static final String GRAPH1 = "A-B(6), A-C(1), A-D(2), B-E(1), C-E(4), D-B(1), E-F(1)";

	/** Graphe contenant une valuation négative sur l’arc B-E. */
	private static final String GRAPH_NEG = "A-B(6), A-C(1), A-D(2), B-E(-3), C-E(4), D-B(1), E-F(1)";

	/** Sommet de départ pour les tests. */
	private static final String FROM = "A";

	/** Sommet d’arrivée pour les tests. */
	private static final String TO = "F";

	/** Distance attendue entre A et F dans GRAPH1. */
	private static final int EXPECTED_DIST = 5;

	/** Chemin attendu entre A et F dans l’ordre inverse (F à A). */
	private static final List<String> EXPECTED_PATH = List.of("F", "E", "B", "D", "A");

	/** Instance partagée de l’algorithme de Dijkstra. */
	private static final Dijkstra<String> dijkstra = new Dijkstra<>();

	/**
	 * Test principal : vérifie que l’algorithme calcule la bonne distance
	 * et les bons prédécesseurs dans un graphe simple sans valuation négative.
	 */

	@Test
	void test() {
		VarGraph g = new GrapheHHAdj();
		g.peupler(GRAPH1);
		tester(g);
	}

	/**
	 * Méthode utilitaire pour tester un graphe donné.
	 *
	 * @param g le graphe à tester
	 */
	void tester(Graph<String> g) {
		Distances<String> dst = dijkstra.compute(g, FROM);
		assertEquals(EXPECTED_DIST, dst.dist().get(TO));

		String current = EXPECTED_PATH.get(0);
		for (String expected : EXPECTED_PATH) {
			assertEquals(expected, current);
			current = dst.pred().get(current);
		}

		assertNull(current); // Le prédécesseur de la source doit être null
	}

	/**
	 * Vérifie que l’algorithme lève une exception si un arc a une valuation négative.
	 */
	@Test
	void pasDeValuationNegative() {
		VarGraph g = new GrapheHHAdj();
		g.peupler(GRAPH_NEG);
		assertThrows(IllegalArgumentException.class,
				() -> dijkstra.compute(g, FROM));
	}

	/**
	 * Affiche les résultats calculés par Dijkstra pour vérification manuelle :
	 * - distances depuis A
	 * - prédécesseurs
	 * - chemin de A à F
	 */

	@Test
	void utilisationDuResultat() {
		VarGraph g = new GrapheHHAdj();
		g.peupler(GRAPH1);
		Distances<String> dst = dijkstra.compute(g, FROM);

		System.out.println("Graphe : " + g);
		System.out.println("Distances de A : " + dst.dist());
		System.out.println("Predecesseurs : " + dst.pred());
		System.out.println("Distance de " + FROM + " à " + TO + " : " + dst.dist().get(TO));

		System.out.print("Chemin de " + FROM + " à " + TO + " : ");
		String sommet = TO;
		Deque<String> pile = new ArrayDeque<>();
		while (sommet != null) {
			pile.push(sommet);
			sommet = dst.pred().get(sommet);
		}
		while (!pile.isEmpty()) {
			System.out.print(pile.pop() + " ");
		}
		System.out.println();
	}
}
