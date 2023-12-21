package mvc.fabrique;

import mvc.vue.liste.VueListeTableau;
import mvc.vue.page.VuePageTableau;
import mvc.vue.tache.VueTacheTableau;
import ollert.Page;
import ollert.tache.ListeTaches;
import ollert.tache.TachePrincipale;

/**
 * Implementation de la FabriqueVue pour un affichage en tableau
 * : affichage classique (colonnes)
 */
public class FabriqueVueTableau implements FabriqueVue {
	/**
	 * Crée la vue d'une tache sous forme de tableau
	 * @param tache tache que la Vue devra gérer
	 * @return Vue de la tache
	 */
	@Override
	public VueTacheTableau creerVueTache(TachePrincipale tache) {
		return new VueTacheTableau(tache);
	}

	/**
	 * Crée la vue d'une liste sous forme de tableau
	 * @param liste liste que la Vue devra gérer
	 * @return Vue de la liste
	 */
	@Override
	public VueListeTableau creerVueListe(ListeTaches liste) {
		return new VueListeTableau(liste);
	}

	/**
	 * Crée la vue d'une page sous forme de tableau
	 * @param page page que la Vue devra gérer
	 * @return Vue de la page
	 */
	@Override
	public VuePageTableau creerVuePage(Page page) {
		return new VuePageTableau(page);
	}
}
