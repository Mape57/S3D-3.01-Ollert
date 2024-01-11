package fabrique;

import mvc.modele.ModeleOllert;
import mvc.vue.structure.VueListe;
import mvc.vue.structure.VuePage;
import mvc.vue.structure.VueTache;

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
