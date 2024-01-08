package mvc.modele;

import mvc.fabrique.FabriqueVue;
import mvc.fabrique.FabriqueVueTableau;
import mvc.vue.Observateur;
import mvc.vue.tache.VueTache;
import ollert.Page;
import ollert.Parent;
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

	private Tache<?> dragged;

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
		this.notifierObservateurs();
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

	public void deplacerTache(int dVerticale, int dHorizontale) {

		ListeTaches liste = (ListeTaches) this.dragged.getParent();
		Tache<?> tache = this.dragged;

		int indexTache = liste.getTaches().indexOf(tache) + dHorizontale;
		if (indexTache < 0 || indexTache >= liste.sizeTaches())
			return;
		int indexListe = this.donnee.getListes().indexOf(liste) + dVerticale;
		if (indexListe < 0 || indexListe >= this.donnee.sizeListe())
			return;


		liste.getTaches().remove(tache);
		if (dVerticale != 0)
			liste = this.donnee.getListeTaches(indexListe);

		if (indexTache > liste.sizeTaches())
			indexTache = liste.sizeTaches();

		liste.getTaches().add(indexTache, (TachePrincipale) tache);
		this.notifierObservateurs();
		this.observateurs.get(0);
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
		while (!t.getSousTaches().isEmpty())
			t = t.getSousTache(indicesCp.remove(0));
		return t;
	}

	public void setDragged(Tache<?> tache) {
		this.dragged = tache;
	}

	public Tache<?> getDragged() {
		return this.dragged;
	}
}
