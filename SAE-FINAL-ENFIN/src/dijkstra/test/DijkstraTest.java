package dijkstra.test;

import graph.GrapheHHAdj;
import graph.VarGraph;
import graph.ShortestPath.Distances;
import dijkstra.Dijkstra;

import java.util.ArrayDeque;
import java.util.Deque;

public class DijkstraTest {
	public static void main(String[] args) {
		// Création d'un graphe avec l'implémentation GrapheHHAdj
		VarGraph graphe = new GrapheHHAdj();

		// Ajout de sommets
		graphe.ajouterSommet("A");
		graphe.ajouterSommet("B");
		graphe.ajouterSommet("C");
		graphe.ajouterSommet("D");
		graphe.ajouterSommet("E");
		graphe.ajouterSommet("F");

		// Ajout d'arcs
		graphe.ajouterArc("A", "B", 6);
		graphe.ajouterArc("A", "C", 1);
		graphe.ajouterArc("A", "D", 2);
		graphe.ajouterArc("B", "E", 1);
		graphe.ajouterArc("C", "E", 4);
		graphe.ajouterArc("D", "B", 1);
		graphe.ajouterArc("E", "F", 1);

		// Affichage du graphe
		System.out.println("Graphe créé manuellement:");
		System.out.println(graphe);

		// Test avec la méthode peupler
		VarGraph graphe2 = new GrapheHHAdj();
		graphe2.peupler("A-B(6), A-C(1), A-D(2), B-E(1), C-E(4), D-B(1), E-F(1)");

		System.out.println("\nGraphe créé avec peupler:");
		System.out.println(graphe2);

		// Test de l'algorithme de Dijkstra
		Dijkstra<String> dijkstra = new Dijkstra<>();
		Distances<String> resultats = dijkstra.compute(graphe, "A");

		// Affichage des résultats
		System.out.println("\nRésultats de Dijkstra à partir de A:");
		System.out.println("Distances: " + resultats.dist());
		System.out.println("Prédécesseurs: " + resultats.pred());

		// Affichage du chemin de A à F
		String sommetCible = "F";
		System.out.println("\nChemin de A à " + sommetCible + ":");

		Deque<String> pile = new ArrayDeque<>();
		String sommet = sommetCible;

		while (sommet != null) {
			pile.push(sommet);
			sommet = resultats.pred().get(sommet);
		}

		while (!pile.isEmpty()) {
			System.out.print(pile.pop() + " ");
		}
		System.out.println();

		// Test avec arc de valuation négative
		try {
			VarGraph grapheNegatif = new GrapheHHAdj();
			grapheNegatif.peupler("A-B(6), A-C(1), A-D(2), B-E(-3), C-E(4), D-B(1), E-F(1)");
			dijkstra.compute(grapheNegatif, "A");
			System.out.println("ERREUR: Exception non levée pour un arc négatif!");
		} catch (IllegalArgumentException e) {
			System.out.println("\nTest d'arc négatif: Exception correctement levée: " + e.getMessage());
		}
	}
}