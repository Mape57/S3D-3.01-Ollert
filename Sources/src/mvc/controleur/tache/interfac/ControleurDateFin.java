package mvc.controleur.tache.interfac;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import mvc.modele.ModeleOllert;

import java.time.LocalDate;

/**
 * Classe ControleurDateFin qui permet de modifier la date de fin d'une tâche
 */
public class ControleurDateFin implements ChangeListener<LocalDate> {

    /**
     * Modele de l'application
     */
    private final ModeleOllert modele;

    /**
     * Constructeur de la classe ControleurDateFin
     * @param modeleOllert Modele de l'application
     */
    public ControleurDateFin(ModeleOllert modeleOllert){
        this.modele = modeleOllert;
    }

    /**
     * Gère la modification de la date de fin d'une tâche
     * @param observableValue observable
     * @param localDate ancienne date de fin
     * @param t1 nouvelle date de fin
     */
    @Override
    public void changed(ObservableValue<? extends LocalDate> observableValue, LocalDate localDate, LocalDate t1) {
        // FIXME : A vérifier !
        modele.getTacheEnGrand().setDateFin(t1);
    }
}
