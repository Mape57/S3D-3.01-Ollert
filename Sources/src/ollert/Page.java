package ollert;

import ollert.tache.donneesTache.Etiquette;
import ollert.tache.donneesTache.Utilisateur;
import ollert.tache.ListeTaches;
import ollert.tache.Tache;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant une page d'un ollert
 */
public class Page implements Parent, Serializable{

    /**
     * Titre de la page
     */
    private String titre;

    /**
     * Liste des tâches archivées
     */
    private List<Tache> archives;

    /**
     * Liste des ListeTaches de la page
     */
    private List<ListeTaches> listes;

    /**
     * Constructeur de la classe Page
     * @param titre Titre de la pafe a créer
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
     * @param titre Titre de la ListeTaches à créer
     * @return L'indice de la ListeTaches créée
     * @throws NullPointerException Si le titre est null
     */
    public int creerListeTaches(String titre){
        if (titre == null) throw new NullPointerException("Le titre ne peut pas être null");
        ListeTaches lt = new ListeTaches(titre, this);
        listes.add(lt);
        return this.listes.indexOf(lt);
    }

    /**
     * Supprimer une ListeTaches de la page avec ses tâches
     * @param indice Indice de la ListeTaches à supprimer
     * @return La ListeTaches supprimée
     * @throws IndexOutOfBoundsException Si l'indice est invalide
     */
    public ListeTaches supprimerListeTaches(int indice){
        if (indice < 0 || indice >= listes.size()) throw new IndexOutOfBoundsException("L'indice de la liste est invalide");
        return listes.remove(indice);
    }

    /**
     * Archiver les tâches d'une ListeTaches
     * @param indice Indice de la ListeTaches à archiver
     * @return La ListeTaches archivée
     * @throws IndexOutOfBoundsException Si l'indice est invalide
     */
    public ListeTaches archiverListeTaches(int indice){
        ListeTaches lt = this.supprimerListeTaches(indice);
        this.archives.addAll(lt.getTaches());
        return lt;
    }

    /**
     * Obtenir la ListeTaches à l'indice fourni
     * @param indice Indice de la ListeTaches à obtenir
     * @return La liste de tâches
     * @throws IndexOutOfBoundsException Si l'indice est invalide
     */
    public ListeTaches obtenirListe(int indice){
        return this.listes.get(indice);
    }

    public int obtenirNbListe() {
    	return this.listes.size();
    }

    /**
     * Obtenir la tâche à l'indice fourni dans la liste de tâches à l'indice fourni
     * @param indiceListe Indice de la liste de tâches
     * @param indiceTache Indice de la tâche
     * @return La tâche
     */
    public Tache archiverTache(int indiceListe, int indiceTache){
        if (indiceListe < 0 || indiceListe >= listes.size()) throw new IndexOutOfBoundsException("L'indice de la liste est invalide");
        ListeTaches lt = listes.get(indiceListe);

        if (indiceTache < 0 || indiceTache >= lt.getTaches().size()) throw new IndexOutOfBoundsException("L'indice de la tache est invalide");
        Tache t = lt.getTaches().remove(indiceTache);
        this.archives.add(t);

        return t;
    }

    /**
     * Getter du titre de la page
     * @return Le titre de la page
     */
    public String getTitre() {return titre;}

    /**
     * Getter des archives d'une page
     * @return les archives de la page
     */
    public List<Tache> getArchives() {return archives;}

    /**
     * Getter des listes de tâches d'une page
     * @return les listes de tâches de la page
     */
    public List<ListeTaches> getListes() {return listes;}

    /**
     * Affiche la page, ses listes de tâches et ses archives
     * @return Le contenu d'une page sous forme de chaîne de caractères
     */
    public String toString(){
        String s = "Page : " + this.titre + "\n";
        s += "Listes de tâches : \n";
        for (ListeTaches lt : this.listes){
            s += "\t" + lt.getTitre() + "\n";
            for (Tache t : lt.getTaches()){
                s += "\t\t" + t.getTitre() + "\n";
                s += "\t\t\t Priorité : " + t.getPriorite() + "\n";
                s += "\t\t\t Membres : " + "\n";
                for (int i = 0; i<t.getMembres().size(); i++){
                    Utilisateur u = (Utilisateur) t.getMembres().get(i);
                    s += "\t\t\t\t" + u.getPseudo() + "\n";
                }
                s += "\t\t\t Tags : " + "\n";
                for (int i = 0; i<t.getTags().size(); i++){
                    Etiquette e = (Etiquette) t.getTags().get(i);
                    s += "\t\t\t\t" + e.getValeur() + "\n";
                }
            }
        }
        s += "Archives : \n";
        for (Tache t : this.archives){
            s += "\t" + t.getTitre() + "\n";
        }
        return s;
    }

}
