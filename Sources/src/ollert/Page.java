package ollert;

import exceptions.IndiceInvalideException;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant une page d'un ollert
 */
public class Page {

    /**
     * Titre de la page
     */
    private String titre;

    /**
     * Liste des tâches archivées
     */
    private List<Tache> archives;

    /**
     * Liste des listes de tâches
     */
    private List<ListeTaches> listes;

    /**
     * Constructeur de la classe Page
     * @param titre Titre de la page
     * @throws NullPointerException Si le titre est null
     */
    public Page(String titre){
        if (titre == null) throw new NullPointerException("Le titre ne peut pas être null");
        this.titre = titre;
        this.archives = new ArrayList<Tache>();
        this.listes = new ArrayList<ListeTaches>();
    }

    /**
     * Créer une liste de tâches dans la page
     * @param titre Titre de la liste
     * @return L'indice de la liste créée
     * @throws NullPointerException Si le titre est null
     */
    public int creerListeTaches(String titre){
        if (titre == null) throw new NullPointerException("Le titre ne peut pas être null");
        ListeTaches lt = new ListeTaches(titre, this);
        listes.add(lt);
        return listes.size()-1;
    }

    /**
     * Supprimer une liste de tâches de la page
     * @param indice Indice de la liste à supprimer
     * @return La liste supprimée
     * @throws IndiceInvalideException Si l'indice est invalide
     */
    public ListeTaches supprimerListeTaches(int indice) throws IndiceInvalideException{
        if (indice < 0 || indice >= listes.size()) throw new IndiceInvalideException("L'indice de la liste est invalide");
        return listes.remove(indice);
    }

    /**
     * Archiver les tâches d'un liste de tâches
     * @param indice Indice de la liste à archiver
     * @return La liste archivée
     * @throws IndiceInvalideException Si l'indice est invalide
     */
    public ListeTaches archiverListeTaches(int indice) throws IndiceInvalideException{
        ListeTaches lt = this.supprimerListeTaches(indice);
        this.archives.addAll(lt.getTaches());
        return lt;
    }

    /**
     * Obtenir la liste de tâches à l'indice fourni
     * @param indice Indice de la liste à obtenir
     * @return La liste de tâches
     * @throws IndiceInvalideException Si l'indice est invalide
     */
    public ListeTaches obtenirListe(int indice) throws IndiceInvalideException{
        if (indice < 0 || indice >= listes.size()) throw new IndiceInvalideException("L'indice de la liste est invalide");
        return this.listes.get(indice);
    }

    /**
     * Obtenir la tâche à l'indice fourni dans la liste de tâches à l'indice fourni
     * @param indiceListe Indice de la liste de tâches
     * @param indiceTache Indice de la tâche
     * @return La tâche
     * @throws IndiceInvalideException Si l'indice est invalide
     */
    public Tache archiverTache(int indiceListe, int indiceTache) throws IndiceInvalideException{
        if (indiceListe < 0 || indiceListe >= listes.size()) throw new IndiceInvalideException("L'indice de la liste est invalide");
        ListeTaches lt = listes.get(indiceListe);

        if (indiceTache < 0 || indiceTache >= lt.getTaches().size()) throw new IndiceInvalideException("L'indice de la tache est invalide");
        Tache t = lt.getTaches().remove(indiceTache);
        this.archives.add(t);

        return t;
    }


    public String getTitre() {return titre;}
    public List<Tache> getArchives() {return archives;}
    public List<ListeTaches> getListes() {return listes;}

    public String toString(){
        String s = "Page : " + this.titre + "\n";
        s += "Listes de tâches : \n";
        for (ListeTaches lt : this.listes){
            s += "\t" + lt.getTitre() + "\n";
            for (Tache t : lt.getTaches()){
                s += "\t\t" + t.getTitre() + "\n";
            }
        }
        s += "Archives : \n";
        for (Tache t : this.archives){
            s += "\t" + t.getTitre() + "\n";
        }
        return s;
    }

}
