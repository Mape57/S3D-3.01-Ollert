package mvc.controleur.tache.interfac;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import mvc.modele.ModeleOllert;
import mvc.vue.tache.VueTacheInterface;
import ollert.tache.TachePrincipale;

import java.time.LocalDate;

public class ControlleurDateDebut implements ChangeListener<LocalDate> {

    private ModeleOllert modele;

    public ControlleurDateDebut(ModeleOllert modeleOllert){
        this.modele = modeleOllert;
    }

    @Override
    public void changed(ObservableValue<? extends LocalDate> observableValue, LocalDate localDate, LocalDate t1) {
        modele.getTacheEnGrand().setDateDebut(t1);
        modele.notifierObservateurs();
    }
}
