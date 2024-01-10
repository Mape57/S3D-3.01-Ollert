package mvc.controleur.tache.interfac;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import mvc.modele.ModeleOllert;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe ControleurSupprMembre qui permet de supprimer un membre
 */
public class ControleurSupprMembre implements EventHandler<ActionEvent> {

    /**
     * Modele de l'application
     */
    private final ModeleOllert modele;
    // FIXME : modeSuppression est-il une validation ?
    private Boolean modeSuppression;
    private List<Label> selected;

    /**
     * Constructeur de la classe ControleurModification
     */
    public ControleurSupprMembre(ModeleOllert modele, List<Label> l) {
        this.modele = modele;
        this.modeSuppression = false;
        this.selected = new ArrayList<>();
        for (Label x : l){
            x.setOnMouseClicked(e -> {
                if (modeSuppression) {
                    if (!selected.contains(x)){
                        selected.add(x);
                    }
                    else {
                        selected.remove(x);
                    }
                }
            });
        }
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        this.modeSuppression = !this.modeSuppression;
        if (!modeSuppression){
            for (Label u : selected){
                modele.getTacheEnGrand().supprimerUtilisateur(u.getText());
            }
            selected = new ArrayList<>();
        }
        ((Button)actionEvent.getSource()).setText(modeSuppression?"Valider" : "Supprimer");
    }
}
