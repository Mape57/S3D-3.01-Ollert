package mvc.controleur.tache.interfac;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import mvc.modele.ModeleOllert;

import java.time.LocalDate;

public class ControleurDateFin implements ChangeListener<LocalDate> {

    private ModeleOllert modele;

    public ControleurDateFin(ModeleOllert modeleOllert){
        this.modele = modeleOllert;
    }

    @Override
    public void changed(ObservableValue<? extends LocalDate> observableValue, LocalDate localDate, LocalDate t1) {
        modele.getTacheEnGrand().setDateFin(t1);
    }
}
