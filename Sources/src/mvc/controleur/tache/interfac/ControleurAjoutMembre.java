package mvc.controleur.tache.interfac;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextInputDialog;
import mvc.modele.ModeleOllert;

import java.util.Optional;

public class ControleurAjoutMembre implements EventHandler<ActionEvent> {

    private final ModeleOllert modele;

    /**
     * Constructeur de la classe ControleurModification
     */
    public ControleurAjoutMembre(ModeleOllert modeleOllert) {
        this.modele = modeleOllert;
    }

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
