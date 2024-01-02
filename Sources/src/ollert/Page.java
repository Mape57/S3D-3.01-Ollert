package ollert;

import ollert.tache.ListeTaches;
import ollert.tache.Tache;
import ollert.tache.donneesTache.Etiquette;
import ollert.tache.donneesTache.Utilisateur;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant une page d'un ollert
 */
public class Page implements Parent, Serializable {


	//-------------------------//
	//        Attributs        //
	//-------------------------//

	/**
	 * Titre de la page
	 */
	private final String titre;

	/**
	 * Liste des ListeTaches de la page
	 */
	private final List<ListeTaches> listes;


	//-------------------------//
	//      Constructeurs      //
	//-------------------------//

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




	//-------------------------//
	//        Méthodes         //
	//-------------------------//

	//---Gestion pages---//

	/**
	 * Renvoie du titre de la page
	 *
	 * @return titre page
	 */
	public String getTitre() {return titre;}


	//---Gestion listes ListeTaches---//

	/**
	 * Insere une ListeTaches comme dernier element de la page
	 * La ListeTaches est creee a partir du titre fourni
	 *
	 * @param titre Titre de la ListeTaches
	 * @return La liste de taches creee
	 * @throws NullPointerException Si le titre est null
	 */
	public ListeTaches addListeTaches(String titre) {
		if (titre == null) throw new NullPointerException("Le titre ne peut pas être null");
		ListeTaches lt = new ListeTaches(titre, this);
		listes.add(lt);
		return lt;
	}

	/**
	 * Retourne le nombre de liste dans la page
	 *
	 * @return nombre de liste dans la page
	 */
	public int sizeListe() {return this.listes.size();}

	/**
	 * Retourne la ListeTache à l'indice fourni
	 *
	 * @param indice Indice de la ListeTaches
	 * @return la ListeTaches demandee
	 */
	public ListeTaches getListeTaches(int indice) {
		return this.listes.get(indice);
	}

	/**
	 * Supprime de la page la ListeTaches fournie
	 *
	 * @param liste ListeTaches a supprimer
	 * @return true si la ListeTaches possede l'element, false sinon
	 */
	public boolean removeListeTaches(ListeTaches liste) {
		return this.listes.remove(liste);
	}

	public List<ListeTaches> getListes() {
		return this.listes;
	}
}
