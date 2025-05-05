package dijkstra.test;

import graph.GrapheHHAdj;
import graph.VarGraph;

/**
 * Classe de test simple pour démontrer l'utilisation de GrapheHHAdj.
 */
public class GrapheHHAdjTest{
    public static void main(String[] args) {
        System.out.println("Test de la classe GrapheHHAdj");

        // Création d'un graphe
        VarGraph graphe = new GrapheHHAdj();

        // Peuplement du graphe avec la chaîne de test fournie dans DijkstraTest
        String chaineGraphe = "A-B(6), A-C(1), A-D(2), B-E(1), C-E(4), D-B(1), E-F(1)";
        System.out.println("Peuplement du graphe avec : " + chaineGraphe);
        graphe.peupler(chaineGraphe);

        // Affichage du graphe
        System.out.println(graphe);

        // Affichage des successeurs de chaque sommet
        System.out.println("\nSuccesseurs de chaque sommet :");
        for (String sommet : new String[]{"A", "B", "C", "D", "E", "F"}) {
            System.out.println("Successeurs de " + sommet + " : " + graphe.getSucc(sommet));
        }

        // Test d'ajout d'un nouvel arc
        try {
            System.out.println("\nAjout d'un nouvel arc F-A(10)");
            graphe.ajouterArc("F", "A", 10);
            System.out.println("Arc ajouté avec succès");
            System.out.println(graphe);
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur lors de l'ajout de l'arc : " + e.getMessage());
        }

        // Test d'ajout d'un arc existant (doit lever une exception)
        try {
            System.out.println("\nTentative d'ajout d'un arc existant A-B(5)");
            graphe.ajouterArc("A", "B", 5);
            System.out.println("Arc ajouté avec succès (ne devrait pas arriver)");
        } catch (IllegalArgumentException e) {
            System.out.println("Exception levée comme prévu : " + e.getMessage());
        }

        System.out.println("\nTest terminé");
    }
}