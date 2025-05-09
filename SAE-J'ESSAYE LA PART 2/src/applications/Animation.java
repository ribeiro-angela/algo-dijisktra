package applications;

import java.awt.Color;
import java.util.List;
import maze.regular.Generators;  // Au lieu de maze.algorithms.Generators
import graph.Graph;
import graph.ShortestPath.Distances;
import maze.Maze;
import maze.panel.MazeView;
import maze.regular.RegularMaze;
import maze.regular.RegularMaze.Shape;
import adaptator.GraphMaze;
import dijkstra.Dijkstra;

public class Animation {
    public static void main(String[] args) {
        final int WIDTH = 15;
        final int HEIGHT = 7;
        Maze<Integer> maze = getMaze(WIDTH, HEIGHT);
        Graph<Integer> graph = new GraphMaze<>(maze);
        MazeView<Integer> view = MazeView.view(maze, "Animated Dijkstra Shortest Path Computation");
        int start = 0;
        int end = WIDTH * HEIGHT - 1;
        view.highlight(start, Color.CYAN);
        view.highlight(end, Color.PINK);
        view.repaint();
        Distances<Integer> dst = new Dijkstra<Integer>().compute(graph, start,
                (pos, dist) -> {
                    view.annotate(pos, Integer.toString(dist));
                    view.repaint();
                    pause(100);
                }
        );
        if (dst.dist().get(end) == null)
            System.out.println("the exit is not reachable from the entrance");
        else {
            int current = end;
            while (current != start) {
                int pred = dst.pred().get(current);
                view.addPath(List.of(current, pred), Color.BLUE);
                current = pred;
            }
            view.highlight(start, Color.CYAN);
            view.highlight(end, Color.PINK);
            view.repaint();
        }
    }

    private static Maze<Integer> getMaze(int width, int height) {
        Maze<Integer> maze = RegularMaze.makeMaze(Shape.TRIANGLE, width, height, 30);
        Generators.primsAlgorithm(maze);
        Generators.makeItImperfect(maze, 5);
        return maze;
    }

    private static void pause(long duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
