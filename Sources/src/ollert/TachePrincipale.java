package ollert;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant une tâche principale
 */
public class TachePrincipale extends Tache<ListeTaches>{
    /**
     * Liste des tâches qui dépendent de la tâche
     */
    private List<TachePrincipale> dependances;
    /**
     * Liste des tâches dont la tâche dépend
     */
    private List<TachePrincipale> antecedents;

    /**
     * Constructeur de la classe TachePrincipale
     * @param titre Titre de la tâche
     * @param listeTaches Liste de tâches parente
     */
    public TachePrincipale(String titre, ListeTaches listeTaches){
        super(titre);
        this.parent = listeTaches;
        this.dependances = new ArrayList<>();
        this.antecedents = new ArrayList<>();
    }

    /**
     * Ajout d'une dépendance à la tâche
     * Ajoute la tâche (this) à la liste des antecedents de la tâche fournie
     * @param tache Tâche dépendante
     */
    public void ajouterDependance(TachePrincipale tache) {
        this.dependances.add(tache);
        tache.antecedents.add(this);
    }

    /**
     * Suppression d'une dépendance à la tâche
     * Supprime la tâche (this) de la liste des antecedents de la tâche fournie
     * @param tache Tâche dépendante
     */
    public void supprimerDependance(TachePrincipale tache) {
        this.dependances.remove(tache);
        tache.antecedents.remove(this);
    }

    /**
     * Getter de la liste des dépendances
     * @return Liste des dépendances
     */
    public List<TachePrincipale> getDependances() {
        return this.dependances;
    }

    /**
     * Getter de la liste des antécédents
     * @return Liste des antécédents
     */
    public List<TachePrincipale> getAntecedents() {
        return this.antecedents;
    }

    /**
     * Setter de la liste de tâches parente
     * @param listeTaches Liste de tâches parente
     */
    public void setParent(ListeTaches listeTaches){
        if(listeTaches == null) throw new NullPointerException("La liste de tâches ne doit pas être null");
        super.parent = listeTaches;
    }

    /**
     * Getter de la liste de tâches parente
     * @return Liste de tâches parente
     */
    @Override
    public ListeTaches getParent() {
        return (ListeTaches) this.parent;
    }
}
