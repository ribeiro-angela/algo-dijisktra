package dijkstra;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import graph.Graph;
import graph.Graph.Arc;
import graph.ShortestPath;

/**
 * Implémentation de l'algorithme de Dijkstra pour trouver les plus courts chemins
 * dans un graphe valué.
 *
 * @param <T> Type des sommets du graphe
 */
public class Dijkstra<T> implements ShortestPath<T> {

	/**
	 * Classe interne pour représenter un nœud dans la file de priorité.
	 * Stocke un sommet et sa distance actuelle depuis le sommet source.
	 */
	private static class Node<T> implements Comparable<Node<T>> {
		T vertex;
		int distance;

		public Node(T vertex, int distance) {
			this.vertex = vertex;
			this.distance = distance;
		}

		@Override
		public int compareTo(Node<T> other) {
			return Integer.compare(this.distance, other.distance);
		}
	}

	@Override
	public Distances<T> compute(Graph<T> g, T src, Animator<T> animator) throws IllegalArgumentException {
		// Structures de données pour stocker les résultats
		Map<T, Integer> distances = new HashMap<>();
		Map<T, T> predecessors = new HashMap<>();

		// Ensemble des sommets dont la distance minimale est déjà calculée définitivement
		Set<T> settled = new HashSet<>();

		// File de priorité pour sélectionner le sommet avec la plus petite distance
		PriorityQueue<Node<T>> priorityQueue = new PriorityQueue<>();

		// Initialisation: distance de la source à elle-même est 0
		distances.put(src, 0);
		predecessors.put(src, null);
		priorityQueue.add(new Node<>(src, 0));

		// Tant qu'il reste des sommets à traiter
		while (!priorityQueue.isEmpty()) {
			// Extraire le sommet avec la plus petite distance
			Node<T> current = priorityQueue.poll();
			T u = current.vertex;

			// Si le sommet est déjà traité, passer au suivant
			if (settled.contains(u)) {
				continue;
			}

			// Marquer le sommet comme traité
			settled.add(u);

			// Notifier l'animateur
			animator.accept(u, current.distance);

			// Pour chaque arc sortant du sommet courant
			for (Arc<T> arc : g.getSucc(u)) {
				T v = arc.dst();
				int weight = arc.val();

				// Vérifier qu'il n'y a pas d'arcs de valuation négative
				if (weight < 0) {
					throw new IllegalArgumentException("L'algorithme de Dijkstra ne supporte pas les arcs de valuation négative");
				}

				// Si le sommet destination n'est pas encore traité définitivement
				if (!settled.contains(v)) {
					// Calculer la nouvelle distance
					int newDistance = distances.get(u) + weight;

					// Si c'est la première fois qu'on voit ce sommet ou si la nouvelle distance est plus petite
					if (!distances.containsKey(v) || newDistance < distances.get(v)) {
						// Mettre à jour la distance et le prédécesseur
						distances.put(v, newDistance);
						predecessors.put(v, u);

						// Ajouter à la file de priorité avec la nouvelle distance
						priorityQueue.add(new Node<>(v, newDistance));
					}
				}
			}
		}

		return new Distances<>(distances, predecessors);
	}
}