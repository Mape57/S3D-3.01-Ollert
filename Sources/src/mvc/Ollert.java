package mvc;

import javafx.application.Application;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mvc.vue.liste.VueListeTableau;
import mvc.vue.page.VuePageTableau;
import ollert.ListeTaches;
import ollert.Page;

public class Ollert extends Application {
    public static void main(String[] args) {launch(args);}

    @Override
    public void start(Stage stage) {
        GridPane racine = new GridPane();
        ModeleOllert modele = new ModeleOllert();
        Page page = new Page("Page 1");
        modele.setDonnee(page);
		page.creerListeTaches("Liste 1");
		page.obtenirListe(0).creerTache("Tache 1");
		page.obtenirListe(0).creerTache("Tache 2");
		page.obtenirListe(0).creerTache("Tache 3");
		page.creerListeTaches("Liste 2");
		page.obtenirListe(1).creerTache("Tache 1");
		page.obtenirListe(1).creerTache("Tache 2");
		page.obtenirListe(1).creerTache("Tache 3");
		page.obtenirListe(1).creerTache("Tache 4");
		page.obtenirListe(1).creerTache("Tache 5");

        VuePageTableau vpt = new VuePageTableau(page);
		modele.ajouterObservateur(vpt);
        racine.getChildren().add(vpt);
        Scene scene = new Scene(racine,935,670);
        stage.setScene(scene);
        stage.show();
		modele.notifierObservateurs();
		modele.notifierObservateurs();
    }
}
