package mvc.controleur.tacheComplete;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextInputDialog;
import mvc.ModeleOllert;

import java.util.Optional;

/**
 * Classe ControleurAjoutMembre qui permet d'ajouter un membre à une tâche
 */
public class ControleurAjoutMembre implements EventHandler<ActionEvent> {

    /**
     * Modele de l'application
     */
    private final ModeleOllert modele;

    /**
     * Constructeur de la classe ControleurModification
     * @param modeleOllert Modele de l'application
     */
    public ControleurAjoutMembre(ModeleOllert modeleOllert) {
        this.modele = modeleOllert;
    }

    /**
     * Gère l'ajout d'un membre à une tâche
     * @param actionEvent action de l'utilisateur (clic sur le bouton)
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Saisie du nom du nouveau membre");
        dialog.setHeaderText(null);
        dialog.setContentText("Nom du nouveau membre :");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(modele.getTacheEnGrand()::ajouterUtilisateur); // TROUVER UNE SOLUTION POUR AFFICHAGE DYNAMIQUE
        modele.notifierObservateurs();
    }
}
