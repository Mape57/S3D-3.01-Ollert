package mvc.controleur.tacheComplete;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextInputDialog;
import mvc.modele.ModeleOllert;

import java.util.Optional;

/**
 * Classe ControleurAjoutEtiquette qui permet d'ajouter une étiquette à une tâche
 */
public class ControleurAjoutEtiquette implements EventHandler<ActionEvent> {

    /**
     * Modele de l'application
     */
    private final ModeleOllert modele;

    /**
     * Constructeur de la classe ControleurModification
     * @param modele Modele de l'application
     */
    public ControleurAjoutEtiquette(ModeleOllert modele) {
        this.modele = modele;
    }

    /**
     * Gère l'ajout d'une étiquette à une tâche
     * @param actionEvent action de l'utilisateur (clic sur le bouton)
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Saisie du nom de la nouvelle étiquette");
        dialog.setHeaderText(null);
        dialog.setContentText("Nom de la nouvelle étiquette :");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(modele.getTacheEnGrand()::ajouterEtiquette);
        modele.notifierObservateurs();
    }
}
