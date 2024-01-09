# Bilan itération 3

## Modification conception
Retrait de l'attribut Tache/ListeTaches/Page des Vue correspondantes
L'acces se fera grace a la symetrie de construction entre les vues et les donnees
- acces au parent puis recherche de la position de la vue dans la liste des vues enfants du parent,...

## Drag and Drop
Implementation partielle (fonctionnement avant changement de conception)
Il devra donc etre modifie lors de l'iteration 4

## Vue
Ajout de nombreuse Vue relativement a l'affichage en Tableau
- Vue Tache avec l'affichage des attributs
- Vue ListeTaches avec l'affichage du titre et de ces taches
- Vue Page avec l'affichage des listes de taches

## Controleur
Ajout de certains Controleur 
- Ajout de tache
- Ajout et suppression de liste de taches
- Deplacement de tache (voir Bug et Drag and Drop)

## Bug
Le deplacement en drag and drop ne fonctionne plus suite a la modification d'acces de la tache correspondante a une vue tache
Il a ete decide de ne pas corriger ce bug dans l'iteration 3 pour ce concentrer a la correction des fonctionnalites principales
Sont implementation est prevu pour l'iteration 4 et ayant une base deja existante, il ne devrait pas etre trop difficile a corriger

## Bilan et Futur
L'iteration 3 a ete une iteration tres productive
Elle a permis de mettre en place les bases visuelles de l'application
Certains bugs apparaissent mais sont prevu pour etre corriger dans l'iteration 4
Notre conception initiale est globalement respectee, nous constatons cependant que nous avons été trop en détail dans ca description
Idem pour les iterations du debut ou nous avons ete trop prevoyant du futur et faisant des methodes au final inutile ou inadapte a la conception