# Bilan itération 3

## Modification conception
Retrait de l'attribut "Tache/ListeTaches/Page" des vues correspondantes. L'accès se fera grâce à la symétrie de construction entre les vues et les données - accès au parent puis recherche de la position de la vue dans la liste des vues enfants du parent.


## Drag and Drop
Implémentation partielle (fonctionnement avant changement de conception). Il devra donc être modifié lors de l'itération 4.


## Vue
Ajout de nombreuses vues relatives à l'affichage en tableau:

    Vue Tache avec l'affichage des attributs.
    Vue ListeTaches avec l'affichage du titre et de ses tâches.
    Vue Page avec l'affichage des listes de tâches.

## Controleur
Ajout de certains contrôleurs :

    Ajout de tâche.
    Ajout et suppression de liste de tâches.
    Déplacement de tâche (voir Bug et Drag and Drop).

## Bug
Le déplacement en drag and drop ne fonctionne plus suite à la modification d'accès de la tâche correspondante à une vue tâche. Il a été décidé de ne pas corriger ce bug dans l'itération 3 pour se concentrer sur la correction des fonctionnalités principales. Son implémentation est prévue pour l'itération 4 et, ayant une base déjà existante, il ne devrait pas être trop difficile à corriger.


## Bilan et Futur
L'itération 3 a été une itération très productive. Elle a permis de mettre en place les bases visuelles de l'application. Certains bugs apparaissent, mais sont prévus pour être corrigés dans l'itération 4. Notre conception initiale est globalement respectée, cependant, nous constatons que nous sommes allés trop dans les détails dans sa description. De même pour les itérations du début où nous avons été trop prévoyants pour l'avenir, créant des méthodes finalement inutiles ou inadaptées à la conception.
