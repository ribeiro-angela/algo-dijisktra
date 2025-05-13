package adaptator;

import java.util.ArrayList;
import java.util.List;

import graph.Graph;
import graph.Graph.Arc;
import maze.Maze;

/**
 * Adaptateur entre Maze et Graph.
 * Transforme un labyrinthe en graphe non orienté non valué.
 */
public class GraphMaze<T extends Integer> implements Graph<T> {
    private final Maze maze;

    public GraphMaze(Maze maze) {
        this.maze = maze;
    }

    @Override
    public List<Arc<T>> getSucc(T s) {
        List<Arc<T>> arcs = new ArrayList<>();
        for (Integer neighbor : maze.openedNeighbours(s)) {
            arcs.add(new Arc<>(1, (T) Integer.valueOf(neighbor)));  // Non valué → valuation = 1
        }
        return arcs;
    }
}
