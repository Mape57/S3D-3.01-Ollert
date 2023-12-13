package ollert;

import java.util.List;

public class ListeTache{

    private String titre;
    private Page parent;
    private List<Tache> taches;

    public ListeTache(String nom){
        // TODO : Vérifier unicité du nom
    }

    public int creerTache(String nom){
        // TODO : Implémenter la méthode
        return -1;
    }

    public void ajouterTache(Tache tache, int indice){
        // TODO : Implémenter la méthode
    }

    public Tache supprimerTache(int indice){
        // TODO : Implémenter la méthode
        return null;
    }

    public Tache obtenirTache(int indice){
        // TODO : Implémenter la méthode
        return null;
    }

    public void setTitre(String titre){
        // TODO : Implémenter la méthode
    }

    public String getTitre(){
        // TODO : Implémenter la méthode
        return null;
    }
}
