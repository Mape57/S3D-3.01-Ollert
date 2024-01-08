package mvc;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import mvc.fabrique.FabriqueVueTableur;
import mvc.modele.ModeleOllert;
import mvc.vue.page.VuePage;
import mvc.vue.page.VuePageTableau;
import mvc.vue.page.VuePageTableur;
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


		VuePage vpt = modele.getFabrique().creerVuePage(modele);
		modele.ajouterObservateur(vpt);
		racine.getChildren().add((Node) vpt);
		Scene scene = new Scene(racine, 1400, 800);
		stage.setScene(scene);
		stage.show();
		modele.notifierObservateurs();
	}
}
