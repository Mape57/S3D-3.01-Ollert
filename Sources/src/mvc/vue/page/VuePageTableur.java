package mvc.vue.page;

import javafx.collections.ObservableList;
import javafx.scene.control.ScrollPane;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import mvc.fabrique.FabriqueVueTableur;
import mvc.modele.ModeleOllert;
import mvc.modele.Sujet;
import mvc.vue.liste.VueListeTableur;
import ollert.Page;
import ollert.Sauvegarde;
import ollert.tache.ListeTaches;

import java.util.List;

/**
 * Classe de la vue représentant une page sous forme de tableau
 */
public class VuePageTableur extends ScrollPane implements VuePage {

	/**
	 * Constructeur de la classe VuePageTableau
	 */
	public VuePageTableur() {
		this.setStyle("-fx-background-color: #eee0cb; -fx-padding: 30; -fx-spacing: 20;");

		VBox vb = new VBox();
		this.setContent(vb);
	}

	/**
	 * Actualise la vue
	 * @param sujet le modèle à partir duquel la vue est actualisée
	 */
	@Override
	public void actualiser(Sujet sujet) {

		VBox vb = (VBox)this.getContent();

		ModeleOllert modele = (ModeleOllert) sujet;
		vb.getChildren().clear();

		Page page = modele.getDonnee();
		List<ListeTaches> liste = page.getListes();

		for (ListeTaches l : liste) {
			VueListeTableur vl_tmp = new FabriqueVueTableur(modele).creerVueListe();
			vb.getChildren().add(vl_tmp);
			vl_tmp.actualiser(modele);
		}
		if (page.getTitre() != "defaut"){
			Sauvegarde.sauvegarderPage(page);
		}
	}

	@Override
	public List<Integer> getLocalisation() {
		return null;
	}

	public Node getParentPrincipale() {
		return null;
	}

	@Override
	public ObservableList<Node> getChildren() {
		return super.getChildren();
	}

	public Node getChildrenPrincipale() {
		return null;
	}
}
