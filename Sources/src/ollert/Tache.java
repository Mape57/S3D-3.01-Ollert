package ollert;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Classe représentant une tâche ou une sous-tâches
 */
public class Tache implements Serializable {
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
	 * Liste de taches parente de la tâche
	 * Peut être null si la tâche n'est pas dans une liste
	 */
	private ListeTaches listeParente;
	/**
	 * Liste des tâches qui dépendent de la tâche
	 */
	private List<Tache> dependances;
	/**
	 * Liste des tâches dont la tâche dépend
	 */
	private List<Tache> antecedents;
	/**
	 * Liste des sous-tâches de la tâche
	 */
	private List<Tache> sousTaches;
	/**
	 * Tâche parente de la tâche si c'est une sous-tâche
	 * Peut être null si la tâche n'a pas de tâche parente / n'est pas une sous-tâche
	 */
	private Tache tacheParente;

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
		this.listeParente = null;
		this.dependances = new ArrayList<>();
		this.antecedents = new ArrayList<>();
		this.sousTaches = new ArrayList<>();
		this.tacheParente = null;
	}

	/**
	 * Création d'une sous-tâche
	 *
	 * @param titre Titre de la sous-tâche
	 * @throws NullPointerException si le titre est null
	 */
	public void creerSousTache(String titre) {
		if (titre == null) throw new NullPointerException("Le titre ne peut pas être null");
		Tache sousTache = new Tache(titre);
		sousTache.setTacheParente(this);
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
		int baseIndex = cp_indices.remove(0);

		if (cp_indices.isEmpty())
			return this.sousTaches.get(baseIndex);
		else
			return this.sousTaches.get(baseIndex).obtenirSousTache(cp_indices);
	}

	/**
	 * Ajout d'une dépendance à la tâche
	 * Ajoute la tâche (this) à la liste des antecedents de la tâche fournie
	 *
	 * @param tache Tâche dépendante
	 */
	public void ajouterDependance(Tache tache) {
		this.dependances.add(tache);
		tache.antecedents.add(this);
	}

	/**
	 * Suppression d'une dépendance à la tâche
	 * Supprime la tâche (this) de la liste des antecedents de la tâche fournie
	 *
	 * @param tache Tâche dépendante
	 */
	public void supprimerDependance(Tache tache) {
		this.dependances.remove(tache);
		tache.antecedents.remove(this);
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

	public ListeTaches getListeParente() {
		return this.listeParente;
	}

	public void setListeParente(ListeTaches listeParente) {
		this.listeParente = listeParente;
	}

	public Tache getTacheParente() {
		return this.tacheParente;
	}

	public void setTacheParente(Tache tacheParente) {
		this.tacheParente = tacheParente;
	}

	public List<Tache> getDependances() {
		return this.dependances;
	}

	public List<Tache> getAntecedents() {
		return this.antecedents;
	}
}
