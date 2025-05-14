package graph;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Représente un graphe orienté avec des sommets de type String et des arcs associés à
 * une valeur entière.
 *
 * Cette interface hérite de Graph et fournit des méthodes pour ajouter des sommets et des arcs,
 * ainsi que pour peupler un graphe à partir d'une chaîne de caractères représentant des arcs.
 *
 * Les comportements des méthodes sont les suivants :
 *
 * ajouterSommet(String)} : Ne fait rien si le sommet est déjà présent.
 *
 * ajouterArc(String, String, Integer)} : Lance une IllegalArgumentException si l'arc est
 * déjà présent.
 *
 * ajouterArc(String, String, Integer)} : Ajoute les sommets s'ils ne sont pas déjà présents.
 *
 * @param *String Le type des sommets du graphe.
 */
public interface VarGraph extends Graph<String> {

	/**
	 * Ajoute un sommet au graphe.
	 *
	 * Cette méthode ne fait rien si le sommet est déjà présent dans le graphe.
	 *
	 * @param noeud Le sommet à ajouter.
	 */
	void ajouterSommet(String noeud);

	/**
	 * Ajoute un arc entre deux sommets du graphe, avec une valeur associée.
	 *
	 * Si l'arc est déjà présent, une IllegalArgumentException} est lancée.
	 *
	 * @param source Le sommet de départ de l'arc.
	 * @param destination Le sommet d'arrivée de l'arc.
	 * @param valeur La valeur associée à l'arc.
	 * @throws IllegalArgumentException Si l'arc est déjà présent dans le graphe.
	 */
	void ajouterArc(String source, String destination, Integer valeur);

	/**
	 * Construit un graphe vide à partir d'une chaîne de caractères représentant des arcs
	 * au format suivant :
	 *
	 *   "A-B(5), A-C(10), B-C(3), C-D(8), E:"
	 *
	 * Chaque arc doit être sous la forme "source-destination(valeur)".
	 * Les sommets sont automatiquement ajoutés si nécessaire, et les arcs sont ajoutés
	 * avec les valeurs spécifiées.
	 *
	 * @param str La chaîne représentant les arcs à ajouter au graphe.
	 * @throws IllegalArgumentException Si le format de la chaîne est invalide ou si un
	 * arc est mal formé.
	 */
	default void peupler(String str) {
		String[] arcs = str.split(",\\s*");
		for (String arc : arcs) {
			arc = arc.trim();
			// Expression régulière pour extraire les parties source, destination et valuation
			Matcher matcher = Pattern.compile("([^-]+)-([^(]+)\\((-?\\d+)\\)")
					.matcher(arc);
			if (matcher.matches()) {
				String src = matcher.group(1).trim();
				String dest = matcher.group(2).trim();
				int val = Integer.parseInt(matcher.group(3).trim());
				ajouterSommet(src);
				ajouterSommet(dest);
				ajouterArc(src, dest, val);
			} else {
				throw new IllegalArgumentException("Format d'arc invalide : " + arc);
			}
		}
	}
}
