package mvc.fabrique;

import mvc.modele.ModeleOllert;
import mvc.vue.liste.VueListe;
import mvc.vue.page.VuePage;
import mvc.vue.sousTache.VueSousTache;
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
	VueTache creerVueTache(ModeleOllert modeleControle);

	/**
	 * Cree la vue d'une liste
	 *
	 * @param modeleControle
	 * @return Vue de la liste
	 */
	VueListe creerVueListe(ModeleOllert modeleControle);

	/**
	 * Cree la vue d'une page
	 *
	 * @param modeleControle
	 * @return Vue de la page
	 */
	VuePage creerVuePage(ModeleOllert modeleControle);

	VueSousTache creerVueSousTache(ModeleOllert modeleControle);
}
