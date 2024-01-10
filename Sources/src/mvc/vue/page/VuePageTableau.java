package mvc.vue.page;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import mvc.fabrique.FabriqueVueTableau;
import mvc.modele.ModeleOllert;
import mvc.modele.Sujet;
import mvc.vue.liste.VueListeTableau;
import ollert.Page;
import ollert.tache.ListeTaches;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe de la vue représentant une page sous forme de tableau
 */
public class VuePageTableau extends HBox implements VuePage {

	/**
	 * Constructeur de la classe VuePageTableau
	 */
	public VuePageTableau() {
		this.setStyle("-fx-background-color: #eee0cb; -fx-padding: 20px;");
	}

	/**
	 * Actualise la vue
	 * @param sujet le modèle à partir duquel la vue est actualisée
	 */
	@Override
	public void actualiser(Sujet sujet) {
		ModeleOllert modele = (ModeleOllert) sujet;
		HBox centre = (HBox) ((ScrollPane) this.getChildren().get(0)).getContent();
		centre.getChildren().clear();

		Page page = modele.getDonnee();
		List<ListeTaches> liste = page.getListes();

		for (ListeTaches l : liste) {
			VueListeTableau vl_tmp = new FabriqueVueTableau(modele).creerVueListe();
			centre.getChildren().add(vl_tmp);
			vl_tmp.actualiser(modele);
		}
	}

	@Override
	public List<Integer> getLocalisation() {
		return new ArrayList<>();
	}

	public Node getParentPrincipale() {
		return null;
	}

	public HBox getChildrenPrincipale() {
		return (HBox) ((ScrollPane) this.getChildren().get(0)).getContent();
	}
}
