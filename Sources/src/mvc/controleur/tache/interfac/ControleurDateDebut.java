package mvc.controleur.tache.interfac;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import mvc.modele.ModeleOllert;

import java.time.LocalDate;

public class ControleurDateDebut implements ChangeListener<LocalDate> {

    private ModeleOllert modele;

    public ControleurDateDebut(ModeleOllert modeleOllert){
        this.modele = modeleOllert;
    }

    @Override
    public void changed(ObservableValue<? extends LocalDate> observableValue, LocalDate localDate, LocalDate t1) {
        modele.getTacheEnGrand().setDateDebut(t1);
    }
}
