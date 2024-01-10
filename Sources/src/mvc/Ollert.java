package mvc;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import mvc.controleur.page.ControleurAjouterListe;
import mvc.controleur.page.ControleurGantt;
import mvc.controleur.page.ControleurTableau;
import mvc.controleur.page.ControleurTableur;
import mvc.controleur.page.*;
import mvc.modele.ModeleOllert;
import mvc.vue.page.VuePage;
import ollert.Page;
import ollert.Sauvegarde;
import ollert.tache.ListeTaches;
import ollert.tache.Tache;
import ollert.tache.TachePrincipale;

public class Ollert extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		BorderPane racine = new BorderPane();
		ModeleOllert modele = new ModeleOllert();

		Page page = Sauvegarde.chargerPage("Ollert1.ol");
		modele.setDonnee(page);

		// top
		HBox header = new HBox();
		header.setStyle(
				"-fx-background-color: #c2847a;" +
				"-fx-border-style: solid; -fx-border-color: black; -fx-border-width: 0 0 2 0;" +
				"-fx-padding: 20;" +
				"-fx-spacing: 20;"
		);

			Insets buttonInsets = new Insets(10);

			Button btn_gantt = new Button();
			ImageView view = new ImageView(new Image("file:Sources/ressource/images/icones/gantt-blanc.png"));
			view.setFitHeight(30);
			view.setPreserveRatio(true);
			btn_gantt.setGraphic(view);
			btn_gantt.setOnAction(new ControleurGantt(modele));

			btn_gantt.setOnMouseEntered(event -> {
				ImageView v = new ImageView(new Image("file:Sources/ressource/images/icones/gantt-noir.png"));
				v.setFitHeight(30);
				v.setPreserveRatio(true);
				btn_gantt.setGraphic(v);
			});
			btn_gantt.setOnMouseExited(event -> {
				ImageView v = new ImageView(new Image("file:Sources/ressource/images/icones/gantt-blanc.png"));
				v.setFitHeight(30);
				v.setPreserveRatio(true);
				btn_gantt.setGraphic(v);
			});




			// affichage en mode liste
			Button btn_tableur = new Button();
			view = new ImageView(new Image("file:Sources/ressource/images/icones/liste-blanc.png"));
			view.setFitHeight(30);
			view.setPreserveRatio(true);
			btn_tableur.setGraphic(view);
			btn_tableur.setOnAction(new ControleurTableur(modele));

			btn_tableur.setOnMouseEntered(event -> {
				ImageView v = new ImageView(new Image("file:Sources/ressource/images/icones/liste-noir.png"));
				v.setFitHeight(30);
				v.setPreserveRatio(true);
				btn_tableur.setGraphic(v);
			});
			btn_tableur.setOnMouseExited(event -> {
				ImageView v = new ImageView(new Image("file:Sources/ressource/images/icones/liste-blanc.png"));
				v.setFitHeight(30);
				v.setPreserveRatio(true);
				btn_tableur.setGraphic(v);
			});


			// affichage en mode tableau
			Button btn_tableau = new Button();
			view = new ImageView(new Image("file:Sources/ressource/images/icones/tableau-blanc.png"));
			view.setFitHeight(30);
			view.setPreserveRatio(true);
			btn_tableau.setGraphic(view);
			btn_tableau.setOnAction(new ControleurTableau(modele));

			btn_tableau.setOnMouseEntered(event -> {
				ImageView v = new ImageView(new Image("file:Sources/ressource/images/icones/tableau-noir.png"));
				v.setFitHeight(30);
				v.setPreserveRatio(true);
				btn_tableau.setGraphic(v);
			});
			btn_tableau.setOnMouseExited(event -> {
				ImageView v = new ImageView(new Image("file:Sources/ressource/images/icones/tableau-blanc.png"));
				v.setFitHeight(30);
				v.setPreserveRatio(true);
				btn_tableau.setGraphic(v);
			});



			// button ajouter liste tache
			Button btn_ajouter = new Button();
			view = new ImageView(new Image("file:Sources/ressource/images/icones/ajouter-blanc.png"));
			view.setFitHeight(30);
			view.setPreserveRatio(true);
			btn_ajouter.setGraphic(view);

			btn_ajouter.setOnMouseEntered(event -> {
				ImageView v = new ImageView(new Image("file:Sources/ressource/images/icones/ajouter-noir.png"));
				v.setFitHeight(30);
				v.setPreserveRatio(true);
				btn_ajouter.setGraphic(v);
			});
			btn_ajouter.setOnMouseExited(event -> {
				ImageView v = new ImageView(new Image("file:Sources/ressource/images/icones/ajouter-blanc.png"));
				v.setFitHeight(30);
				v.setPreserveRatio(true);
				btn_ajouter.setGraphic(v);
			});
			btn_ajouter.setOnAction(new ControleurAjouterListe(modele));

			// button creer nouvelle page
			Button btn_new = new Button();
			view = new ImageView(new Image("file:Sources/ressource/images/icones/page-blanc.png"));
			view.setFitHeight(30);
			view.setPreserveRatio(true);
			btn_new.setGraphic(view);

			btn_new.setOnMouseEntered(event -> {
				ImageView v = new ImageView(new Image("file:Sources/ressource/images/icones/page-noir.png"));
				v.setFitHeight(30);
				v.setPreserveRatio(true);
				btn_new.setGraphic(v);
			});
			btn_new.setOnMouseExited(event -> {
				ImageView v = new ImageView(new Image("file:Sources/ressource/images/icones/page-blanc.png"));
				v.setFitHeight(30);
				v.setPreserveRatio(true);
				btn_new.setGraphic(v);
			});
			btn_new.setOnAction(new ControleurNew(modele, stage));


			// button charger fichier
			Button btn_charger = new Button();
			view = new ImageView(new Image("file:Sources/ressource/images/icones/dossier-blanc.png"));
			view.setFitHeight(30);
			view.setPreserveRatio(true);
			btn_charger.setGraphic(view);

			btn_charger.setOnMouseEntered(event -> {
				ImageView v = new ImageView(new Image("file:Sources/ressource/images/icones/dossier-noir.png"));
				v.setFitHeight(30);
				v.setPreserveRatio(true);
				btn_charger.setGraphic(v);
			});
			btn_charger.setOnMouseExited(event -> {
				ImageView v = new ImageView(new Image("file:Sources/ressource/images/icones/dossier-blanc.png"));
				v.setFitHeight(30);
				v.setPreserveRatio(true);
				btn_charger.setGraphic(v);
			});
			btn_charger.setOnAction(new ControleurCharger(modele, stage));

			Rectangle r = new Rectangle(2, 40);


			header.getChildren().addAll(btn_new, btn_charger, r, btn_gantt, btn_tableur, btn_tableau, btn_ajouter);

		racine.setTop(header);


		//center
		VuePage vpt = modele.getFabrique().creerVuePage();
		modele.ajouterObservateur(vpt);
		racine.setCenter((Node) vpt);



		// création de la scène
		Scene scene = new Scene(racine, 1400, 800);
		stage.setScene(scene);
		stage.show();
		modele.notifierObservateurs();
	}
}
