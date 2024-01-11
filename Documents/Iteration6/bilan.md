# Bilan itération 6


## Fin du drag&drop

Le drag&drop est maintenant fonctionnel
Les données ne sont modifiées que lorsque l'on relâche le clic de la souris.


## Restructuration des fichiers

Les fichiers ont été restructurés pour être plus clairs et plus faciles à retrouver.
Notamment, en triant les contrôleurs par fonctionnalité (affichage complet, drag&drop,..)
ou encore en séparant les VuePrincipales des Vue plus précises, là aussi par fonctionnalité (vue en tableau, tableur,..).


## Intégration des méthodes de localisation

L'affichage en tableur et en complet recherchait leur tache associée manuellement.
Leur méthode getLocalisation, getChildrenPrincipale et getParentPrincipale ont été intégrées
Permettant une simplification et une amélioration de la lisibilité du code.


## Modification conception



## Interface de tache et de sous-tache

Réglages d'un bug => Toutes les taches sont maintenant ouvrable sous forme d'interface.
Ajout de l'affichage de la liste des sous-tâches dans l'interface et prises en compte de ces dernières pour les dates.

## Bug
La sauvegarde bug, car l'utilisation d'un multiton pour les etiquettes et membres. Ces élément ne sont pas contenu dans la classe
Page et donc la sérialisation de la classe Page ne sauvegarde pas toutes les informations de la classe.

## Bilan

On ne peut pas redéfinir la taille des tâches directement dans le Gantt, il faudrait
opter pour le faire avec des rectangles javaFX.



