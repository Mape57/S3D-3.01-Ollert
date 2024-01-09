package mvc.vue.page;

import javafx.beans.binding.Bindings;
import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import mvc.controleur.liste.ControlleurDragTache;
import mvc.fabrique.FabriqueVueTableur;
import mvc.modele.ModeleOllert;
import mvc.modele.Sujet;
import mvc.vue.liste.VueListeTableur;
import ollert.Page;
import ollert.tache.ListeTaches;

import java.util.List;

/**
 * Classe de la vue représentant une page sous forme de tableau
 */
public class VuePageTableur extends VBox implements VuePage {

	/**
	 * Constructeur de la classe VuePageTableau
	 */
	public VuePageTableur(ModeleOllert modeleControle) {
		this.setStyle("-fx-background-color: #eee0cb; -fx-padding: 30; -fx-spacing: 20;");
	}

	/**
	 * Actualise la vue
	 * @param sujet le modèle à partir duquel la vue est actualisée
	 */
	@Override
	public void actualiser(Sujet sujet) {

		ModeleOllert modele = (ModeleOllert) sujet;
		this.getChildren().clear();

		Page page = (Page)modele.getDonnee();
		List<ListeTaches> liste = page.getListes();

		for (ListeTaches l : liste) {
			VueListeTableur vl_tmp = new FabriqueVueTableur().creerVueListe(modele);
			this.getChildren().add(vl_tmp);
			vl_tmp.actualiser(modele);
		}
	}

	@Override
	public List<Integer> getLocalisation() {
		return null;
	}

	public Node getParentPrincipale() {
		return null;
	}

	public Node getChildrenPrincipale() {
		return null;
	}
}
