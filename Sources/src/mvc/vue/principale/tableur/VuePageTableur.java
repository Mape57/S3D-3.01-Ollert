package mvc.vue.principale.tableur;

import mvc.vue.structure.VuePage;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import fabrique.FabriqueVueTableur;
import mvc.modele.Sujet;
import mvc.modele.ModeleOllert;
import ollert.donnee.Page;
import ollert.tool.ParentScrollPane;
import ollert.tool.Sauvegarde;
import ollert.donnee.ListeTaches;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe de la vue représentant une page sous forme de tableau
 */
public class VuePageTableur extends ParentScrollPane implements VuePage {

	/**
	 * Constructeur de la classe VuePageTableau
	 */
	public VuePageTableur() {
		this.setStyle("-fx-background-color: #eee0cb; -fx-padding: 30; -fx-spacing: 20;");

		VBox vb = new VBox();
		this.setContentAndChildrenProp(vb);
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
		return new ArrayList<>();
	}

	public Node getParentPrincipale() {
		return null;
	}

	@Override
	public ObservableList<Node> getChildren() {
		return super.getChildren();
	}

	public Node getChildrenPrincipale() {
		return super.getChildren().get(0);
	}
}
