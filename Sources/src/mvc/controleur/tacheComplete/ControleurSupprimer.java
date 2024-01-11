package mvc.controleur.tacheComplete;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import mvc.modele.ModeleOllert;

import java.util.Optional;

/**
 * Classe ControleurSupprimer qui permet de supprimer une tâche
 */
public class ControleurSupprimer implements EventHandler<ActionEvent> {

    /**
     * Modele de l'application
     */
    private final ModeleOllert modele;

    /**
     * Constructeur de la classe ControleurModification
     * @param modele Modele de l'application
     */
    public ControleurSupprimer(ModeleOllert modele) {
        this.modele = modele;
    }

    /**
     * Gère la suppression d'une tâche
     * @param actionEvent action de l'utilisateur (clic sur le bouton "supprimer")
     */
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
