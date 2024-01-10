package mvc.fabrique;

import mvc.vue.liste.VueListe;
import mvc.vue.page.VuePage;
import mvc.vue.tache.VueTache;

/**
 * Base de la fabrique abstraite
 * Les implementations de cette interface permettent de creer les differents composants d'un affichage
 */
public interface FabriqueVue {
	/**
	 * Cree la vue d'une tache
	 *
	 * @return Vue de la tache
	 */
	VueTache creerVueTache();

	/**
	 * Cree la vue d'une liste
	 * @return Vue de la liste
	 */
	VueListe creerVueListe();

	/**
	 * Cree la vue d'une page
	 * @return Vue de la page
	 */
	VuePage creerVuePage();

	VueTache creerVueSousTache();
}
