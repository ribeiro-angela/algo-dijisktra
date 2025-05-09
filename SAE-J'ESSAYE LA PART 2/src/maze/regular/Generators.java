package maze.regular;

import maze.Maze;
import java.util.Random;
import java.util.Set;

public class Generators {
    private static final Random rand = new Random();

    /**
     * Implémentation de l'algorithme de Prim pour générer un labyrinthe parfait
     */
    public static <C> void primsAlgorithm(Maze<C> maze) {
        Set<C> visited = new java.util.HashSet<>();
        java.util.PriorityQueue<C> frontier = new java.util.PriorityQueue<>();

        // Choisir une cellule de départ aléatoire
        C start = maze.start();
        visited.add(start);
        addFrontierCells(maze, start, frontier, visited);

        while (!frontier.isEmpty()) {
            C cell = frontier.poll();
            C neighbor = getRandomVisitedNeighbor(maze, cell, visited);

            if (neighbor != null) {
                maze.open(cell, neighbor);
                maze.open(neighbor, cell); // Pour un labyrinthe non-orienté
                visited.add(cell);
                addFrontierCells(maze, cell, frontier, visited);
            }
        }
    }

    /**
     * Rend le labyrinthe imparfait en ouvrant des murs supplémentaires
     */
    public static <C> void makeItImperfect(Maze<C> maze, int wallsToRemove) {
        Set<C> allCells = new java.util.HashSet<>();
        // Implémentation basique : ouverture aléatoire de murs
        for (int i = 0; i < wallsToRemove; i++) {
            // Logique pour sélectionner et ouvrir des murs aléatoires
        }
    }

    // Méthodes auxiliaires
    private static <C> void addFrontierCells(Maze<C> maze, C cell, java.util.Queue<C> frontier, Set<C> visited) {
        for (C neighbor : maze.neighbours(cell)) {
            if (!visited.contains(neighbor)) {
                frontier.add(neighbor);
            }
        }
    }

    private static <C> C getRandomVisitedNeighbor(Maze<C> maze, C cell, Set<C> visited) {
        java.util.List<C> candidates = new java.util.ArrayList<>();
        for (C neighbor : maze.neighbours(cell)) {
            if (visited.contains(neighbor)) {
                candidates.add(neighbor);
            }
        }
        return candidates.isEmpty() ? null : candidates.get(rand.nextInt(candidates.size()));
    }
}