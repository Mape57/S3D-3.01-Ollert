package ollert.tache;

import ollert.Page;
import ollert.Parent;
import ollert.tache.donneesTache.Etiquette;
import ollert.tache.donneesTache.Priorite;
import ollert.tache.donneesTache.Utilisateur;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static ollert.tache.donneesTache.Etiquette.obtenirEtiquette;
import static ollert.tache.donneesTache.Utilisateur.obtenirUtilisateur;


/**
 * Classe représentant une tâche ou une sous-tâches
 */
public abstract class Tache<T extends Parent> extends Enfant<T> implements Parent, Serializable {


	//--------------------------------//
	//            ATTRIBUTS           //
	//--------------------------------//

	/**
	 * Titre de la tache
	 */
	private String titre;

	/**
	 * Description de la tache
	 */
	private String description;

	/**
	 * Liste representant respectivement la date de debut et de fin de la tache
	 */
	private final LocalDate[] dates;

	/**
	 * Liste des sous-taches de la tache
	 */
	private final List<SousTache> sousTaches;

	/**
	 * Priorite de la tache
	 */
	private Priorite priorite;

	/**
	 * Liste des utilisateurs de la tache
	 */
	private final List<Utilisateur> membres;

	/**
	 * Liste des etiquettes de la tache
	 */
	private final List<Etiquette> tags;


	//--------------------------------//
	//          CONSTRUCTEURS         //
	//--------------------------------//

	/**
	 * Constructeur d'une tache
	 *
	 * @param titre Titre de la tache
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
	 * Constructeur d'une tache
	 *
	 * @param titre     Titre de la tache
	 * @param dateDebut Date de debut de la tache
	 * @param dateFin   Date de fin de la tache
	 * @throws NullPointerException si le titre est null
	 */
	public Tache(String titre, LocalDate dateDebut, LocalDate dateFin) {
		if (titre == null) throw new NullPointerException("Le titre ne peut pas être null");
		this.titre = titre;
		this.description = "";
		this.dates = new LocalDate[2];
		this.sousTaches = new ArrayList<>();
		this.priorite = Priorite.INDEFINI;
		this.membres = new ArrayList<>();
		this.tags = new ArrayList<>();
	}


	//--------------------------------//
	//            METHODES            //
	//--------------------------------//

	/**
	 * Ajout d'une nouvelle tache dans la liste des sous-taches
	 * La tache est creee dans la methode
	 *
	 * @param titre Titre de la sous-tâche
	 * @throws NullPointerException si le titre est null
	 */
	public void addSousTache(String titre) {
		if (titre == null) throw new NullPointerException("Le titre ne peut pas être null");
		SousTache sousTache = new SousTache(titre, this);
		this.sousTaches.add(sousTache);
	}


	public void addSousTache(Integer integer, SousTache tacheDragged) {
		this.sousTaches.add(integer, tacheDragged);
	}

	/**
	 * Retourne la liste des sous-taches de la tache
	 *
	 * @return liste des sous-taches
	 */
	public List<SousTache> getSousTaches() {
		return this.sousTaches;
	}

	/**
	 * Supprime la sous-tache fournie en parametre de la liste
	 *
	 * @param sousTache sous-tache a supprimer
	 */
	public void removeSousTache(SousTache sousTache) {
		this.sousTaches.remove(sousTache);
	}

	/**
	 * Retourne la tache a la position specifiee en parametre
	 *
	 * @param indice indice de la sous-tache
	 * @return tache a l'indice specifie
	 */
	public SousTache getSousTache(int indice) {
		return this.sousTaches.get(indice);
	}

	/**
	 * Ajout d'un Utilisateur à la liste des utilisateurs de la tâche
	 * Ajoute l'Utilisateur correspondant au nom passé en paramètre de la fonction à la tâche
	 * Si l'Utilisateur n'existe pas, il est créé et ajouté
	 *
	 * @param nomUtilisateur nom de l'utilisateur
	 */
	public void ajouterUtilisateur(String nomUtilisateur) {
		if (nomUtilisateur.isEmpty()) throw new NullPointerException("Le nom de l'utilisateur ne peut être vide.");
		for (Utilisateur u : this.membres) {
			if (u.getPseudo().equals(nomUtilisateur)) {
				return;
			}
		}
		Page p = trouverListeTaches().getParent();
		String nomPage = p.getTitre();
		this.membres.add(obtenirUtilisateur(nomPage, nomUtilisateur));
	}

