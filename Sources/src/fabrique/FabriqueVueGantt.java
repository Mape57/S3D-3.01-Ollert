package fabrique;

import javafx.scene.layout.HBox;
import mvc.modele.ModeleOllert;
import mvc.vue.principale.gantt.VuePageGantt;
import mvc.vue.structure.VueListe;
import mvc.vue.structure.VueTache;
import ollert.tool.ParentScrollPane;

/**
 * Implementation de la FabriqueVue pour un affichage sous forme de gantt
 */
public class FabriqueVueGantt extends FabriqueVue {
	// TODO utiliser le Fabrique lors de la creation d'un gannt a la place du canva

	public FabriqueVueGantt(ModeleOllert modeleOllert) {
		super(modeleOllert);
	}

	@Override
	public VuePageGantt creerVuePage() {
		VuePageGantt vuePageGantt = new VuePageGantt();

		// centre de la page
		ParentScrollPane centre = new ParentScrollPane();
		centre.setContentAndChildrenProp(new HBox());

		vuePageGantt.setFillHeight(true);
		vuePageGantt.getChildren().add(centre);

		return vuePageGantt;
	}

	/**
	 * Il n'existe pas de VueListe pour le gantt
	 *
	 * @return null
	 */
	@Override
	public VueListe creerVueListe() {
		return null;
	}

	/**
	 * Il n'existe pas de VueTache pour le gantt
	 *
	 * @return null
	 */
	@Override
	public VueTache creerVueTache() {
		return null;
	}

	/**
	 * Il n'existe pas de VueSousTache pour le gantt
	 *
	 * @return null
	 */
	@Override
	public VueTache creerVueSousTache() {
		return null;
	}
}
