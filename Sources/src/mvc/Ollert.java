package mvc;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import mvc.controleur.page.ControlleurAjouterListe;
import mvc.controleur.page.ControlleurGantt;
import mvc.controleur.page.ControlleurTableau;
import mvc.controleur.page.ControlleurTableur;
import mvc.fabrique.FabriqueVueTableur;
import mvc.modele.ModeleOllert;
import mvc.vue.page.VuePage;
import mvc.vue.page.VuePageTableau;
import mvc.vue.page.VuePageTableur;
import ollert.Page;
import ollert.tache.donneesTache.Utilisateur;

public class Ollert extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		BorderPane racine = new BorderPane();
		ModeleOllert modele = new ModeleOllert();



		// création des données provisoire de teste
		Page page = new Page("Page 1");
		modele.setDonnee(page);
		page.addListeTaches("Liste 1");
		page.getListeTaches(0).addTache("Tache 1");
		page.getListeTaches(0).addTache("Tache 2");
		page.getListeTaches(0).addTache("Tache 3");
		page.getListeTaches(0).addTache("Tache 4");
		page.addListeTaches("Liste 2");


		/* DEBUT tests membres et étiquettes */

		page.getListeTaches(0).addTache("Tache 5 avec beaucoup de texte du genre Tache 5 avec beaucoup de texte du genre Tache 5 avec beaucoup de texte du genre Tache 5 avec beaucoup de texte du genre Tache 5 avec beaucoup de texte du genre Tache 5 avec beaucoup de texte du genre Tache 5 avec beaucoup de texte du genre Tache 5 avec beaucoup de texte du genre Tache 5 avec beaucoup de texte du genre Tache 5 avec beaucoup de texte du genre Tache 5 avec beaucoup de texte du genre Tache 5 avec beaucoup de texte du genre Tache 5 avec beaucoup de texte du genre Tache 5 avec beaucoup de texte du genre Tache 5 avec beaucoup de texte du genre Tache 5 avec beaucoup de texte du genre Tache 5 avec beaucoup de texte du genre Tache 5 avec beaucoup de texte du genre Tache 5 avec beaucoup de texte du genre Tache 5 avec beaucoup de texte du genre v");
		// Crée l'utilisateur et l'ajoute à la tâche
		page.getListeTaches(0).getTache(0).ajouterUtilisateur(Utilisateur.obtenirUtilisateur("Page 1", "Augerau").getPseudo());
		page.getListeTaches(0).getTache(0).ajouterEtiquette("Maintenance");

		/* FIN tests membres et étiquettes */


		//modele.setFabrique(new FabriqueVueTableur());

		// top
		HBox header = new HBox();
		header.setStyle("-fx-border-style: solid; -fx-border-color: black; -fx-border-width: 0 0 2 0;");

			Insets buttonInsets = new Insets(10);

			Button btn_fermer = new Button("Fermer la page");
			HBox.setMargin(btn_fermer, buttonInsets);

			Button btn_gantt = new Button("Gantt");
			btn_gantt.setOnAction(new ControlleurGantt(modele));
			HBox.setMargin(btn_gantt, buttonInsets);

			Button btn_tableur = new Button("Aff Liste");
			btn_tableur.setOnAction(new ControlleurTableur(modele));
			HBox.setMargin(btn_tableur, buttonInsets);

			Button btn_tableau = new Button("Aff Tableau");
			btn_tableau.setOnAction(new ControlleurTableau(modele));
			HBox.setMargin(btn_tableau, buttonInsets);

			Button btn_ajouter = new Button("Ajouter liste");
			btn_ajouter.setOnAction(new ControlleurAjouterListe(modele));
			HBox.setMargin(btn_ajouter, buttonInsets);

			header.getChildren().addAll(btn_fermer, btn_gantt, btn_tableur, btn_tableau, btn_ajouter);

		racine.setTop(header);


		//center
		VuePage vpt = modele.getFabrique().creerVuePage(modele);
		modele.ajouterObservateur(vpt);
		racine.setCenter((Node) vpt);



		// création de la scène
		Scene scene = new Scene(racine, 1400, 800);
		stage.setScene(scene);
		stage.show();
		modele.notifierObservateurs();
	}
}
