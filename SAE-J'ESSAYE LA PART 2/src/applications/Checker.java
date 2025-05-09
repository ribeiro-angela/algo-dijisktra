package applications;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import graph.Graph;
import graph.ShortestPath.Distances;
import maze.regular.RegularMaze;
import adaptator.GraphMaze;
import dijkstra.Dijkstra;

public class Checker {
    public static void main(String[] args) {
        if (args.length > 0)
            throw new IllegalArgumentException("Aucun argument attendu");

        File benchDir = new File("bench");
        if (!benchDir.exists() || !benchDir.isDirectory()) {
            System.out.println("Le répertoire 'bench' est introuvable ou n'est pas un répertoire.");
            return;
        }

        File[] mazeFiles = benchDir.listFiles((dir, name) -> name.endsWith(".maze"));
        if (mazeFiles == null || mazeFiles.length == 0) {
            System.out.println("Aucun fichier .maze trouvé dans le répertoire 'bench'.");
            return;
        }

        Arrays.stream(mazeFiles).forEach(mazeFile -> {
            String baseName = mazeFile.getName().replace(".maze", "");
            File distFile = new File(benchDir, baseName + ".dist");
            if (distFile.exists()) {
                check(mazeFile.getPath(), distFile.getPath());
            } else {
                System.out.println("Fichier .dist correspondant introuvable pour : " + mazeFile.getName());
            }
        });
    }

    public static void check(String mazeFile, String distFile) {
        RegularMaze<Integer> maze = null;
        try {
            maze = RegularMaze.readMaze(mazeFile);
        } catch (ClassNotFoundException | IOException e) {
            System.out.println("Fichier '" + mazeFile + "' manquant ou dans un format incorrect");
            return;
        }

        Distances<Integer> expectedDist = null;
        try {
            expectedDist = Distances.readDist(distFile);
        } catch (ClassNotFoundException | IOException e) {
            System.out.println("Fichier '" + distFile + "' manquant ou dans un format incorrect");
            return;
        }

        Graph<Integer> graph = new GraphMaze<>(maze);
        Distances<Integer> dst = new Dijkstra<Integer>().compute(graph, maze.start());

        if (!dst.equals(expectedDist)) {
            System.out.println("Échec pour " + mazeFile);
        } else {
            System.out.println("Succès pour " + mazeFile);
        }
    }
}