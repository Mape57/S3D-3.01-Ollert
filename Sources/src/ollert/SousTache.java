package ollert;

/**
 * Classe représentant une sous-tâche
 */
public class SousTache extends Tache<Tache>{
    /**
     * Constructeur d'une SousTache
     * @param titre Titre de la sous-tâche
     * @param tacheParente Tâche parente
     */
    public SousTache(String titre, Tache tacheParente){
        super(titre);
        this.parent = tacheParente;
    }

    /**
     * Setter de la tâche parente
     * @param tacheParente Nouvelle tâche parente
     */
    public void setParent(Tache tacheParente){
        if(tacheParente == null) throw new NullPointerException("La tâche parente ne doit pas être null");
        this.parent = tacheParente;
    }


    /**
     * Getter de la tâche parente
     * @return Tâche parente
     */
    @Override
    public Tache getParent() {
        return (Tache)this.parent;
    }
}
