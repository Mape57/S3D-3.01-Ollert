package mvc.controleur.tache.interfac;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import mvc.modele.ModeleOllert;
import mvc.vue.tache.VueTacheInterface;
import ollert.tache.TachePrincipale;

public class ControlleurDescription implements ChangeListener<String> {

    private ModeleOllert modele;

    public ControlleurDescription(ModeleOllert modeleOllert){
        this.modele = modeleOllert;
    }

    @Override
    public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
        modele.getTacheEnGrand().setDescription(t1);
        modele.notifierObservateurs();
    }
}
