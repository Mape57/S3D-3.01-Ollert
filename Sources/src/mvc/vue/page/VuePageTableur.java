package mvc.vue.page;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import mvc.controleur.page.ControlleurAjouterListe;
import mvc.controleur.page.ControlleurTableau;
import mvc.controleur.page.ControlleurTableur;
import mvc.fabrique.FabriqueVueTableau;
import mvc.fabrique.FabriqueVueTableur;
import mvc.modele.ModeleOllert;
import mvc.modele.Sujet;
import mvc.vue.liste.VueListeTableau;
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

	/**
	 * @return page réelle que représente la vue
	 */
	public Page getPage() {
		return null;
	}

	@Override
	public List<Integer> getLocalisation() {
		return null;
	}
}
