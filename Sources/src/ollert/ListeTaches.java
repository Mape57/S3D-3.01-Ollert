package ollert;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant une liste de tâches
 */
public class ListeTaches extends Enfant implements Parent, Serializable {
    /**
     * Titre de la liste de tâches
     */
    private String titre;
    /**
     * Liste des tâches de la liste
     */
    private List<Tache> taches;

    /**
     * Constructeur de la classe ListeTaches
     * @param nom Nom de la liste de tâches
     * @param parent Page parente de la liste de tâches
     */
    public ListeTaches(String nom, Page parent){
        if (nom == null) throw new NullPointerException("Le titre ne peut pas être null");
        this.titre = nom;
        this.taches = new ArrayList<Tache>();
        this.parent = parent;
    }

    /**
     * Créer une tâche dans la liste
     * L’ajout de la tâche s’effectue à la fin de la liste des tâches + la description est vide par défaut
     * @param titre Nom de la tâche
     * @return L'indice de la tâche créée
     */
    public int creerTache(String titre){
        if(titre == null) throw new NullPointerException("Le nom de la tache ne doit pas être null");
        Tache tache = new TachePrincipale(titre, this); // la description est vide par défaut
        this.taches.add(tache);
        return this.taches.indexOf(tache); // renvoie l’indice de la tâche (et prends en compte si un jour on décide d'ajouter un tri)
    }

    /**
     * Ajoute une tâche dans la liste à un indice donné
     * @param indice Indice dans le tableau où on veut ajouter la tâche
     * @param tache Tache à ajouter
     */
    public void ajouterTache(int indice, Tache tache){
        if(tache == null) throw new NullPointerException("La tache ne doit pas être null");

        // javadoc add(indice, tache) : IndexOutOfBoundsException - if the index is out of range (index < 0 || index > size())
        if(indice<0 || indice > this.taches.size()) this.taches.add(tache);
        else this.taches.add(indice, tache);
    }

    /**
     * Supprime une tâche de la liste
     * @param indice Indice de la tâche à supprimer
     * @return La tâche supprimée
     */
    public Tache supprimerTache(int indice) {
        // javadoc remove(indice) : IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size())
        if(indice < 0 || indice >= this.taches.size()) throw new IndexOutOfBoundsException("Indice de la tâche à supprimer invalide");

        return this.taches.remove(indice);
    }

    /**
     * Récupère une tâche de la liste
     *
     * @param indice Indice de la tâche à récupérer
     * @return La tâche récupérée
     */
    public Tache obtenirTache(int indice){
        return this.taches.get(indice);
    }

    /**
     * Change le titre de la liste de tâches
     * @param titre Nouveau titre de la liste de tâches
     */
    public void setTitre(String titre){
        if(titre == null) throw new NullPointerException("Le titre de la liste de tâches ne doit pas être null");

        this.titre = titre;
    }

    /**
     * Récupère le titre de la liste de tâches
     * @return Le titre de la liste de tâches
     */
    public String getTitre(){
        return this.titre;
    }

    /**
     * Récupère toutes les tâches de la liste
     * @return La liste des tâches
     */
    public List<Tache> getTaches(){
        return this.taches;
    }
}
