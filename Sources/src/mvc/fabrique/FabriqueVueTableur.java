package mvc.fabrique;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import mvc.controleur.liste.ControlleurAjouterTache;
import mvc.controleur.liste.ControlleurModifierTitre;
import mvc.controleur.liste.ControlleurSupprimerTache;
import mvc.controleur.page.ControlleurGantt;
import mvc.controleur.tache.ControlleurModification;
import mvc.modele.ModeleOllert;
import mvc.vue.liste.VueListeTableur;
import mvc.vue.page.VuePageTableur;
import mvc.vue.sousTache.VueSousTache;
import mvc.vue.tache.VueTacheTableur;

/**
 * Implementation de la FabriqueVue pour un affichage tableur
 * : affichage classique (colonnes)
 */
public class FabriqueVueTableur implements FabriqueVue {

	private ModeleOllert modeleOllert;

	public FabriqueVueTableur(ModeleOllert modeleOllert) {
		this.modeleOllert = modeleOllert;
	}

	/**
	 * Crée la vue d'une tache sous forme tableur
	 * @return Vue de la tache
	 */
	@Override
	public VueTacheTableur creerVueTache() {
		VueTacheTableur vueTacheTableur = new VueTacheTableur();
		vueTacheTableur.setOnMouseClicked(new ControlleurModification(this.modeleOllert));

		HBox tache = new HBox();
		HBox titre = new HBox();

		titre.getChildren().addAll(creerLabel("1", 280, 200));
		tache.getChildren().add(titre);

		for (int i = 2; i <= 6; i++) {
			tache.getChildren().add(creerLabel(Integer.toString(i), 200, 200));
		}

		vueTacheTableur.getChildren().add(tache);

		return vueTacheTableur;
	}

	private Label creerLabel(String texte, int largeurMin, int largeurMax) {
		Label label = new Label(texte);
		label.setMinWidth(largeurMin);
		label.setMaxWidth(largeurMax);
		label.setStyle("-fx-border-style: solid; -fx-border-color: black; -fx-border-width: 1 1 1 1; -fx-padding: 10;");
		return label;
	}

