package mvc.fabrique;

import mvc.vue.liste.VueListe;
import mvc.vue.page.VuePage;
import mvc.vue.tache.VueTache;
import ollert.ListeTaches;
import ollert.Page;
import ollert.TachePrincipale;

/**
 * Base de la fabrique abstraite
 * Les implementations de cette interface permettent de creer les differents composants d'un affichage
 */
public interface FabriqueVue {
	/**
	 * Cree la vue d'une tache
	 *
	 * @param tache tache que la Vue devra gerer
	 * @return Vue de la tache
	 */
	VueTache creerVueTache(TachePrincipale tache);

	/**
	 * Cree la vue d'une liste
	 *
	 * @param liste liste que la Vue devra gerer
	 * @return Vue de la liste
	 */
	VueListe creerVueListe(ListeTaches liste);

	/**
	 * Cree la vue d'une page
	 *
	 * @param page page que la Vue devra gerer
	 * @return Vue de la page
	 */
	VuePage creerVuePage(Page page);
}
