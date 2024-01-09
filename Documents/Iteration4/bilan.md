# Bilan itération 4

## Modification conception
Cette itération a été très graphique, nous n'avons donc pas eu besoin de modifier la conception.

## Drag and Drop
Implémentation partielle (fonctionnement avant changement de conception)
Il devra donc être modifié lors de l'itération 5.

## Vue
Ajout de nombreuses Vues relatives à l'affichage en Tableur
- Vue ListeTaches avec l'affichage du titre et de ces tâches
- Vue Page avec l'affichage des listes de tâches

## Gantt
Réflexion sur la classe JavaFX pour l'affichage du Gantt.
Un StackedBarChart est un histogramme à l'horizontale, et n'est donc pas
optimal pour l'affichage du Gantt. Nous avons donc décidé de faire un Canvas et de
gérer tout via des boucles sur les dépendances.

## Contrôleur
Modification de certains Contrôleurs pour ajouter une confirmation en pop-up
- Ajout de tâche
- Ajout et suppression de liste de tâches
- Modifier le titre de la liste de tâches
- Modifier le titre de la tâche

## Bilan et Futur
L'itération 4 a été une itération très graphique avec uniquement du JavaFX. Nous avons donc à la fin de cette itération
une V1 de design relativement fonctionnelle. Les vues principales sont fonctionnelles avec l'ensemble des
contrôleurs. Nous avons cependant rencontré des problèmes avec le drag and drop qui n'est pas fonctionnel, mais qui sera modifié
durant la prochaine itération. Durant l'itération 5, nous devrons terminer rapidement les vues tableau et tableur, le drag and drop et surtout
la vue du Gantt qui restera un gros morceau.
