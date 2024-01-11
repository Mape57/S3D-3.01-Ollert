package mvc.vue.tache.contenu.vueinterface;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import mvc.modele.ModeleOllert;
import mvc.modele.Sujet;
import mvc.vue.Observateur;
import ollert.tache.Tache;
import ollert.tache.TachePrincipale;

public class VueDependanceInterface extends GridPane implements Observateur {


    public VueDependanceInterface(){
        Label dependances = new Label("Dépendances de la tâche");
        this.add(dependances, 0, 0);
        this.setHgap(50);
        this.setPadding(new Insets(10, 20, 20, 20));
    }


    @Override
    public void actualiser(Sujet sujet) {
        ModeleOllert modele = (ModeleOllert) sujet;
        Tache<?> tache = modele.getTacheEnGrand();
        FlowPane flowPane  = new FlowPane();
        flowPane.setPrefWrapLength(Double.MAX_VALUE);
        flowPane.setPrefWidth(1200);
        flowPane.setHgap(50);
        flowPane.setVgap(10);
        flowPane.setPadding(new Insets(10));
        GridPane.setColumnSpan(flowPane, 9);
        this.add(flowPane, 0,1);
        if (tache instanceof TachePrincipale){
            for (TachePrincipale tp : ((TachePrincipale)tache).getAntecedents()){
                flowPane.getChildren().add(new Label(tp.getTitre()));
            }
        }

    }
}
