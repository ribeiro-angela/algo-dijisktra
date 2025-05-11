# SAE2-02 – Rapport de Projet : Graphes

## Groupe 108

- Angela Dos Santos Ribeiro  
- Anastasia Minkov  
- Gabriel Saltré  
- Romann Greco Brulport  

## Avancement du Projet

### Partie 1 – Algorithme de Dijkstra

La première partie du projet est fonctionnelle. L'algorithme de Dijkstra a été correctement implémenté et testé. Il permet de calculer les chemins les plus courts dans un graphe, et son intégration est considérée comme terminée.

### Partie 2 – Intégration avec Checker

Nous rencontrons actuellement des difficultés avec la classe `Checker`. Bien que les chemins d'accès aux fichiers `.maze` situés dans le dossier `bench` semblent corrects, nous ne parvenons pas à les lire dans notre programme.

Nous avons vérifié que les chemins sont accessibles, mais l'ouverture ou la lecture effective des fichiers échoue, ce qui empêche `Checker` de fonctionner comme prévu. Ce blocage limite fortement notre capacité à valider cette partie du projet avec les tests fournis sur Moodle.

### Adaptateur

L’adaptateur n’a pas encore été entièrement codé à ce stade du projet.

## Conclusion

Nous avons finalisé la première partie du projet, mais nous sommes actuellement bloqués sur des problèmes liés à la lecture des fichiers `.maze` dans le cadre de la seconde partie. Cela nous empêche de faire fonctionner `Checker` et de passer les tests automatiquement.
