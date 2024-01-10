package mvc.vue.tache.contenu.vueinterface;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import mvc.controleur.tache.interfac.ControlleurPriorite;
import mvc.modele.ModeleOllert;
import mvc.modele.Sujet;
import mvc.vue.Observateur;
import mvc.vue.tache.VueTache;
import mvc.vue.tache.VueTacheInterface;
import ollert.tache.Tache;

import static ollert.tache.donneesTache.Priorite.*;

public class VuePrioriteInterface extends HBox implements Observateur {

    public VuePrioriteInterface(){
        Button faible = new Button("Faible");
        Button moyenne = new Button("Moyenne");
        Button elevee = new Button("Elevée");
        faible.setStyle("-fx-background-color: lightgray;");
        moyenne.setStyle("-fx-background-color: lightgray;");
        elevee.setStyle("-fx-background-color: lightgray;");
        this.getChildren().addAll(faible, moyenne, elevee);
        this.setPadding(new Insets(20, 50, 20, 50));
    }

    @Override
    public void actualiser(Sujet sujet) {
        ModeleOllert modele = (ModeleOllert) sujet;
        Tache<?> tache = modele.getTacheEnGrand();
        switch(tache.getPriorite()) {
            case FAIBLE:
                this.getChildren().get(0).setDisable(true);
                this.getChildren().get(1).setDisable(false);
                this.getChildren().get(2).setDisable(false);
                this.getChildren().get(0).setStyle("-fx-background-color: yellow;");
                this.getChildren().get(1).setStyle("-fx-background-color: lightgray;");
                this.getChildren().get(2).setStyle("-fx-background-color: lightgray;");
                break;
            case MOYENNE:
                this.getChildren().get(0).setDisable(false);
                this.getChildren().get(1).setDisable(true);
                this.getChildren().get(2).setDisable(false);
                this.getChildren().get(0).setStyle("-fx-background-color: lightgray;");
                this.getChildren().get(1).setStyle("-fx-background-color: orange;");
                this.getChildren().get(2).setStyle("-fx-background-color: lightgray;");
                break;
            case ELEVEE:
                this.getChildren().get(0).setDisable(false);
                this.getChildren().get(1).setDisable(false);
                this.getChildren().get(2).setDisable(true);
                this.getChildren().get(0).setStyle("-fx-background-color: lightgray;");
                this.getChildren().get(1).setStyle("-fx-background-color: lightgray;");
                this.getChildren().get(2).setStyle("-fx-background-color: red;");
                break;
            case INDEFINI:
                this.getChildren().get(0).setDisable(false);
                this.getChildren().get(1).setDisable(false);
                this.getChildren().get(2).setDisable(false);
                this.getChildren().get(2).setStyle("-fx-background-color: lightgray;");
                this.getChildren().get(0).setStyle("-fx-background-color: lightgray;");
                this.getChildren().get(1).setStyle("-fx-background-color: lightgray;");
                break;
        }
        ((Button)this.getChildren().get(0)).setOnAction(new ControlleurPriorite(modele));
        ((Button)this.getChildren().get(1)).setOnAction(new ControlleurPriorite(modele));
        ((Button)this.getChildren().get(2)).setOnAction(new ControlleurPriorite(modele));
    }
}
