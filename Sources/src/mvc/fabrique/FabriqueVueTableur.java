package mvc.fabrique;

import mvc.modele.ModeleOllert;
import mvc.vue.liste.VueListeTableur;
import mvc.vue.page.VuePageTableur;
import mvc.vue.tache.VueTacheTableur;

/**
 * Implementation de la FabriqueVue pour un affichage tableur
 * : affichage classique (colonnes)
 */
public class FabriqueVueTableur implements FabriqueVue {

	/**
	 * Crée la vue d'une tache sous forme tableur
	 * @return Vue de la tache
	 */
	@Override
	public VueTacheTableur creerVueTache(ModeleOllert modeleControle) {
		return new VueTacheTableur(modeleControle);
	}

	/**
	 * Crée la vue d'une liste sous forme tableur
	 *
	 * @param modeleControle
	 * @return Vue de la liste
	 */
	@Override
	public VueListeTableur creerVueListe(ModeleOllert modeleControle) {
		return new VueListeTableur(modeleControle);
	}

	/**
	 * Crée la vue d'une page sous forme tableur
	 *
	 * @param modeleControle
	 * @return Vue de la page
	 */
	@Override
	public VuePageTableur creerVuePage(ModeleOllert modeleControle) {
		return new VuePageTableur(modeleControle);
	}
}
