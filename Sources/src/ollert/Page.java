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
public class Page implements Parent, Serializable {
	/**
	 * Titre de la page
	 */
	private String titre;

	/**
	 * Liste des ListeTaches de la page
	 */
	private List<ListeTaches> listes;

	/**
	 * Constructeur de la classe Page
	 *
	 * @param titre Titre de la page a creer
	 * @throws NullPointerException Si le titre est null
	 */
	public Page(String titre) {
		if (titre == null) throw new NullPointerException("Le titre ne peut pas être null");
		this.titre = titre;
		this.listes = new ArrayList<>();
	}

	/**
	 * Insere une ListeTaches comme dernier element de la page
	 * La ListeTaches est creee a partir du titre fourni
	 *
	 * @param titre Titre de la ListeTaches
	 * @return La liste de taches creee
	 * @throws NullPointerException Si le titre est null
	 */
	public ListeTaches addListe(String titre) {
		if (titre == null) throw new NullPointerException("Le titre ne peut pas être null");
		ListeTaches lt = new ListeTaches(titre, this);
		listes.add(lt);
		return lt;
	}

	/**
	 * Supprime de la page la ListeTaches fournie
	 *
	 * @param liste ListeTaches a supprimer
	 * @return true si la ListeTaches possede l'element, false sinon
	 */
	public boolean removeListe(ListeTaches liste) {
		return listes.remove(liste);
	}

	/**
	 * Retourne la liste à l'indice fourni
	 *
	 * @param indice Indice de la liste a obtenir
	 * @return liste de taches
	 */
	public ListeTaches getListe(int indice) {
		return this.listes.get(indice);
	}

	/**
	 * Retourne le nombre de liste dans la page
	 *
	 * @return nombre de liste dans la page
	 */
	public int sizeListe() {
		return this.listes.size();
	}

	/**
	 * Retourne le titre de la page
	 *
	 * @return titre de la page
	 */
	public String getTitre() {
		return titre;
	}

	/**
	 * Retourne l'ensemble des listes de la page
	 *
	 * @return listes de taches de la page
	 */
	public List<ListeTaches> getListes() {
		return listes;
	}

	/**
	 * Affiche la page, ses listes de tâches et ses archives
	 *
	 * @return Le contenu d'une page sous forme de chaîne de caractères
	 */
	public String toString() {
		String s = "Page : " + this.titre + "\n";
		s += "Listes de tâches : \n";
		for (ListeTaches lt : this.listes) {
			s += "\t" + lt.getTitre() + "\n";
			for (Tache t : lt.getTaches()) {
				s += "\t\t" + t.getTitre() + "\n";
				s += "\t\t\t Priorité : " + t.getPriorite() + "\n";
				s += "\t\t\t Membres : " + "\n";
				for (int i = 0; i < t.getMembres().size(); i++) {
					Utilisateur u = (Utilisateur) t.getMembres().get(i);
					s += "\t\t\t\t" + u.getPseudo() + "\n";
				}
				s += "\t\t\t Tags : " + "\n";
				for (int i = 0; i < t.getTags().size(); i++) {
					Etiquette e = (Etiquette) t.getTags().get(i);
					s += "\t\t\t\t" + e.getValeur() + "\n";
				}
			}
		}
		return s;
	}

}
