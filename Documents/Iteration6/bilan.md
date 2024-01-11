# Bilan itération 6

## Fin du drag&drop
Le drag&drop est maintenant fonctionnel
Les données ne sont modifiées que lorsque l'on relâche le clic de la souris.

## Restructuration des fichiers
Les fichiers ont été restructurés pour être plus clairs et plus faciles à retrouver.
Notamment, en triant les controleurs par fonctionnalité (affichage complet, drag&drop,..)
ou encore en séparant les VuePrincipales des Vue plus précises, là aussi par fonctionnalité (vue en tableau, tableur,..).

## Intégration des méthodes de localisation
L'affichage en tableur et en complet recherchait leur tache associée manuellement
Leur methode getLocalisation, getChildrenPrincipale et getParentPrincipale ont été intégrées
Permettant une simplification et une amélioration de la lisibilité du code.

## Modification conception


## Bilan
On ne peut pas redéfinir la taille des tâches directement dans le Gantt, il faudrait
opter pour le faire avec des rectangles javaFX.

