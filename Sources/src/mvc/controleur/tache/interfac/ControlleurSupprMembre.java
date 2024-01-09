package mvc.controleur.tache.interfac;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import mvc.modele.ModeleOllert;
import mvc.vue.tache.VueTacheInterface;
import ollert.tache.TachePrincipale;

import java.util.ArrayList;
import java.util.List;

public class ControlleurSupprMembre implements EventHandler<ActionEvent> {

    private ModeleOllert modele;
    private Boolean modeSuppression;
    private List<Label> selected;

    /**
     * Constructeur de la classe ControleurModification
     */
    public ControlleurSupprMembre(ModeleOllert modele, List<Label> l) {
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
        modeSuppression = !modeSuppression;
        if (!modeSuppression){
            for (Label u : selected){
                modele.getTacheEnGrand().supprimerUtilisateur(u.getText());
            }
            selected = new ArrayList<>();
            modele.notifierObservateurs();
        }
        ((Button)actionEvent.getSource()).setText(modeSuppression?"Valider" : "Supprimer");
    }
}
