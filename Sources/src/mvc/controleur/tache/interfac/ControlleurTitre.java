package mvc.controleur.tache.interfac;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import mvc.modele.ModeleOllert;
import mvc.vue.tache.VueTache;
import mvc.vue.tache.VueTacheInterface;
import ollert.tache.TachePrincipale;

public class ControlleurTitre implements ChangeListener<String> {

    private ModeleOllert modele;

    public ControlleurTitre(ModeleOllert modeleOllert){
        this.modele = modeleOllert;
    }

    @Override
    public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
        System.out.println("ControleurTitre");
        System.out.println("Titre avant : " + modele.getTacheEnGrand().getTitre());
        modele.getTacheEnGrand().setTitre(t1);
        System.out.println("Titre après : " + modele.getTacheEnGrand().getTitre());
        modele.notifierObservateurs();
    }
}