	/**
	 * Crée la vue d'une liste sous forme tableur
	 * @return Vue de la liste
	 */
	@Override
	public VueListeTableur creerVueListe() {
		VueListeTableur vueListeTableur = new VueListeTableur();

		// Création du bandeau
		HBox bandeau = new HBox();
		bandeau.setStyle("-fx-background-color: #a0a0a0;");

		HBox titre = new HBox();
		titre.setPrefWidth(280);
		titre.setPrefHeight(50);
		titre.setStyle("-fx-border-style: solid; -fx-border-color: black; -fx-border-width: 1 1 1 1; -fx-padding: 10;");
		Label l1 = new Label("Nom de la liste");
		l1.setStyle("-fx-padding: 0 0 0 0;");


		Button btn_modif = new Button();
		btn_modif.setStyle("-fx-background-color: transparent;");
		ImageView view = new ImageView(new Image("file:Sources/ressource/images/icones/crayon-blanc.png"));
		view.setFitHeight(20);
		view.setPreserveRatio(true);
		btn_modif.setGraphic(view);
		btn_modif.setOnAction(new ControlleurGantt(this.modeleOllert));

		btn_modif.setOnMouseEntered(event -> {
			ImageView v = new ImageView(new Image("file:Sources/ressource/images/icones/crayon-noir.png"));
			v.setFitHeight(20);
			v.setPreserveRatio(true);
			btn_modif.setGraphic(v);
		});
		btn_modif.setOnMouseExited(event -> {
			ImageView v = new ImageView(new Image("file:Sources/ressource/images/icones/crayon-blanc.png"));
			v.setFitHeight(20);
			v.setPreserveRatio(true);
			btn_modif.setGraphic(v);
		});
		btn_modif.setOnAction(new ControlleurModifierTitre(this.modeleOllert));



		Button btn_ajout = new Button();
		btn_ajout.setStyle("-fx-background-color: transparent;");
		view = new ImageView(new Image("file:Sources/ressource/images/icones/ajouter-blanc.png"));
		view.setFitHeight(20);
		view.setPreserveRatio(true);
		btn_ajout.setGraphic(view);
		btn_ajout.setOnAction(new ControlleurGantt(this.modeleOllert));

		btn_ajout.setOnMouseEntered(event -> {
			ImageView v = new ImageView(new Image("file:Sources/ressource/images/icones/ajouter-noir.png"));
			v.setFitHeight(20);
			v.setPreserveRatio(true);
			btn_ajout.setGraphic(v);
		});
		btn_ajout.setOnMouseExited(event -> {
			ImageView v = new ImageView(new Image("file:Sources/ressource/images/icones/ajouter-blanc.png"));
			v.setFitHeight(20);
			v.setPreserveRatio(true);
			btn_ajout.setGraphic(v);
		});
		btn_ajout.setOnAction(new ControlleurAjouterTache(this.modeleOllert));


		Button btn_supp = new Button();
		btn_supp.setStyle("-fx-background-color: transparent;");
		view = new ImageView(new Image("file:Sources/ressource/images/icones/delete-blanc.png"));
		view.setFitHeight(20);
		view.setPreserveRatio(true);
		btn_supp.setGraphic(view);
		btn_supp.setOnAction(new ControlleurGantt(this.modeleOllert));

		btn_supp.setOnMouseEntered(event -> {
			ImageView v = new ImageView(new Image("file:Sources/ressource/images/icones/delete-noir.png"));
			v.setFitHeight(20);
			v.setPreserveRatio(true);
			btn_supp.setGraphic(v);
		});
		btn_supp.setOnMouseExited(event -> {
			ImageView v = new ImageView(new Image("file:Sources/ressource/images/icones/delete-blanc.png"));
			v.setFitHeight(20);
			v.setPreserveRatio(true);
			btn_supp.setGraphic(v);
		});
		btn_supp.setOnAction(new ControlleurSupprimerTache(this.modeleOllert, vueListeTableur));


		Button btn_archiv = new Button();
		btn_archiv.setStyle("-fx-background-color: transparent;");
		view = new ImageView(new Image("file:Sources/ressource/images/icones/archive-blanc.png"));
		view.setFitHeight(20);
		view.setPreserveRatio(true);
		btn_archiv.setGraphic(view);
		btn_archiv.setOnAction(new ControlleurGantt(this.modeleOllert));

		btn_archiv.setOnMouseEntered(event -> {
			ImageView v = new ImageView(new Image("file:Sources/ressource/images/icones/archive-noir.png"));
			v.setFitHeight(20);
			v.setPreserveRatio(true);
			btn_archiv.setGraphic(v);
		});
		btn_archiv.setOnMouseExited(event -> {
			ImageView v = new ImageView(new Image("file:Sources/ressource/images/icones/archive-blanc.png"));
			v.setFitHeight(20);
			v.setPreserveRatio(true);
			btn_archiv.setGraphic(v);
		});


		titre.getChildren().addAll(l1, btn_modif, btn_ajout, btn_supp, btn_archiv);

		Label l2 = new Label("Debut");
		l2.setPrefWidth(200);
		l2.setPrefHeight(50);
		l2.setStyle("-fx-border-style: solid; -fx-border-color: black; -fx-border-width: 1 1 1 1; -fx-padding: 10;");

		Label l3 = new Label("Echéance");
		l3.setPrefWidth(200);
		l3.setPrefHeight(50);
		l3.setStyle("-fx-border-style: solid; -fx-border-color: black; -fx-border-width: 1 1 1 1; -fx-padding: 10;");


		Label l4 = new Label("Membres");
		l4.setPrefWidth(200);
		l4.setPrefHeight(50);
		l4.setStyle("-fx-border-style: solid; -fx-border-color: black; -fx-border-width: 1 1 1 1; -fx-padding: 10;");


		Label l5 = new Label("Etiquettes");
		l5.setPrefWidth(200);
		l5.setPrefHeight(50);
		l5.setStyle("-fx-border-style: solid; -fx-border-color: black; -fx-border-width: 1 1 1 1; -fx-padding: 10;");


		Label l6 = new Label("Priorité");
		l6.setPrefWidth(200);
		l6.setPrefHeight(50);
		l6.setStyle("-fx-border-style: solid; -fx-border-color: black; -fx-border-width: 1 1 1 1; -fx-padding: 10;");


		bandeau.getChildren().addAll(titre, l2, l3, l4, l5, l6);
		vueListeTableur.getChildren().add(bandeau);

		// Création du contenu
		VBox centre = new VBox();
		centre.setStyle("-fx-background-color: #e9e9e9");
		vueListeTableur.getChildren().add(centre);

		return vueListeTableur;
	}

	/**
	 * Crée la vue d'une page sous forme tableur
	 * @return Vue de la page
	 */
	@Override
	public VuePageTableur creerVuePage() {
		return new VuePageTableur();
	}

	@Override
	public VueSousTache creerVueSousTache() {
		return null;
	}
}
