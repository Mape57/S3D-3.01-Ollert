package mvc.controleur.tache.interfac;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
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
    private List<HBox> list;

    /**
     * Constructeur de la classe ControleurModification
     */
    public ControleurSupprMembre(ModeleOllert modele, List<HBox> l) {
        this.modele = modele;
        this.modeSuppression = false;
        this.list = l;
        this.selected = new ArrayList<>();
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        this.modeSuppression = !this.modeSuppression;

        if (!modeSuppression){
            for (HBox h : list){
                h.getChildren().remove(2);
            }
            for (HBox h2 : selected){
                String rec = "";
                for (Utilisateur u : modele.getTacheEnGrand().getMembres()){
                    if (u.getPseudo().contains(((Label)h2.getChildren().get(1)).getText())){
                        rec = u.getPseudo();
                    }
                }
                modele.getTacheEnGrand().supprimerUtilisateur(rec);
                h2.setDisable(true);
                h2.setVisible(false);
            }
            selected = new ArrayList<>();
        }
        else {
            for (HBox h : list){
                Circle circle = new Circle(10);
                circle.setFill(Color.GREEN);
                Rectangle bar = new Rectangle(8, 2);
                bar.setFill(Color.WHITE);
                StackPane stackPane = new StackPane();
                stackPane.getChildren().addAll(circle, bar);
                stackPane.setOnMouseClicked(e -> {
                    for (HBox x : this.list) {
                        if (modeSuppression) {
                            if (!selected.contains(x)) {
                                ((Circle)stackPane.getChildren().get(0)).setFill(Color.RED);
                                selected.add(x);
                            } else {
                                ((Circle)stackPane.getChildren().get(0)).setFill(Color.GREEN);
                                selected.remove(x);
                            }
                        }
                    }
                });
                h.getChildren().add(stackPane);
            }
        }
        ((Button)actionEvent.getSource()).setText(modeSuppression?"Valider" : "Supprimer");
    }
}
