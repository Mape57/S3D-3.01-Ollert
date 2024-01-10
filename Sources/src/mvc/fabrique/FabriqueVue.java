package mvc.fabrique;

import mvc.modele.ModeleOllert;
import mvc.vue.liste.VueListe;
import mvc.vue.page.VuePage;
import mvc.vue.tache.VueTache;

/**
 * Base de la fabrique abstraite
 * Les implementations de cette interface permettent de creer les differents composants d'un affichage
 */
public abstract class FabriqueVue {

	/**
	 * Modele de l'application
	 */
	protected final ModeleOllert modeleOllert;

	public FabriqueVue(ModeleOllert modeleOllert){
		this.modeleOllert = modeleOllert;
	}

	/**
	 * Cree la vue d'une tache
	 *
	 * @return Vue de la tache
	 */
	public abstract VueTache creerVueTache();

	/**
	 * Cree la vue d'une liste
	 * @return Vue de la liste
	 */
	public abstract VueListe creerVueListe();

	/**
	 * Cree la vue d'une page
	 * @return Vue de la page
	 */
	public abstract VuePage creerVuePage();

 	public abstract VueTache creerVueSousTache();
}
