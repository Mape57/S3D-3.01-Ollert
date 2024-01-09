package mvc.modele;

import mvc.fabrique.FabriqueVue;
import mvc.fabrique.FabriqueVueTableau;
import mvc.vue.Observateur;
import ollert.Page;
import ollert.tache.ListeTaches;
import ollert.tache.Tache;
import ollert.tache.TachePrincipale;

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

	/**
	 * Constructeur de la classe ModeleOllert
	 * L'affichage par defaut est en tableau
	 * Le titre de la page est "defaut"
	 */
	public ModeleOllert() {
		this.donnee = new Page("defaut");
		this.observateurs = new ArrayList<>();
		this.fabrique = new FabriqueVueTableau();
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

	public void addTache(ListeTaches liste, String titre) {
		liste.addTache(titre);
		this.notifierObservateurs();
	}

	public void addListe(String titre) {
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

	public void deplacerTacheDragged(ListeTaches nv_liste, Tache<?> nv_tache) {
		if (this.tacheDragged == null) return;

		int nv_indice = nv_tache == null ? 0 : nv_liste.getTaches().indexOf(nv_tache);
		this.tacheDragged.getParent().removeTache(this.tacheDragged);
		nv_liste.addTache(nv_indice, this.tacheDragged);
		this.tacheDragged.setParent(nv_liste);
		this.notifierObservateurs();
	}

	public void removeListeTache(ListeTaches liste) {
		this.donnee.removeListeTaches(liste);
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
}
