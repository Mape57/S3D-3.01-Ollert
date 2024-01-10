package ollert.tache;

import ollert.tache.donneesTache.Etiquette;
import ollert.tache.donneesTache.Utilisateur;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant une tâche principale (une tâche qui contient des tâches sous-tâches)
 */
public class TachePrincipale extends Tache<ListeTaches> {

	//------------------------------------//
	//------------ ATTRIBUTS -------------//
	//------------------------------------//

	/**
	 * Liste parente
	 */
	private ListeTaches parent;

	/**
	 * Liste des taches qui dependent de la tache
	 */
	private final List<TachePrincipale> dependances;

	/**
	 * Liste des taches dont la tache depend
	 */
	private final List<TachePrincipale> antecedents;




	//------------------------------------//
	//----------- CONSTRUCTEURS ----------//
	//------------------------------------//

	/**
	 * Constructeur de la classe TachePrincipale
	 *
	 * @param titre       Titre de la tache
	 * @param listeTaches Liste de taches parente
	 */
	public TachePrincipale(String titre, ListeTaches listeTaches) {
		super(titre);
		this.parent = listeTaches;
		this.dependances = new ArrayList<>();
		this.antecedents = new ArrayList<>();
	}

	public TachePrincipale(Tache<?> tache, ListeTaches parent) {
		super(tache.getTitre());
		this.parent = parent;
		this.dependances = new ArrayList<>();
		this.antecedents = new ArrayList<>();
		this.getSousTaches().addAll(tache.getSousTaches());
		for (SousTache sousTache : this.getSousTaches())
			sousTache.setParent(this);

		this.setDescription(tache.getDescription());
		this.setDateDebut(tache.getDateDebut());
		this.setDateFin(tache.getDateFin());
		this.setPriorite(tache.getPriorite());
		for (Utilisateur utilisateur : tache.getMembres())
			this.ajouterUtilisateur(utilisateur.getPseudo());

		for (Etiquette etiquette : tache.getTags())
			this.ajouterEtiquette(etiquette.getValeur());
	}


	//------------------------------------//
	//------------ METHODES --------------//
	//------------------------------------//

	/**
	 * Ajout d'une dependance a la tache
	 * Ajoute la tache (this) a la liste des antecedents de la tache fournie
	 *
	 * @param tache Tache dependante
	 */
	public void ajouterDependance(TachePrincipale tache) {
		this.dependances.add(tache);
		tache.antecedents.add(this);
	}

	/**
	 * Suppression d'une dependance à la tache
	 * Supprime la tache (this) de la liste des antecedents de la tache fournie
	 *
	 * @param tache Tache dependante
	 */
	public void supprimerDependance(TachePrincipale tache) {
		this.dependances.remove(tache);
		tache.antecedents.remove(this);
	}

	/**
	 * Retourne la liste des taches dont this dependent
	 *
	 * @return Liste des dépendances
	 */
	public List<TachePrincipale> getDependances() {
		return this.dependances;
	}

	/**
	 * Retourne la liste des taches dont this est l'antecedent
	 *
	 * @return Liste des antecedents
	 */
	public List<TachePrincipale> getAntecedents() {
		return this.antecedents;
	}

	/**
	 * Retourne la liste parente
	 *
	 * @return Liste parente
	 */
	@Override
	public ListeTaches getParent() {
		return this.parent;
	}

	/**
	 * Remplace la liste parente par celle fournie
	 *
	 * @param listeTaches nouvelle liste parente
	 * @throws NullPointerException si la liste fournie est null
	 */
	@Override
	public void setParent(ListeTaches listeTaches) {
		if (listeTaches == null) throw new NullPointerException("La liste de tâches ne doit pas être null");
		this.parent = listeTaches;
	}
}
