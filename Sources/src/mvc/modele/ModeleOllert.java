package mvc.modele;

import mvc.fabrique.FabriqueVue;
import mvc.fabrique.FabriqueVueTableau;
import mvc.vue.Observateur;
import ollert.Page;
import ollert.tache.ListeTaches;
import ollert.tache.Tache;
import ollert.tache.TachePrincipale;
import ollert.tache.comparateur.ComparateurDateDebut;

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

	private TachePrincipale tacheDragged;
	private ListeTaches listeDragged;

	private Tache<?> tacheEnGrand;
	private List<Integer> indicesDragged;


	private TachePrincipale tacheCible;
	private List<TachePrincipale> listeAnt;

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

	public void deplacerTacheDragged() {
		if (this.tacheDragged == null || this.indicesDragged == null) return;

		ListeTaches nv_liste = this.donnee.getListeTaches(this.indicesDragged.get(0));
		ListeTaches anc_liste = this.tacheDragged.getParent();
		if (nv_liste == anc_liste && this.indicesDragged.get(1) > this.tacheDragged.getParent().getTaches().indexOf(this.tacheDragged)) {
			this.indicesDragged.set(1, this.indicesDragged.get(1) - 1);
		}


		this.tacheDragged.getParent().removeTache(this.tacheDragged);
		nv_liste.addTache(this.indicesDragged.get(1), this.tacheDragged);
		this.tacheDragged.setParent(nv_liste);

		this.tacheDragged = null;
		this.indicesDragged = null;
		this.notifierObservateurs();
	}

	public Tache<?> getTache(List<Integer> indices) {
		// copie de la liste d'indice pour ne pas modifier l'originale
		List<Integer> indicesCp = new ArrayList<>(indices);
		ListeTaches l = this.donnee.getListeTaches(indicesCp.remove(0));
		if (indicesCp.isEmpty())
			return null;
		Tache<?> t = l.getTache(indicesCp.remove(0));
		while (!indicesCp.isEmpty())
			t = t.getSousTache(indicesCp.remove(0));
		return t;
	}

	public void setDraggedTache(TachePrincipale tache) {
		this.tacheDragged = tache;
		if (tache == null) {
			this.indicesDragged = null;
		}

		this.notifierObservateurs();
	}

	public TachePrincipale getDraggedTache() {
		return this.tacheDragged;
	}

	public void setDraggedListe(ListeTaches liste) {
		this.listeDragged = liste;
	}

	public ListeTaches getDraggedListe() {
		return this.listeDragged;
	}

	public Tache<?> getTacheEnGrand() {
		return tacheEnGrand;
	}

	public void setTacheEnGrand(Tache<?> tacheEnGrand) {
		this.tacheEnGrand = tacheEnGrand;
	}

	public List<TachePrincipale> getListeAnt() {
		return listeAnt;
	}

	public void setListeAnt(List<TachePrincipale> listeAnt) {
		this.listeAnt = listeAnt;
	}

	public TachePrincipale getTacheCible() {
		return tacheCible;
	}

	public void setTacheCible(TachePrincipale tacheCible) {
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
