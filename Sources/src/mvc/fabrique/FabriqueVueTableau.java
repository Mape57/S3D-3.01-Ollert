package mvc.fabrique;

import mvc.vue.liste.VueListeTableau;
import mvc.vue.page.VuePageTableau;
import mvc.vue.tache.VueTacheTableau;
import ollert.ListeTaches;
import ollert.Page;
import ollert.TachePrincipale;

/**
 * Implementation de la FabrqiueVue pour un affichage en tableau
 * : affichage classique (colonnes)
 */
public class FabriqueVueTableau implements FabriqueVue {
	@Override
	public VueTacheTableau creerVueTache(TachePrincipale tache) {
		return new VueTacheTableau(tache);
	}

	@Override
	public VueListeTableau creerVueListe(ListeTaches liste) {
		return new VueListeTableau(liste);
	}

	@Override
	public VuePageTableau creerVuePage(Page page) {
		return new VuePageTableau(page);
	}
}
