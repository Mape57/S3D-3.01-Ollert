package mvc;

import mvc.vue.structure.Sujet;
import fabrique.FabriqueVue;
import fabrique.FabriqueVueTableau;
import mvc.vue.structure.Observateur;
import ollert.donnee.Page;
import ollert.donnee.ListeTaches;
import ollert.donnee.tache.SousTache;
import ollert.donnee.tache.TacheAbstraite;
import ollert.donnee.tache.Tache;

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

	private TacheAbstraite<?> tacheDragged;
	private ListeTaches listeDragged;

	private TacheAbstraite<?> tacheEnGrand;
	private List<Integer> indicesDragged;


	private Tache tacheCible;
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

	public void addListeTache(String titre) {
		this.donnee.addListeTaches(titre);
		this.notifierObservateurs();
	}

	public void deplacerListeDraggedAvant(ListeTaches liste) {
		if (this.listeDragged == null) return;

		int indice = liste == null ? this.donnee.sizeListe() : this.donnee.getListes().indexOf(liste);

		this.donnee.removeListeTaches(this.listeDragged);
		this.donnee.addListeTaches(indice, this.listeDragged);
		this.notifierObservateurs();
	}

	public void deplacerDraggedVersSousTache() {
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

	private boolean verifierDate(TacheAbstraite<?> parent, TacheAbstraite<?> tache) {
		if (tache.getDateDebut() == null || tache.getDateFin() == null) return true;
		if (parent.getDateDebut() == null || parent.getDateFin() == null) return true;
		return !tache.getDateDebut().isBefore(parent.getDateDebut()) && !tache.getDateFin().isAfter(parent.getDateFin());
	}

	public void deplacerDraggedVersTache() {
		if (this.tacheDragged == null || this.indicesDragged == null) return;
		List<Integer> indices = new ArrayList<>(this.indicesDragged);
		ListeTaches nv_liste = this.donnee.getListeTaches(indices.remove(0));

		Tache tache;

		if (this.tacheDragged instanceof SousTache) {
			((TacheAbstraite<?>) this.tacheDragged.getParent()).removeSousTache((SousTache) this.tacheDragged);
			tache = new Tache(this.tacheDragged, nv_liste);
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

	public void setDraggedTache(TacheAbstraite<?> tache) {
		this.tacheDragged = tache;
		if (tache == null) {
			this.indicesDragged = null;
		}

		this.notifierObservateurs();
	}

	public TacheAbstraite<?> getDraggedTache() {
		return this.tacheDragged;
	}

	public void setDraggedListe(ListeTaches liste) {
		this.listeDragged = liste;
		this.notifierObservateurs();
	}

	public ListeTaches getDraggedListe() {
		return this.listeDragged;
	}

	public TacheAbstraite<?> getTacheEnGrand() {
		return tacheEnGrand;
	}

	public void setTacheEnGrand(TacheAbstraite<?> tacheEnGrand) {
		this.tacheEnGrand = tacheEnGrand;
	}

	public List<Tache> getListeAnt() {
		return listeAnt;
	}

	public void setListeAnt(List<Tache> listeAnt) {
		this.listeAnt = listeAnt;
	}

	public Tache getTacheCible() {
		return tacheCible;
	}

	public void setTacheCible(Tache tacheCible) {
		this.tacheCible = tacheCible;
	}

	public void setIndicesDragged(List<Integer> indicesDragged) {
		this.indicesDragged = indicesDragged;
		this.notifierObservateurs();
	}

	public List<Integer> getIndicesDragged() {
		return this.indicesDragged;
	}
}
