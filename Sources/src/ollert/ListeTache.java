package ollert;

import java.util.ArrayList;
import java.util.List;

public class ListeTache{

    private String titre;
    private Page parent;
    private List<Tache> taches;

    public ListeTache(String nom, Page parent){
        if (nom == null) throw new NullPointerException("Le titre ne peut pas être null");
        this.titre = nom;
        this.taches = new ArrayList<Tache>();
        this.parent = parent;
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

    public String getTitre(){return this.titre;}
    public List<Tache> getTaches(){return this.taches;}
}
