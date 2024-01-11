package mvc.vue.secondaire.tacheComplete.contenu;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import mvc.vue.structure.Observateur;
import mvc.vue.structure.Sujet;
import mvc.ModeleOllert;
import ollert.donnee.tache.SousTache;
import ollert.donnee.tache.TacheAbstraite;

public class VueSousTacheInterface extends GridPane implements Observateur {

    public VueSousTacheInterface(){
        Label dependances = new Label("Sous-tâches de la tâche");
        this.add(dependances, 0, 0);
        this.setHgap(50);
        this.setPadding(new Insets(10, 20, 20, 20));
    }

    @Override
    public void actualiser(Sujet sujet) {
        ModeleOllert modele = (ModeleOllert) sujet;
        TacheAbstraite<?> tache = modele.getTacheEnGrand();
        FlowPane flowPane  = new FlowPane();
        flowPane.setPrefWrapLength(Double.MAX_VALUE);
        flowPane.setPrefWidth(1200);
        flowPane.setHgap(50);
        flowPane.setVgap(10);
        flowPane.setPadding(new Insets(10));
        GridPane.setColumnSpan(flowPane, 9);
        this.add(flowPane, 0,1);
        for (SousTache st : tache.getSousTaches()){
            flowPane.getChildren().add(new Label(st.getTitre()));
        }
    }
}
