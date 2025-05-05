package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implémentation d'un graphe orienté valué avec une liste d'adjacence.
 * HH signifie HashMap de HashMap, représentant un graphe comme une HashMap
 * où les clés sont les sommets et les valeurs sont des HashMaps associant
 * les sommets destinations aux valuations des arcs.
 */
public class GrapheHHAdj implements VarGraph {

	// Structure de données : HashMap de HashMap
	// La première HashMap associe chaque sommet à une seconde HashMap
	// La seconde HashMap associe chaque successeur à la valuation de l'arc
	private Map<String, Map<String, Integer>> adjacence;

	/**
	 * Constructeur par défaut.
	 */
	public GrapheHHAdj() {
		adjacence = new HashMap<>();
	}

	@Override
	public List<Arc<String>> getSucc(String s) {
		List<Arc<String>> successeurs = new ArrayList<>();

		// Si le sommet existe dans le graphe
		if (adjacence.containsKey(s)) {
			// Pour chaque paire (destination, valuation) dans la HashMap des successeurs
			for (Map.Entry<String, Integer> entry : adjacence.get(s).entrySet()) {
				String destination = entry.getKey();
				Integer valuation = entry.getValue();
				// Créer un nouvel arc et l'ajouter à la liste des successeurs
				successeurs.add(new Arc<>(valuation, destination));
			}
		}

		return successeurs;
	}

	@Override
	public void ajouterSommet(String noeud) {
		// Ne fait rien si le sommet est déjà présent
		if (!adjacence.containsKey(noeud)) {
			adjacence.put(noeud, new HashMap<>());
		}
	}

	@Override
	public void ajouterArc(String source, String destination, Integer valeur) {
		// Ajouter les sommets s'ils ne sont pas déjà présents
		ajouterSommet(source);
		ajouterSommet(destination);

		// Vérifier si l'arc existe déjà
		if (adjacence.get(source).containsKey(destination)) {
			throw new IllegalArgumentException("Arc déjà présent : " + source + " -> " + destination);
		}

		// Ajouter l'arc
		adjacence.get(source).put(destination, valeur);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Graphe : { ");

		boolean first = true;
		for (Map.Entry<String, Map<String, Integer>> entry : adjacence.entrySet()) {
			String source = entry.getKey();

			for (Map.Entry<String, Integer> arcEntry : entry.getValue().entrySet()) {
				String destination = arcEntry.getKey();
				Integer valuation = arcEntry.getValue();

				if (!first) {
					sb.append(", ");
				}
				first = false;

				sb.append(source).append("-").append(destination).append("(").append(valuation).append(")");
			}
		}

		sb.append(" }");
		return sb.toString();
	}
}