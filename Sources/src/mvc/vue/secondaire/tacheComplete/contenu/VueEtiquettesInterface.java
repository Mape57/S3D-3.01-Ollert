package mvc.vue.secondaire.tacheComplete.contenu;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import mvc.vue.structure.Observateur;
import mvc.modele.Sujet;
import mvc.controleur.tacheComplete.ControleurAjoutEtiquette;
import mvc.controleur.tacheComplete.ControleurSupprEtiquette;
import mvc.modele.ModeleOllert;
import ollert.donnee.tache.TacheAbstraite;
import ollert.donnee.tache.attribut.Etiquette;

import java.util.ArrayList;
import java.util.List;

public class VueEtiquettesInterface extends GridPane implements Observateur {

    public VueEtiquettesInterface(){
        Label etiquettes = new Label("Etiquettes de la tâche");
        Button supprTag = new Button("Supprimer");
        Button ajoutTag = new Button("Ajouter");
        this.add(etiquettes, 0, 0);
        this.add(ajoutTag, 9, 0);
        this.add(supprTag, 10, 0);
        this.setHgap(50);
        this.setPadding(new Insets(10, 20, 20, 20));
    }

    @Override
    public void actualiser(Sujet sujet) {
        ModeleOllert modele = (ModeleOllert) sujet;
        TacheAbstraite<?> tache = modele.getTacheComplete();
        FlowPane flowPane  = new FlowPane();
        flowPane.setPrefWrapLength(Double.MAX_VALUE);
        flowPane.setPrefWidth(1200);
        flowPane.setHgap(50);
        flowPane.setVgap(10);
        flowPane.setPadding(new Insets(10));
        GridPane.setColumnSpan(flowPane, 9);
        this.add(flowPane, 0,1);
        List<Label> list = new ArrayList<>();
        for (Etiquette u : tache.getTags()) {
            Label label = new Label(u.getValeur());
            label.setFont(new Font("Arial", 12));
            label.setPadding(new Insets(3,3,3,3));
            label.setStyle("-fx-background-color: purple;");
            label.autosize();
            flowPane.getChildren().add(label);
            list.add(label);
        }
        ((Button)this.getChildren().get(1)).setOnAction(new ControleurAjoutEtiquette(modele));
        ((Button)this.getChildren().get(2)).setOnAction(new ControleurSupprEtiquette(modele, list));
    }
}
