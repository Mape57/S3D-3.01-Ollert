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

## Tâche non faite
    - Diagramme de scène

## Bug
    pas de bug

## Bilan et Futur
    La base de l'application a été maintenant faite (conception de tâche, étiquettes et utilisateurs et sauvegarde des tâches en local), 
    nous devons nous occuper maintenant de l'interface graphique qui va nous prendre beaucoup de temps. Nous allons
    continuer le travail sur les fabriques pour générer les différentes vues et commencer à faire les controllers. Il faut aussi mettre 
    en place la conception des actions possibles par l'utilisateur (Dispatcher ou Patron Visiteur)