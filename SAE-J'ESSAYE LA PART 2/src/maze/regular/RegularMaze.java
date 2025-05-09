package maze.regular;

import maze.Maze;
import java.awt.*;
import java.util.*;
import java.io.*;

public class RegularMaze<C> implements Maze<C>, Serializable {
    private final Shape shape;
    private final int width, height;
    private final Set<C> cells;
    private final Map<C, Set<C>> openPaths = new HashMap<>();
    private C start;

    public enum Shape { SQUARE, TRIANGLE, HEXAGON }

    public RegularMaze(Shape shape, int width, int height) {
        this.shape = shape;
        this.width = width;
        this.height = height;
        this.cells = generateCells();
        this.start = cells.iterator().next(); // Premier élément comme départ
    }

    private Set<C> generateCells() {
        Set<C> generated = new LinkedHashSet<>();
        // Génération des cellules selon la forme
        if (shape == Shape.SQUARE) {
            for (int i = 0; i < width * height; i++) {
                generated.add((C) Integer.valueOf(i));
            }
        }
        return generated;
    }
    @Override
    public int getWidth() {
        return width;
    }
    @Override
    public C start() {
        return start;
    }

    @Override
    public Set<C> neighbours(C from) {
        return openPaths.getOrDefault(from, Collections.emptySet());
    }

    @Override
    public boolean isOpen(C from, C to) {
        return openPaths.getOrDefault(from, Collections.emptySet()).contains(to);
    }

    @Override
    public void open(C from, C to) {
        openPaths.computeIfAbsent(from, k -> new HashSet<>()).add(to);
        openPaths.computeIfAbsent(to, k -> new HashSet<>()).add(from); // Pour labyrinthe non-orienté
    }

    @Override
    public void close(C from, C to) {
        if (openPaths.containsKey(from)) {
            openPaths.get(from).remove(to);
        }
        if (openPaths.containsKey(to)) {
            openPaths.get(to).remove(from);
        }
    }

    // Méthode factory
    public static Maze<Integer> makeMaze(Shape shape, int width, int height, int seed) {
        RegularMaze<Integer> maze = new RegularMaze<>(shape, width, height);
        // Initialisation des chemins ouverts
        return maze;
    }

    // Méthode de désérialisation
    public static RegularMaze<Integer> readMaze(String filename)
            throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (RegularMaze<Integer>) in.readObject();
        }
    }

    // Méthodes pour le dessin (implémentations basiques)
    @Override
    public void draw(Graphics2D g) {
        // Implémentation du dessin du labyrinthe
    }

    @Override
    public Rectangle rectangle() {
        return new Rectangle(0, 0, width * 30, height * 30);
    }
}