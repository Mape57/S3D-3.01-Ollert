package mvc.controleur.tache.interfac;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import mvc.modele.ModeleOllert;
import ollert.tache.donneesTache.Utilisateur;

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
    private List<HBox> selected;

    /**
     * Constructeur de la classe ControleurModification
     */
    public ControleurSupprMembre(ModeleOllert modele, List<HBox> l) {
        this.modele = modele;
        this.modeSuppression = false;
        this.selected = new ArrayList<>();
        for (HBox x : l){
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
            for (HBox h : selected){
                String rec = "";
                for (Utilisateur u : modele.getTacheEnGrand().getMembres()){
                    if (u.getPseudo().contains(((Label)h.getChildren().get(1)).getText())){
                        rec = u.getPseudo();
                    }
                }
                modele.getTacheEnGrand().supprimerUtilisateur(rec);
            }
            selected = new ArrayList<>();
        }
        ((Button)actionEvent.getSource()).setText(modeSuppression?"Valider" : "Supprimer");
    }
}
