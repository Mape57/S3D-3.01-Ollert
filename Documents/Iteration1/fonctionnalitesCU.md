# Itération 1

Le but de cette itération est de créer la base mère de notre application. Il faut pouvoir créer une page, une liste de tâche et des tâches. De plus, il faut pouvoir effectuer les opérations de base sur les éléments.

## Les fonctionnalités

- Page
    - Créer une liste de tâche

      La création d’une liste de tâche **demande un nom**. Si le nom :

        - nom=null ⇒ NullPointerException
        - nom valide ⇒ ajout ListeTache dans la page + renvoie son indice

      Ajout à la fin de la liste des “ListeTache” de la page

    - Supprimer une liste de tâche

      La suppression d’une liste de tâche **demande l’indice de l’élément** à supprimer dans le tableau des “ListeTache” de la page. Si :

        - indice valide ⇒ suppression de la ListeTache + renvoie la ListeTache
        - indice non valide ⇒ annulation de la suppression + IndiceInvalideException()

    - Archiver une liste de tâches

      L’archivage d’une liste de tâche demande l’indice de l’élément à archiver dans le tableau des “ListeTache” de la page. Si :

        - indice valide ⇒ ajout des tâches de la liste dans l’archive + remove de la liste des “ListeTache” + renvoie la “ListeTache”
        - indice non valide ⇒ annulation archivage + IndiceInvalideException()

    - Archiver une tâche

      L’archivage d’une tâche demande l’indice de l’objet “ListeTache” puis l’indice dans la tâche elle-même dans l’objet “ListeTache”. Si :

        - indices valides ⇒ archivage de la tâche + supprime tache dans ListeTache + renvoie la tâche archivée
        - indices non valides ⇒ IndiceInvalideException()

- Liste de tâches
    - Créer une tâche

      La création d’une tâche demande un nom de tâche. Si :

        - nom = null ⇒ annulation ajout + NullPointerException
        - nom valide ⇒ ajout de la tâche dans la “ListeTache” + renvoie l’indice de la tâche

      L’ajout de la tâche s’effectue à la fin de la liste des tâches + la description est vide par défaut

    - Ajouter une tâche

      L’ajout d’une tâche demande un objet “Tache” et un indice de position dans la liste. Si :

        - Objet = null ⇒ annulation de l’ajout + NullPointerException
        - Objet valide ⇒ si indice :
            - indice non valide ⇒ ajout en fin de liste
            - indice valide ⇒ ajout à la position

    - Supprimer une tâche

      La suppression d’une tâche demande l’indice de la tâche dans la liste. Si :

        - indice valide ⇒ suppression de la tâche + renvoie la tâche
        - indice non valide ⇒ annulation de la suppression + IndiceInvalideException()

    - Renommer la liste de tâches

      Le renommage d’une liste de tâche demande un nom String. Si :

        - nom=null ⇒ NullPointerException()
        - nom valide ⇒ renomme la “ListeTache”

- Tâches
    - Renommer la tâche

      Le renommage d’une tâche demande un nom String. Si :

        - nom=null ⇒ NullPointerException()
        - nom valide ⇒ renomme la tache

    - Changer la description

      La modification de la description d’une tâche demande une chaîne de caractères quelconque. Si :

        - chaîne = null ⇒ NullPointerException()
        - sinon ⇒ modification de la description avec

    - Changer la date de début

      Changer la date de début demande un objet “LocalDate”. Si :

      dateDebut=null ⇒ NullPointerException()

      dateFin définie si :

        - dateDebut > dateFin ⇒ DateConditionException()
        - dateDebut ≤ dateFin ⇒ modification de la dateDebut

      dateFin non définie alors :

        - modification de la dateDebut

    - Changer la date de fin

      Changer la date de fin demande un objet “LocalDate”. Si :

      dateFin=null ⇒ NullPointerException()

      dateDebut définie si :

        - dateFin < dateDebut ⇒ DateConditionException()
        - dateFin ≥ dateDebut ⇒ modification de la dateFin

      dateDebut non définie alors :

        - modification de la dateFin

    - Créer une sous-tâche

      La création d’une sous-tâche demande un nom pour la sous-tâche. Si :

        - nom=null ⇒ NullPointerException()
        - nom valide ⇒ création de l’objet “Tache”

      L’objet “Tache” créé est modifié pour mettre à jour sa tâche mère et ensuite ajouté à la liste des sous-tâches de la tâche mère.

    - Supprimer une sous-tâche

      La suppression d’une sous-tâche demande l’indice de la sous-tâche dans la liste des sous-tâches de la tâche mère. Si :

        - indice valide ⇒ remove tache dans la liste des sous-tâches + renvoie la tâche supprimée
        - indice invalide ⇒ IndiceInvalideException()

    - Ajouter une dépendance

      L’ajout d’une dépendance demande un objet “Tache”. Si :

        - tache=null ⇒ NullPointerException
        - tache valide ⇒ mise à jour de la liste des dépendances pour this + mise à jour des antécédents pour la tâche fournie

    - Supprimer une dépendance

      La suppression d’une dépendance demande un objet “Tache”. Si :

        - tache=null ⇒ NullPointerException
        - tache valide ⇒ mise à jour de la liste des dépendances pour this + mise à jour des antécédents pour la tache fournie

## Les classes

- Page
- ListeTache
- Tache

## Assignation

- Grégoire : classe Page
- Mateo : classe ListeTache
- Stéphane : classe Tache
- Enzo : aide sur toutes les classes (car que 3 classes à faire)
