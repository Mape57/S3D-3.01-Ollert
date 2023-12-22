package mvc;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
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

		page.getListeTaches(0).addTache("Tache 1 avec beaucoup de texte du genre :  Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc iaculis consectetur finibus. Cras aliquet nulla sed tellus faucibus consequat sit amet vel augue. Mauris felis justo, placerat id convallis vel, tristique a quam. Interdum et malesuada fames ac ante ipsum primis in faucibus. In sollicitudin tincidunt ipsum, sit amet consectetur ex egestas non. Nulla urna urna, sollicitudin et nunc nec, placerat porttitor est. Donec pulvinar cursus venenatis. Curabitur commodo nunc in libero accumsan aliquet. Vestibulum molestie ullamcorper eros, eget consequat diam dictum a. ");
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
