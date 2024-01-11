package mvc.modele;

import fabrique.FabriqueVue;
import fabrique.FabriqueVueTableau;
import mvc.vue.structure.Observateur;
import ollert.donnee.ListeTaches;
import ollert.donnee.Page;
import ollert.donnee.tache.SousTache;
import ollert.donnee.tache.Tache;
import ollert.donnee.tache.TacheAbstraite;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe ModeleOllert, modele principale de l'application.
 */
public class ModeleOllert implements Sujet {
	/**
	 * Attributs principal fournissant l'acces aux donnees de la page
	 */
	private Page donnee;

	/**
	 * Liste des observateurs de la fenetre
	 */
	private List<Observateur> observateurs;

	/**
	 * Fabrique correspond au type d'affichage de la page
	 */
	private FabriqueVue fabrique;

	/**
	 * Tache en cours de drag
	 */
	private TacheAbstraite<?> tacheDragged;

	/**
	 * Liste en cours de drag
	 */
	private ListeTaches listeDragged;

	/**
	 * Tache a afficher completement
	 */
	private TacheAbstraite<?> tacheComplete;
	/**
	 * Liste des indices localisant la nouvelle position de la tache en cours de drag
	 */
	private List<Integer> indicesDragged;

	/**
	 * Tache cible par la modification de dependance
	 */
	private Tache tacheCible;
	/**
	 * Liste des taches antecedentes a la tache cible
	 */
	private List<Tache> listeAnt;

	/**
	 * Constructeur de la classe ModeleOllert
	 * L'affichage par defaut est en tableau
	 * Le titre de la page est "defaut"
	 */
	public ModeleOllert() {
		this.donnee = new Page("defaut");
		this.observateurs = new ArrayList<>();
		this.fabrique = new FabriqueVueTableau(this);
	}

	/**
	 * Methode de modification de l'affichage
	 *
	 * @param fabrique La nouvelle fabrique à utiliser pour l'affichage
	 */
	public void setFabrique(FabriqueVue fabrique) {
		this.fabrique = fabrique;
		this.observateurs.remove(0);
	}

	/**
	 * Récupère la fabrique actuellement utilisée pour l'affichage
	 *
	 * @return fabrique de l'affichage
	 */
	public FabriqueVue getFabrique() {
		return this.fabrique;
	}


	/**
	 * Modifie les données de la page
	 *
	 * @param donnee nouvelle page
	 */
	public void setDonnee(Page donnee) {
		this.donnee = donnee;
		this.notifierObservateurs();
	}

	/**
	 * Récupère la page
	 *
	 * @return la page actuelle
	 */
	public Page getDonnee() {
		return this.donnee;
	}

	@Override
	public void ajouterObservateur(Observateur observateur) {
		this.observateurs.add(observateur);
	}

	@Override
	public void supprimerObservateur(Observateur observateur) {
		this.observateurs.remove(observateur);
	}

	@Override
	public void notifierObservateurs() {
		for (Observateur observateur : this.observateurs)
			observateur.actualiser(this);
	}

	/**
	 * Methode ajoutant une liste de taches a la page a partir du titre fourni
	 *
	 * @param titre titre de la liste de taches
	 */
	public void addListeTache(String titre) {
		this.donnee.addListeTaches(titre);
		this.notifierObservateurs();
	}

	/**
	 * Methode deplacant la liste en cours de drag avant la liste fournie en parametre
	 * Si la liste fournie est null, la liste en cours de drag est deplacee en derniere position
	 *
	 * @param liste liste de reference
	 */
	public void deplacerListeDraggedAvant(ListeTaches liste) {
		if (this.listeDragged == null) return;

		int indice = liste == null ? this.donnee.sizeListe() : this.donnee.getListes().indexOf(liste);

		this.donnee.removeListeTaches(this.listeDragged);
		this.donnee.addListeTaches(indice, this.listeDragged);
		this.notifierObservateurs();
	}

	/**
	 * Methode initialisant le deplacement de la tache en cours de drag
	 */
	public void deplacerDragged() {
		if (this.tacheDragged == null || this.indicesDragged == null) return;
		if (this.indicesDragged.size() <= 2) {
			deplacerDraggedVersTache();
		} else {
			deplacerDraggedVersSousTache();
		}
	}

	/**
	 * Deplace la tache en cours de drag vers la position de la sous tache actuellement selectionnee (par indicesDragged)
	 */
	private void deplacerDraggedVersSousTache() {
		if (this.tacheDragged == null || this.indicesDragged == null) return;
		List<Integer> indices = new ArrayList<>(this.indicesDragged);
		ListeTaches nv_liste = this.donnee.getListeTaches(indices.remove(0));
		TacheAbstraite<?> nv_tache = nv_liste.getTache(indices.remove(0));
		SousTache tache;

		while (indices.size() > 1)
			nv_tache = nv_tache.getSousTache(indices.remove(0));

		if (verifierDate(nv_tache, this.tacheDragged)) {
			if (this.tacheDragged instanceof Tache) {
				((ListeTaches) this.tacheDragged.getParent()).removeTache(this.tacheDragged);
				tache = new SousTache((Tache) this.tacheDragged, nv_tache);
			} else {
				((TacheAbstraite<?>) this.tacheDragged.getParent()).removeSousTache((SousTache) this.tacheDragged);
				tache = (SousTache) this.tacheDragged;
				tache.setParent(nv_tache);
			}
			nv_tache.addSousTache(indices.get(0), tache);
		}

		this.tacheDragged = null;
		this.indicesDragged = null;
		this.notifierObservateurs();
	}

