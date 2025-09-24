<<<<<<< HEAD
# SAE 2.02 - Graphes et Labyrinthes
## Groupe 108
- 👑 Angela Dos Santos Ribeiro 
- Anastasia Minkov  
- Gabriel Saltré  
- Romann Greco Brulport

## Description du Projet
Ce projet consiste à implémenter l'algorithme de Dijkstra pour trouver le plus court chemin dans un graphe, puis à l'appliquer pour résoudre des labyrinthes. 

## Architecture du Projet
Le projet est structuré en deux parties principales:

### Partie 1: Représentation des graphes et Algorithme de Dijkstra
- Implémentation de l'interface `Graph` avec `GrapheHHAdj`
- Implémentation de l'algorithme de Dijkstra dans la classe `Dijkstra` qui implémente l'interface `ShortestPath`
- Tests unitaires pour valider le fonctionnement de l'algorithme

### Partie 2: Résolution de Labyrinthes
- Implémentation de l'adaptateur `GraphMaze` pour transformer un labyrinthe en graphe
- Classes `Animation` et `Checker` pour visualiser et vérifier les solutions

## Implémentation de Dijkstra
Notre implémentation de l'algorithme de Dijkstra:
- Utilise une file de priorité pour sélectionner efficacement le sommet avec la distance minimale
- Gère correctement les cas particuliers (cycles, chemins multiples, graphes non connexes)
- Vérifie l'absence d'arcs à valuation négative (lance une exception le cas échéant)

## Tests
Nous avons créé plusieurs tests unitaires pour vérifier le bon fonctionnement de notre implémentation:
- `TestGrapheAvecBoucle`: Vérifie que l'algorithme gère correctement les cycles
- `TestGrapheCheminsMultiples`: Vérifie que l'algorithme choisit bien le chemin le plus court
- `TestGrapheConnexe`: Teste le fonctionnement sur un graphe connexe simple
- `TestGrapheNonConnexe`: Vérifie le comportement avec des composantes non connexes
- `TestGrapheValuationNegative`: Vérifie que l'exception est bien levée pour des arcs négatifs
- `TestGrapheVide`: Teste le comportement sur un graphe vide ou avec un sommet isolé

Tous les tests fournis sur Moodle passent également.

## Difficultés Rencontrées et Solutions
- Nous avons rencontré des difficultés avec la lecture des fichiers `.maze` du dossier `bench`
- Un problème de typage (INTEGER au lieu de INT) a été résolu
- Après débogage, tous les problèmes de lecture ont été résolus

## Conclusion
Ce projet nous a permis d'appliquer concrètement les concepts de théorie des graphes, notamment l'algorithme de Dijkstra, et de développer une solution  pour la résolution de problèmes de chemin le plus court.
=======
# algo-dijisktra
>>>>>>> 3712ddabe2dfaf42ffee50154f26ea8633adc3bd
