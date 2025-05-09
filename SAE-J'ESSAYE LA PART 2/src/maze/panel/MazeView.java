package maze.panel;

import maze.Maze;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MazeView<C> extends JPanel {
    private final Maze<C> maze;
    private final int cellSize = 30; // Taille d'une cellule en pixels

    public static <C> MazeView<C> view(Maze<C> maze, String title) {
        MazeView<C> view = new MazeView<>(maze);
        JFrame frame = new JFrame(title);
        frame.setContentPane(view);
        frame.pack();
        frame.setVisible(true);
        return view;
    }

    public MazeView(Maze<C> maze) {
        this.maze = maze;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        maze.draw((Graphics2D) g);
    }

    public void highlight(C cell, Color color) {
        // Implémentation pour surligner une cellule
    }

    public void addPath(List<C> path, Color color) {
        // Implémentation pour dessiner un chemin
    }
    // Dans MazeView.java
    public void annotate(Integer cell, String text) {
        Graphics2D g = (Graphics2D) getGraphics();
        if (g != null) {
            g.setColor(Color.BLACK);
            g.drawString(text, getCellX(cell), getCellY(cell));
        }
    }
    protected int getCellX(Integer cell) {
        return (cell % maze.getWidth()) * cellSize + cellSize/2;
    }

    /**
     * Calcule la position Y d'une cellule dans la grille
     * @param cell La cellule (doit être un Integer pour cette implémentation)
     * @return Position Y en pixels
     */
    protected int getCellY(Integer cell) {
        return (cell / maze.getWidth()) * cellSize + cellSize/2;
    }

}