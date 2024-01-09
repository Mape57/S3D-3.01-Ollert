package mvc.controleur.tache.interfac;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import mvc.modele.ModeleOllert;
import mvc.vue.tache.VueTacheInterface;
import ollert.tache.TachePrincipale;

import java.util.Optional;

public class ControlleurAjoutMembre implements EventHandler<ActionEvent> {

    private ModeleOllert modele;

    /**
     * Constructeur de la classe ControleurModification
     */
    public ControlleurAjoutMembre(ModeleOllert modeleOllert) {
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
