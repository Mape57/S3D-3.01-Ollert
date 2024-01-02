package mvc.controleur.tache;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mvc.modele.ModeleOllert;
import mvc.vue.tache.VueTache;

public class ControleurModification implements EventHandler<MouseEvent> {

    private ModeleOllert modele;

    /**
     * Constructeur de la classe ControleurModification
     */
    public ControleurModification(ModeleOllert modele) {
        this.modele = modele;
    }

    @Override
    public void handle(MouseEvent event) {
        VueTache vueTache = (VueTache) event.getSource();
        // Créer une nouvelle alerte
        VBox vbox = new VBox();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);  // Rend la Stage modale
        stage.setScene(new Scene(vbox, 300, 300));  // Ajustez la taille au besoin

        // Afficher la Stage
        stage.show();
    }
}
