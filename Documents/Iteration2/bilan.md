# Bilan itération 2

## Modification conception
    - remise en place d'un composite pour les tâches

## Ajout Terminé
    - Tâche - étiquette
    - Tâche - priorité
    - Tâche - sauvegarde
    - MVC - Modèle
    - MVC - Vue Fabrique Abstraite

## Ajout en cours
    - Utilisateur (test a faire)

## Refaire conception de tâche
    -  Les tâches tout en haut de l'arborescence doivent avoir un parent ListeTaches
        et les sous-tâches doivent avoir un parent Tache
    - Type générique pour gérer les différents getParent()
    - Complication : Changer le nom des types pour plus de clarté dans tout le code

## MVC et Fabrique Abstraite
    Les structures sont implémentées avec un cas d'utilisation pour l'instant
    Une seule fabrique existe donc un seul affichage
    Le MVC a egalement un affichage simple de quelques tâches et listes de tâches
    L'affichage est bien actualisé
    Des tests pour verifier la symétrie entre les vues et les données n'ont pas été fait du a la presence du javafx

## Tâche non faite
    - Diagramme de scène
    - test symetrie

## Bug
    pas de bug

## Bilan
    Pour finir, cette itération a été plutot productive avec l'ajout de