package ollert;

import java.io.Serializable;
import ollert.donneesTache.Etiquette;
import ollert.donneesTache.Priorite;
import ollert.donneesTache.Utilisateur;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


/**
 * Classe représentant une tâche ou une sous-tâches
 */
public abstract class Tache<T extends Parent> extends Enfant<T> implements Parent, Serializable{
	/**
	 * Titre de la tâche
	 */
	private String titre;
	/**
	 * Description de la tâche
	 */
	private String description;
	/**
	 * Liste représentant respectivement la dates de début et de fin de la tâche
	 */
	private LocalDate[] dates;
	/**
	 * Liste des sous-tâches de la tâche
	 */
	private List<Tache> sousTaches;
	/**
	 * Priorité de la tâche
	 */
	private Priorite priorite;
	/**
	 * Liste des utilisateurs de la tâche
	 */
	private List<Utilisateur> membres;
	/**
	 * Liste des étiquettes de la tâche
	 */
	private List<Etiquette> tags;

	/**
	 * Création d'une tâche
	 *
	 * @param titre Titre de la tâche
	 * @throws NullPointerException si le titre est null
	 */
	public Tache(String titre) {
		if (titre == null) throw new NullPointerException("Le titre ne peut pas être null");
		this.titre = titre;
		this.description = "";
		this.dates = new LocalDate[2];
		this.sousTaches = new ArrayList<>();
		this.priorite = Priorite.INDEFINI;
		this.membres = new ArrayList<>();
		this.tags = new ArrayList<>();
	}

	/**
	 * Création d'une sous-tâche
	 *
	 * @param titre Titre de la sous-tâche
	 * @throws NullPointerException si le titre est null
	 */
	public void creerSousTache(String titre) {
		if (titre == null) throw new NullPointerException("Le titre ne peut pas être null");
		Tache sousTache = new SousTache(titre, this);
		this.sousTaches.add(sousTache);
	}

	/**
	 * Suppression d'une sous-tâche accessible par un chemin d'indices
	 *
	 * @param indices Chemin d'indices, n'est pas modifié au cours de l'exécution
	 * @throws NoSuchElementException    si aucun index n'est fourni
	 * @throws IndexOutOfBoundsException si un index est invalide
	 */
	public void supprimerSousTache(List<Integer> indices) {
		// copie de la liste pour éviter de la modifier
		List<Integer> cp_indices = new ArrayList<>(indices);

		int lastIndex = cp_indices.remove(cp_indices.size() - 1);
		Tache tache = this.obtenirSousTache(cp_indices);
		tache.sousTaches.remove(lastIndex);
	}

	/**
	 * Obtention d'une sous-tâche accessible par un chemin d'indices
	 *
	 * @param indices Chemin d'indices, n'est pas modifié au cours de l'exécution
	 * @return La sous-tâche
	 * @throws NoSuchElementException    si aucun index n'est fourni
	 * @throws IndexOutOfBoundsException si un index est invalide
	 */
	public Tache obtenirSousTache(List<Integer> indices) {
		// copie de la liste pour éviter de la modifier
		List<Integer> cp_indices = new ArrayList<>(indices);
		Tache tache = this.sousTaches.get(cp_indices.remove(0));

		if (!cp_indices.isEmpty())
			tache = tache.obtenirSousTache(cp_indices);

		return tache;
	}

	/**
	 * Ajout d'un Utilisateur à la liste des utilisateurs de la tâche
	 * Ajoute l'Utilisateur correspondant au nom passé en paramètre de la fonction à la tâche
	 * Si l'Utilisateur n'existe pas, il est créé et ajouté
	 *
	 * @param nomUtilisateur nom de l'utilisateur
	 */
	public void ajouterUtilisateur(String nomUtilisateur) {

	}

	/**
	 * Suppression d'un Utilisateur de la liste des utilisateurs de la tâche
	 * Supprime l'utilisateur correspondant au nom fourni en paramètre
	 *
	 * @param nomUtilisateur nom de l'utilisateur
	 */
	public void supprimerUtilisateur(String nomUtilisateur) {

	}

	/**
	 * Ajout d'une Etiquette à la liste des étiquettes de la tâche
	 * Ajoute l'étiquette correspondant au nom passé en paramètre de la fonction à la tâche
	 * Si l'Etiquette n'existe pas, elle est créée et ajoutée
	 *
	 * @param nomTag nom de l'étiquette
	 */
	public void ajouterEtiquette(String nomTag) {

	}

	/**
	 * Suppression d'une étiquette de la liste des étiquettes de la tâche
	 * Supprime l'étiquette correspondant au nom passé en paramètre de la fonction
	 *
	 * @param nomTag nom de l'étiquette
	 */
	public void supprimerEtiquette(String nomTag) {

	}

	public String getTitre() {
		return this.titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDateDebut() {
		return this.dates[0];
	}

	public void setDateDebut(LocalDate dateDebut) {
		this.dates[0] = dateDebut;
	}

	public LocalDate getDateFin() {
		return this.dates[1];
	}

	public void setDateFin(LocalDate dateFin) {
		this.dates[1] = dateFin;
	}


	/**
	 * Obtention de la liste des sous-tâches en partant d'une tache et en remontant jusqu'à la liste de tâches
	 * @return La liste de tâches mère de la tâche courante
	 */
	public ListeTaches trouverListeTaches() {
		Enfant enfant = (Enfant)this.getParent();
		while(!(enfant instanceof ListeTaches)){
			Tache tache = (Tache) parent;
			enfant = (Enfant)tache.getParent();
		}

		return (ListeTaches)enfant;
	}

	public void setPriorite(Priorite p){this.priorite = p;}
	public Priorite getPriorite(){return this.priorite;}

	public List<Utilisateur> getMembres() {return membres;}

	public List<Etiquette> getTags() {return tags;}
}
