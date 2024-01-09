package mvc.controleur.tache.interfac;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import mvc.modele.ModeleOllert;
import ollert.tache.TachePrincipale;

import java.util.Optional;

public class ControlleurSupprimer implements EventHandler<ActionEvent> {

    private ModeleOllert modele;

    /**
     * Constructeur de la classe ControleurModification
     */
    public ControlleurSupprimer(ModeleOllert modele) {
        this.modele = modele;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de Suppression");
        alert.setHeaderText(null);
        alert.setContentText("Voulez-vous vraiment supprimer cette tâche ?");

        ButtonType buttonTypeValider = new ButtonType("Valider");
        ButtonType buttonTypeAnnuler = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeValider, buttonTypeAnnuler);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == buttonTypeValider) {
            modele.getDonnee().getListes().get(modele.getDonnee().getListes().indexOf(modele.getTacheEnGrand().trouverListeTaches())).removeTache(modele.getTacheEnGrand());

            modele.notifierObservateurs();

            Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
            stage.close();
        }
    }
}
