# SAE2-02 – Rapport de Projet : Graphes

## Groupe 108

- 👑 Angela Dos Santos Ribeiro 
- Anastasia Minkov  
- Gabriel Saltré  
- Romann Greco Brulport  

## Avancement du Projet

### Partie 1 – Algorithme de Dijkstra

La première partie du projet est fonctionnelle. L'algorithme de Dijkstra a été correctement implémenté et testé. Il permet de calculer les chemins les plus courts dans un graphe, et son intégration est considérée comme terminée.
Nous avons rajouté des Tests Unitaires supplementaires, pour verifier que tout le code de la partie 1 est fonctionnel

### Partie 2 – Intégration avec Checker

- Difficultés initiales avec la lecture des fichiers `.maze` du dossier `bench`.
- Difficultés avec type: INTEGER a la place de INT
- Après plusieurs heures de débogage, les problèmes de lecture ont été résolus.
- `Checker` fonctionne désormais correctement avec notre implémentation.
- Les tests fournis sur Moodle passent.

### Adaptateur

L’adaptateur a été entièrement codé.

## Tests

J'ai rajouté des tests unitaire pour verifier que tout fonctionne corretement:
- TestGrapheAvecBoucle
- TestGrapheCheminsMultiples
- TestGrapheConnexe
- TestGrapheNonConnexe
- TestGrapheValuationNegative
- TestGrapheVide 

## Conclusion

Nous avons finalisé la première partie du projet, et la deuxieme partie . Tous les tests founi passent automatiquement