	/**
	 * Suppression d'un Utilisateur de la liste des utilisateurs de la tâche
	 * Supprime l'utilisateur correspondant au nom fourni en paramètre
	 *
	 * @param nomUtilisateur nom de l'utilisateur
	 */
	public void supprimerUtilisateur(String nomUtilisateur) {
		boolean existant = false;
		for (Utilisateur u : this.membres) {
			if (u.getPseudo().equals(nomUtilisateur)) {
				existant = true;
				break;
			}
		}
		if (!existant) return;
		Page p = trouverListeTaches().getParent();
		String nomPage = p.getTitre();
		this.membres.remove(Utilisateur.supprimerUtilisateur(nomPage, nomUtilisateur));
	}

	/**
	 * Ajout d'une Etiquette à la liste des étiquettes de la tâche
	 * Ajoute l'étiquette correspondant au nom passé en paramètre de la fonction à la tâche
	 * Si l'Etiquette n'existe pas, elle est créée et ajoutée
	 *
	 * @param nomTag nom de l'étiquette
	 */
	public void ajouterEtiquette(String nomTag) {
		if (nomTag.isEmpty()) throw new NullPointerException("Le nom de l'étiquette ne peut être vide.");
		for (Etiquette e : this.tags) {
			if (e.getValeur().equals(nomTag)) {
				return;
			}
		}
		Page p = trouverListeTaches().getParent();
		String nomPage = p.getTitre();
		this.tags.add(obtenirEtiquette(nomPage, nomTag));
	}

	/**
	 * Suppression d'une étiquette de la liste des étiquettes de la tâche
	 * Supprime l'étiquette correspondant au nom passé en paramètre de la fonction
	 *
	 * @param nomTag nom de l'étiquette
	 */
	public void supprimerEtiquette(String nomTag) {
		boolean existant = false;
		for (Etiquette e : this.tags) {
			if (e.getValeur().equals(nomTag)) {
				existant = true;
				break;
			}
		}
		if (!existant) return;
		Page p = trouverListeTaches().getParent();
		String nomPage = p.getTitre();
		this.tags.remove(Etiquette.supprimerEtiquette(nomPage, nomTag));
	}

	//--- Gestion tâche ---//

	/**
	 * @return le titre de la tâche
	 */
	public String getTitre() {
		return this.titre;
	}

	/**
	 * @param titre le nouveau titre de la tâche
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}

	/**
	 * @return la description de la tâche
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * @param description la nouvelle description de la tâche
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return la date de début de la tâche
	 */
	public LocalDate getDateDebut() {
		return this.dates[0];
	}

	/**
	 * @param dateDebut la nouvelle date de début de la tâche
	 */
	public void setDateDebut(LocalDate dateDebut) {
		this.dates[0] = dateDebut;
	}

	/**
	 * @return la date de fin de la tâche
	 */
	public LocalDate getDateFin() {
		return this.dates[1];
	}

	/**
	 * @param dateFin la nouvelle date de fin de la tâche
	 */
	public void setDateFin(LocalDate dateFin) {
		this.dates[1] = dateFin;
	}

	/**
	 * @return la durée de la tâche en jours
	 */
	public int getDuree() {
		if (this.dates[0] == null || this.dates[1] == null) return 0;
		return (int) ChronoUnit.DAYS.between(this.dates[0], this.dates[1]);
	}


	/**
	 * Obtention de la liste des sous-tâches en partant d'une tache et en remontant jusqu'à la liste de tâches
	 *
	 * @return La liste de tâches mère de la tâche courante
	 */
	public ListeTaches trouverListeTaches() {
		Enfant<?> enfant = (Enfant<?>) this.getParent();
		while (!(enfant instanceof ListeTaches))
			enfant = (Enfant<?>) enfant.getParent();
		return (ListeTaches) enfant;
	}

	public Priorite getPriorite() {
		return this.priorite;
	}

	public void setPriorite(Priorite p) {
		this.priorite = p;
	}

	public List<Utilisateur> getMembres() {
		return membres;
	}

	public List<Etiquette> getTags() {
		return tags;
	}
}
