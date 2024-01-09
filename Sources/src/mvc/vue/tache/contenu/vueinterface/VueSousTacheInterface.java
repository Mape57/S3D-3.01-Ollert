package mvc.vue.tache.contenu.vueinterface;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import mvc.modele.ModeleOllert;
import mvc.modele.Sujet;
import mvc.vue.Observateur;
import mvc.vue.tache.VueTacheTableau;
import mvc.vue.tache.VueTacheTableauInterface;
import ollert.tache.SousTache;
import ollert.tache.TachePrincipale;

public class VueSousTacheInterface extends GridPane implements Observateur {

    public VueSousTacheInterface(){
        Label dependances = new Label("Sous-tâches de la tâche");
        this.add(dependances, 0, 0);
    }

    @Override
    public void actualiser(Sujet sujet) {
        ModeleOllert modele = (ModeleOllert) sujet;
        TachePrincipale tache = (TachePrincipale) modele.getTacheEnGrand();
        int x = 0;
        int y = 1;
        for (SousTache st : tache.getSousTaches()){
            VueTacheTableauInterface vueTacheTableauInterface = new VueTacheTableauInterface(modele);
            vueTacheTableauInterface.actualiser(sujet);
            this.add(vueTacheTableauInterface, x, y);
            x++;
        }
    }
}
