package ollert;

public abstract class Tache {

    private String titre;
    private String description;
    private ListeTache parent;

    public Tache(String titre){
        // TODO : à compléter
    }

    public abstract void creerSousTache(String titre);
    public abstract Tache supprimerSousTache(int indice);
    public abstract void ajouterDependance(Tache tache);
    public abstract void supprimerDependance(Tache tache);


    public String getTitre() {
        // TODO : à compléter
        return null;
    }

    public void setTitre(String titre) {
        // TODO : à compléter
    }

    public String getDescription() {
        // TODO : à compléter
        return null;
    }

    public void setDescription(String description) {
        // TODO : à compléter
    }
}
