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
public class VuePageTableur extends BorderPane implements VuePage {

	/**
	 * Constructeur de la classe VuePageTableau
	 */
	public VuePageTableur(ModeleOllert modeleControle) {

		// header de la page
		HBox header = new HBox();

			header.setStyle("-fx-border-style: solid; -fx-border-color: black; -fx-border-width: 0 0 2 0;");



			Insets buttonInsets = new Insets(10);

			Button btn_fermer = new Button("Fermer la page");
			HBox.setMargin(btn_fermer, buttonInsets);

			Button btn_gantt = new Button("Gantt");
			HBox.setMargin(btn_gantt, buttonInsets);

			Button btn_tableur = new Button("Aff Liste");
			btn_tableur.setOnAction(new ControlleurTableur(modeleControle));
			HBox.setMargin(btn_tableur, buttonInsets);

			Button btn_tableau = new Button("Aff Tableau");
			btn_tableau.setOnAction(new ControlleurTableau(modeleControle));
			HBox.setMargin(btn_tableau, buttonInsets);

			Button btn_ajouter = new Button("Ajouter liste");
			btn_ajouter.setOnAction(new ControlleurAjouterListe(modeleControle));
			HBox.setMargin(btn_ajouter, buttonInsets);

			header.getChildren().addAll(btn_fermer, btn_gantt, btn_tableur, btn_tableau, btn_ajouter);

		this.setTop(header);


		// centre de la page
		VBox centre = new VBox();
		this.setCenter(centre);
	}

	/**
	 * Actualise la vue
	 * @param sujet le modèle à partir duquel la vue est actualisée
	 */
	@Override
	public void actualiser(Sujet sujet) {

		ModeleOllert modele = (ModeleOllert) sujet;
		VBox centre = (VBox) this.getCenter();
		centre.getChildren().clear();

		Page page = (Page)modele.getDonnee();
		List<ListeTaches> liste = page.getListes();

		for (ListeTaches l : liste) {
			VueListeTableur vl_tmp = new FabriqueVueTableur().creerVueListe(modele);
			centre.getChildren().add(vl_tmp);
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
