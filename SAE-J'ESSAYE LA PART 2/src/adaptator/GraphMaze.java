package adaptator;

import graph.Graph;
import maze.Maze;
import java.util.List;
import java.util.stream.Collectors;

public class GraphMaze<C> implements Graph<C> {
    private final Maze<C> maze;

    public GraphMaze(Maze<C> maze) {
        this.maze = maze;
    }

    @Override
    public List<Graph.Arc<C>> getSucc(C cell) {
        return maze.openedNeighbours(cell).stream()
                .map(neighbor -> new Graph.Arc<>(1, neighbor)) // Valuation = 1
                .collect(Collectors.toList());
    }
}