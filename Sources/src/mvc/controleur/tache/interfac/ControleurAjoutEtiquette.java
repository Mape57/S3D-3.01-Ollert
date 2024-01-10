package mvc.controleur.tache.interfac;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextInputDialog;
import mvc.modele.ModeleOllert;

import java.util.Optional;

public class ControleurAjoutEtiquette implements EventHandler<ActionEvent> {

    private final ModeleOllert modele;

    /**
     * Constructeur de la classe ControleurModification
     */
    public ControleurAjoutEtiquette(ModeleOllert modele) {
        this.modele = modele;
    }

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
