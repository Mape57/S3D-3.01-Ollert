# Itération 2

## Fonctionnalités

### Sérialisation de la classe Page dans un fichier
    - Sauvegarde une page
    - Charge un fichier de sauvegarde

### Ajout attributs tâche
    - Utilisateur
    - Etiquette
    - Priorité

### Implémentation de composite pour la tâche
- Actuellement, nous avions un attribut listeTaches dans la classe Tache qui pointait vers la listeTaches qui contenait la tâche, et qui valait null si pour toutes les sous-tâches. Il faut donc différencier les cas où la tâche est une sous-tâche ou la tâche principale (tout en haut de l’arbre). Le parent n’est pas le même.
###
    - Chaque sous-tâche a un parent Tache
    - Chaque tâche principale a un parent ListeTaches
- On crée donc une interface Parent vide qui référencera tous les parents et une classe abstraite qui référence les enfants et qui pourront avori accès au parent avec getParent()

### MVC - Fabrique abstraite
    - Interfaces vues principales + interfaces implémentant l’interface
    - Faire la fabrique abstraite
    - Mettre en place le modèle
    - Mettre en place le contrôleur
    - Mettre en place la vue classique de la page sans contrôleur (VueTableau)


## Les classes
    - Utilisateur
    - Etiquette
    - Priorité
    - Closeable
    - Sauvegarde


## Diagramme de classe

<img src="">