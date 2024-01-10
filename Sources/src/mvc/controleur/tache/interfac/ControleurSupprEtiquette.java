package mvc.controleur.tache.interfac;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import mvc.modele.ModeleOllert;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe ControleurSupprEtiquette qui permet de supprimer une étiquette
 */
public class ControleurSupprEtiquette implements EventHandler<ActionEvent> {

    /**
     * Modele de l'application
     */
    private final ModeleOllert modele;
    // FIXME: A vérifier
    private Boolean modeSuppression;
    private List<Label> selected;

    /**
     * Constructeur de la classe ControleurModification
     * @param modeleOllert Modele de l'application
     */
    public ControleurSupprEtiquette(ModeleOllert modeleOllert, List<Label> l) {
        this.modele = modeleOllert;
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
        modeSuppression = !modeSuppression;
        if (!modeSuppression){
            for (Label u : selected){
                modele.getTacheEnGrand().supprimerEtiquette(u.getText());
            }
            selected = new ArrayList<>();
            modele.notifierObservateurs();
        }
        ((Button)actionEvent.getSource()).setText(modeSuppression?"Valider" : "Supprimer");
    }
}
