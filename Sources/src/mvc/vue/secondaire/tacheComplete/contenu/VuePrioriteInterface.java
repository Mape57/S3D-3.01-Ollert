package mvc.vue.secondaire.tacheComplete.contenu;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import mvc.controleur.tacheComplete.ControleurPriorite;
import mvc.modele.ModeleOllert;
import mvc.modele.Sujet;
import mvc.vue.structure.Observateur;
import ollert.donnee.tache.Tache;

public class VuePrioriteInterface extends HBox implements Observateur {

    public VuePrioriteInterface(){
        Button faible = new Button("Faible");
        Button moyenne = new Button("Moyenne");
        Button elevee = new Button("Elevée");
        faible.setStyle("-fx-background-color: lightgray;");
        moyenne.setStyle("-fx-background-color: lightgray;");
        elevee.setStyle("-fx-background-color: lightgray;");
        this.getChildren().addAll(faible, moyenne, elevee);
        this.setSpacing(300);
        this.setAlignment(Pos.CENTER);
    }

    @Override
    public void actualiser(Sujet sujet) {
        ModeleOllert modele = (ModeleOllert) sujet;
        Tache<?> tache = modele.getTacheComplete();
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
        ((Button)this.getChildren().get(0)).setOnAction(new ControleurPriorite(modele));
        ((Button)this.getChildren().get(1)).setOnAction(new ControleurPriorite(modele));
        ((Button)this.getChildren().get(2)).setOnAction(new ControleurPriorite(modele));
    }
}