	/**
	 * Verifie qu'une tache est bien dans les dates de la tache parente
	 *
	 * @param parent tache parente
	 * @param tache  tache a verifier
	 * @return true si la tache est dans les dates de la tache parente, false sinon
	 */
	private boolean verifierDate(TacheAbstraite<?> parent, TacheAbstraite<?> tache) {
		if (tache.getDateDebut() == null || tache.getDateFin() == null) return true;
		if (parent.getDateDebut() == null || parent.getDateFin() == null) return true;
		return !tache.getDateDebut().isBefore(parent.getDateDebut()) && !tache.getDateFin().isAfter(parent.getDateFin());
	}

	/**
	 * Deplace la tache en cours de drag vers la position de la liste actuellement selectionnee (par indicesDragged)
	 */
	private void deplacerDraggedVersTache() {
		if (this.tacheDragged == null || this.indicesDragged == null) return;
		List<Integer> indices = new ArrayList<>(this.indicesDragged);
		ListeTaches nv_liste = this.donnee.getListeTaches(indices.remove(0));

		Tache tache;

		if (this.tacheDragged instanceof SousTache) {
			((TacheAbstraite<?>) this.tacheDragged.getParent()).removeSousTache((SousTache) this.tacheDragged);
			tache = new Tache((SousTache) this.tacheDragged, nv_liste);
		} else {
			ListeTaches liste = (ListeTaches) this.tacheDragged.getParent();
			if (liste.getTaches().indexOf((Tache) this.tacheDragged) < indices.get(0) && liste == nv_liste)
				indices.set(0, indices.get(0) - 1);

			liste.removeTache(this.tacheDragged);
			tache = (Tache) this.tacheDragged;
			tache.setParent(nv_liste);
		}
		nv_liste.addTache(indices.get(0), tache);
		this.tacheDragged = null;
		this.indicesDragged = null;
		this.notifierObservateurs();
	}

	/**
	 * Retourne la tache se trouvant aux coordonnées fournis en parametre
	 *
	 * @param indices liste d'indices
	 * @return tache correspondant aux indices
	 */
	public TacheAbstraite<?> getTache(List<Integer> indices) {
		// copie de la liste d'indice pour ne pas modifier l'originale
		List<Integer> indicesCp = new ArrayList<>(indices);
		ListeTaches l = this.donnee.getListeTaches(indicesCp.remove(0));
		if (indicesCp.isEmpty())
			return null;
		TacheAbstraite<?> t = l.getTache(indicesCp.remove(0));
		while (!indicesCp.isEmpty())
			t = t.getSousTache(indicesCp.remove(0));
		return t;
	}

	/**
	 * Modifie la tache en cours de drag
	 * Si null -> mise a null de indicesDragged
	 *
	 * @param tache tache en cours de drag
	 */
	public void setDraggedTache(TacheAbstraite<?> tache) {
		this.tacheDragged = tache;
		if (tache == null) {
			this.indicesDragged = null;
		}

		this.notifierObservateurs();
	}

	/**
	 * Retourne la tache en cours de drag
	 *
	 * @return tache en cours de drag
	 */
	public TacheAbstraite<?> getDraggedTache() {
		return this.tacheDragged;
	}

	/**
	 * Modifie la liste en cours de drag
	 *
	 * @param liste liste en cours de drag
	 */
	public void setDraggedListe(ListeTaches liste) {
		this.listeDragged = liste;
		this.notifierObservateurs();
	}

	/**
	 * Retourne la liste en cours de drag
	 *
	 * @return liste en cours de drag
	 */
	public ListeTaches getDraggedListe() {
		return this.listeDragged;
	}

	/**
	 * Retourne la tache a afficher complete
	 *
	 * @return tache complete
	 */
	public TacheAbstraite<?> getTacheComplete() {
		return tacheComplete;
	}

	/**
	 * Modifie la tache a afficher complete
	 *
	 * @param tacheComplete tache complete
	 */
	public void setTacheComplete(TacheAbstraite<?> tacheComplete) {
		this.tacheComplete = tacheComplete;
	}

	/**
	 * Modifie la liste des taches antecedentes a la tache cible
	 *
	 * @param listeAnt liste des taches antecedentes
	 */
	public void setListeAnt(List<Tache> listeAnt) {
		this.listeAnt = listeAnt;
	}

	/**
	 * Retourne la liste des taches antecedentes a la tache cible
	 *
	 * @return liste des taches antecedentes
	 */
	public List<Tache> getListeAnt() {
		return listeAnt;
	}

	/**
	 * Modifie la tache ciblé par la modification de dépendance
	 *
	 * @param tacheCible tache cible
	 */
	public void setTacheCible(Tache tacheCible) {
		this.tacheCible = tacheCible;
	}

	/**
	 * Retourne la tache ciblé par la modification de dépendance
	 *
	 * @return tache cible
	 */
	public Tache getTacheCible() {
		return tacheCible;
	}

	/**
	 * Modifie la liste des indices localisant la nouvelle position de la tache en cours de drag
	 *
	 * @param indicesDragged liste d'indices
	 */
	public void setIndicesDragged(List<Integer> indicesDragged) {
		this.indicesDragged = indicesDragged;
		this.notifierObservateurs();
	}

	/**
	 * Retourne la liste des indices localisant la future nouvelle position de la tache en cours de drag
	 *
	 * @return liste d'indices
	 */
	public List<Integer> getIndicesDragged() {
		return this.indicesDragged;
	}
}
