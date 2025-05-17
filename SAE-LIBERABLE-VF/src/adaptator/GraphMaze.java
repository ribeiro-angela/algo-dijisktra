package adaptator;

import java.util.ArrayList;
import java.util.List;

import graph.Graph;
import maze.Maze;

/**
 * Adaptateur entre Maze et Graph.
 * Transforme un labyrinthe en graphe non orienté non valué.
 */
public class GraphMaze<T> implements Graph<T> {
    private final Maze<T> maze;

    public GraphMaze(Maze<T> maze) {
        this.maze = maze;
    }

    @Override
    public List<Graph.Arc<T>> getSucc(T s) {
        List<Graph.Arc<T>> arcs = new ArrayList<>();
        for (T neighbor : maze.openedNeighbours(s)) {
            arcs.add(new Graph.Arc<>(1, neighbor));
        }
        return arcs;
    }
}