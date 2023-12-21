package mvc;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import mvc.controleur.liste.Supprimer;
import mvc.modele.ModeleOllert;
import mvc.vue.page.VuePageTableau;
import ollert.Page;
import ollert.tache.donneesTache.Utilisateur;

public class 	Ollert extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		GridPane racine = new GridPane();
		ModeleOllert modele = new ModeleOllert();

		Page page = new Page("Page 1");
		modele.setDonnee(page);
		page.addListeTaches("Liste 1");
		page.addListeTaches("Liste 2");


		/* DEBUT tests membres et étiquettes */

		page.getListeTaches(0).addTache("Tache 1");
		// Crée l'utilisateur et l'ajoute à la tâche
		page.getListeTaches(0).getTache(0).ajouterUtilisateur(Utilisateur.obtenirUtilisateur("Page 1", "Augerau").getPseudo());
		page.getListeTaches(0).getTache(0).ajouterEtiquette("Maintenance");

		/* FIN tests membres et étiquettes */


		VuePageTableau vpt = new VuePageTableau(page, modele);
		modele.ajouterObservateur(vpt);
		racine.getChildren().add(vpt);
		Scene scene = new Scene(racine, 935, 670);
		stage.setScene(scene);
		stage.show();
		modele.notifierObservateurs();
	}
}
