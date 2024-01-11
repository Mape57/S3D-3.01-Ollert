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
import javafx.stage.Stage;
import mvc.controleur.page.ControleurAjouterListe;
import mvc.controleur.page.ControleurGantt;
import mvc.controleur.page.ControleurTableau;
import mvc.controleur.page.ControleurTableur;
import mvc.modele.ModeleOllert;
import mvc.vue.page.VuePage;
import ollert.Page;
import ollert.tache.donneesTache.Utilisateur;

import java.time.LocalDate;

public class Ollert extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		BorderPane racine = new BorderPane();
		ModeleOllert modele = new ModeleOllert();



		// création des données provisoires de teste
		Page page = new Page("Page 1");
		modele.setDonnee(page);
		page.addListeTaches("Liste 1");
		page.getListeTaches(0).addTache("Tache 1 Création d'une BD");
		page.getListeTaches(0).addTache("Tache 2");
		page.getListeTaches(0).addTache("Tache 3");
		page.getListeTaches(0).addTache("Tache 4");
		page.getListeTaches(0).addTache("Tache 5 Analyse");
		page.getListeTaches(0).addTache("Tache 6");
		page.getListeTaches(0).addTache("Tache 7");
		page.getListeTaches(0).addTache("Tache 8");
		page.addListeTaches("Liste 2");


		page.getListeTaches(0).addTache("Tache 5 avec beaucoup de texte du genreTache 5 avec beaucoup de texte du genreTache 5 avec beaucoup de texte du genreTache 5 avec beaucoup de texte du genre");
		// Crée l'utilisateur et l'ajoute à la tâche
		page.getListeTaches(0).getTache(0).ajouterUtilisateur(Utilisateur.obtenirUtilisateur("Page 1", "Augerau").getPseudo());
		page.getListeTaches(0).getTache(0).ajouterEtiquette("Maintenance");

		// Ajout de dates à des taches
		page.getListeTaches(0).getTache(0).setDateDebut(LocalDate.of(2024,1,1));
		page.getListeTaches(0).getTache(0).setDateFin(LocalDate.of(2024,1,8));
		page.getListeTaches(0).getTache(1).setDateDebut(LocalDate.of(2024,1,10));
		page.getListeTaches(0).getTache(1).setDateFin(LocalDate.of(2024,1,16));
		page.getListeTaches(0).getTache(2).setDateDebut(LocalDate.of(2024,1,16));
		page.getListeTaches(0).getTache(2).setDateFin(LocalDate.of(2024,1,18));
		page.getListeTaches(0).getTache(3).setDateDebut(LocalDate.of(2024,1,18));
		page.getListeTaches(0).getTache(3).setDateFin(LocalDate.of(2024,1,20));
		page.getListeTaches(0).getTache(4).setDateDebut(LocalDate.of(2024,1,1));
		page.getListeTaches(0).getTache(4).setDateFin(LocalDate.of(2024,1,3));
		page.getListeTaches(0).getTache(5).setDateDebut(LocalDate.of(2024,1,1));
		page.getListeTaches(0).getTache(5).setDateFin(LocalDate.of(2024,1,9));
		page.getListeTaches(0).getTache(6).setDateDebut(LocalDate.of(2024,1,1));
		page.getListeTaches(0).getTache(6).setDateFin(LocalDate.of(2024,2,10));

		// Création d'une dépendance
		page.getListeTaches(0).getTache(0).ajouterDependance(page.getListeTaches(0).getTache(1));
		page.getListeTaches(0).getTache(1).ajouterDependance(page.getListeTaches(0).getTache(2));
		page.getListeTaches(0).getTache(2).ajouterDependance(page.getListeTaches(0).getTache(3));
		page.getListeTaches(0).getTache(4).ajouterDependance(page.getListeTaches(0).getTache(1));
		page.getListeTaches(0).getTache(5).ajouterDependance(page.getListeTaches(0).getTache(1));

		page.getListeTaches(0).getTache(0).addSousTache("Sous-tâche 1");
		page.getListeTaches(0).getTache(0).getSousTache(0).addSousTache("Sous-tâche 1.1");


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




		header.getChildren().addAll(btn_gantt, btn_tableur, btn_tableau, btn_ajouter);

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
