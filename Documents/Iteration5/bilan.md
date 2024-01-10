# Bilan itération 5

## Modification conception
Classe DiagGantt qui hérite de Canvas et qui a des données pour gérer son affichage 
comme la date du début et la date de fin du diagramme.

Changement de place des contrôleurs qui étaient jusqu'à maintenant dans les vues, 
on avait donc le modèle dans le constructeur des vues ce qui n'est pas vraiment correct.
Ainsi, l'association des contrôleurs aux vues est fait dans la fabrique.
Aussi, La fabrique prend le modèle lors de la construction et dispose de celui-ci pour le reste des méthodes. Cela évite la redondance (paramètre modèle dans chaque méthode de la fabrique)



## Drag and Drop


## Vue

### Vue Tableur
La vue tableur est fonctionnelle, mais elle ne dispose pas encore des fonctionnalités les plus récentes comme la gestion des dépendances. De plus
l'affichage des sous-tâches n'est pas encore implémenté (mais la structure est pensée pour).
 


## Gantt
Le diagramme affiche parfaitement les tâches et leurs dépendances avec titre, date et flèche. 
Il manque le fait de pouvoir déplacer les tâches directement dans la VueGantt.
La conception de la VuePage est plus logique avec un attribut DiagGantt héritant de Canvas.
Le canvas s'agrandit en fonction du nombre de tâches et de dépendances.

## Controleur


## Bilan et Futur
On ne peut pas redéfinir la taille des tâches directement dans le Gantt, il faudrait
opter pour le faire avec des rectangles javaFX.

