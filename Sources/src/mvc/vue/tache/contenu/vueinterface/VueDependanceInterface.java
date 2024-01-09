package mvc.vue.tache.contenu.vueinterface;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import mvc.modele.ModeleOllert;
import mvc.modele.Sujet;
import mvc.vue.Observateur;
import ollert.tache.TachePrincipale;

import java.util.ArrayList;
import java.util.List;

public class VueDependanceInterface extends GridPane implements Observateur {


    public VueDependanceInterface(){
        Label dependances = new Label("Dépendances de la tâche");
        this.add(dependances, 0, 0);
    }


    @Override
    public void actualiser(Sujet sujet) {
        ModeleOllert modele = (ModeleOllert) sujet;
        TachePrincipale tache = (TachePrincipale) modele.getTacheEnGrand();
        int x = 0;
        int y = 1;
        for (TachePrincipale tp : tache.getAntecedents()){
            Label label = new Label(tp.getTitre());
            this.add(label, x, y);
            x++;
        }
    }
}
