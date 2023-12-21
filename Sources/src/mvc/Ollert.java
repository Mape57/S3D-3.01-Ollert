package mvc;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import mvc.modele.ModeleOllert;
import mvc.vue.page.VuePageTableau;
import ollert.Page;

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
		page.getListeTaches(0).addTache("Tache 1");
		page.getListeTaches(0).addTache("Tache 2");
		page.getListeTaches(0).addTache("Tache 3");
		page.addListeTaches("Liste 2");
		page.getListeTaches(1).addTache("Tache 1");
		page.getListeTaches(1).addTache("Tache 2");
		page.getListeTaches(1).addTache("Tache 3");
		page.getListeTaches(1).addTache("Tache 4");
		page.getListeTaches(1).addTache("Tache 5");

		VuePageTableau vpt = new VuePageTableau(page);
		modele.ajouterObservateur(vpt);
		racine.getChildren().add(vpt);
		Scene scene = new Scene(racine, 935, 670);
		stage.setScene(scene);
		stage.show();
		modele.notifierObservateurs();
	}
}
