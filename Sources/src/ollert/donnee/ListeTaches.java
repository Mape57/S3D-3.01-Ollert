package ollert.donnee;

import ollert.donnee.structure.Enfant;
import ollert.donnee.structure.Parent;
import ollert.donnee.tache.TachePrincipale;
import ollert.donnee.tache.Tache;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe representation une liste de tâches
 */
public class ListeTaches extends Enfant<Page> implements Parent, Serializable {

	//-------------------------//
	//        Attributs        //
	//-------------------------//

	/**
	 * Titre de la liste de taches
	 */
	private String titre;
	/**
	 * Liste des taches de la liste
	 */
	private final List<TachePrincipale> taches;


	//-------------------------//
	//      Constructeurs      //
	//-------------------------//

	/**
	 * Constructeur de la classe ListeTaches
	 *
	 * @param nom    Nom de la liste de taches
	 * @param parent Page parente de la liste de taches
	 * @throws NullPointerException si le nom ou la page parente est null
	 */
	public ListeTaches(String nom, Page parent) {
		if (nom == null || parent == null) throw new NullPointerException("les paramètres ne doivent pas être null");
		this.titre = nom;
		this.taches = new ArrayList<>();
		this.parent = parent;
	}


	//-------------------------//
	//        Méthodes         //
	//-------------------------//

	//--- Gestion taches ---//

	/**
	 * Insere une nouvelle tache possedant le titre specifie en parametre dans la liste
	 * La tache est creee dans la methode
	 *
	 * @param titre Nom de la tâche
	 * @return tache nouvellement creee
	 * @throws NullPointerException si le titre est null
	 */
	public TachePrincipale addTache(String titre) {
		if (titre == null) throw new NullPointerException("Le nom de la tache ne doit pas être null");
		TachePrincipale tache = new TachePrincipale(titre, this);
		this.taches.add(tache);
		return tache;
	}

	/**
	 * Insere la tache dans la liste a l'indice specifie
	 *
	 * @param indice position de la tache dans la liste
	 * @param tache  tache a inserer
	 */
	public void addTache(int indice, TachePrincipale tache) {
		this.taches.add(indice, tache);
	}

	/**
	 * Supprime la tache fournie en parametre de la liste
	 *
	 * @param tache tache a supprimer
	 * @return booleen indiquant le succes de la suppression
	 */

	public boolean removeTache(Tache tache) {
		return this.taches.remove(tache);
	}

	/**
	 * Retourne le nombre de tache de la liste
	 *
	 * @return taille de la liste
	 */

	public int sizeTaches() {
		return this.taches.size();
	}

	/**
	 * Retourne la tache a la position specifiee en parametre
	 *
	 * @param indice indice de la tache
	 * @return tache a l'indice specifie
	 */
	public TachePrincipale getTache(int indice) {
		return this.taches.get(indice);
	}

	/**
	 * Retourne la liste des taches
	 *
	 * @return liste des taches
	 */
	public List<TachePrincipale> getTaches() {
		return this.taches;
	}

	/**
	 * Retourne le titre de la liste
	 *
	 * @return titre de la liste
	 */
	public String getTitre() {
		return this.titre;
	}

	/**
	 * Remplace le titre de la tache par le titre specifie en parametre
	 *
	 * @param titre nouveau titre de la tache
	 * @throws NullPointerException si le titre est null
	 */
	public void setTitre(String titre) {
		if (titre == null) throw new NullPointerException("Le titre de la liste de tâches ne doit pas être null");
		this.titre = titre;
	}

	/**
	 * Retourne la page parente a la liste
	 *
	 * @return parent de la liste
	 */
	@Override
	public Page getParent() {
		return (Page) parent;
	}

	/**
	 * Remplace la page parente par celle fournie en parametre
	 *
	 * @param parent nouvelle page parente
	 */
	@Override
	public void setParent(Page parent) {
		if (parent == null) throw new NullPointerException("La page parente ne doit pas être null");
		this.parent = parent;
	}
}
