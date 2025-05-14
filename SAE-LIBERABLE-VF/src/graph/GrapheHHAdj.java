package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implémentation d’un graphe orienté valué à l’aide d’une structure
 * de données basée sur une HashMap de HashMaps.
 *
 * Chaque sommet est une chaîne de caractères, et les arcs sont représentés
 * par des couples (successeur, valuation).
 * La structure interne est :
 *
 * Map<String, Map<String, Integer>>
 *
 * où la première clé représente un sommet source, et la valeur associée
 * est une HashMap contenant les successeurs et leurs valuations.

 * Ce type de représentation est utile pour un accès rapide aux successeurs
 * et à la valuation des arcs.
 */
public class GrapheHHAdj implements VarGraph {

	/**
	 * Structure de données principale :
	 * Map de sommets vers une Map de successeurs et leurs valuations.
	 */
	private Map<String, Map<String, Integer>> adjacence;

	/**
	 * Constructeur par défaut qui initialise une structure vide.
	 */
	public GrapheHHAdj() {
		adjacence = new HashMap<>();
	}

	/**
	 * Retourne la liste des arcs sortants du sommet spécifié.
	 *
	 * @param s le sommet source
	 * @return une liste d’arcs contenant tous les successeurs et leurs valuations
	 */
	@Override
	public List<Arc<String>> getSucc(String s) {
		List<Arc<String>> successeurs = new ArrayList<>();

		if (adjacence.containsKey(s)) {
			for (Map.Entry<String, Integer> entry : adjacence.get(s).entrySet()) {
				String destination = entry.getKey();
				Integer valuation = entry.getValue();
				successeurs.add(new Arc<>(valuation, destination));
			}
		}

		return successeurs;
	}

	/**
	 * Ajoute un sommet au graphe s’il n’est pas déjà présent.
	 *
	 * @param noeud le nom du sommet à ajouter
	 */
	@Override
	public void ajouterSommet(String noeud) {
		if (!adjacence.containsKey(noeud)) {
			adjacence.put(noeud, new HashMap<>());
		}
	}

	/**
	 * Ajoute un arc entre deux sommets avec une valuation spécifiée.
	 *
	 * @param source le sommet source
	 * @param destination le sommet destination
	 * @param valeur la valuation (poids) de l’arc
	 * @throws IllegalArgumentException si un arc existe déjà entre source et destination
	 */
	@Override
	public void ajouterArc(String source, String destination, Integer valeur) {
		ajouterSommet(source);
		ajouterSommet(destination);

		if (adjacence.get(source).containsKey(destination)) {
			throw new IllegalArgumentException("Arc déjà présent : " + source + " -> " + destination);
		}

		adjacence.get(source).put(destination, valeur);
	}

	/**
	 * Le format est : source-destination(valeur), ...
	 *
	 * @return une chaîne représentant les arcs du graphe
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
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

				sb.append(source)
						.append("-")
						.append(destination)
						.append("(")
						.append(valuation)
						.append(")");
			}
		}

		sb.append(" }");
		return sb.toString();
	}
}
