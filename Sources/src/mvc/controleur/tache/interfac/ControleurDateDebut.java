package mvc.controleur.tache.interfac;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import mvc.modele.ModeleOllert;

import java.time.LocalDate;

/**
 * Classe ControleurDateDebut qui permet de modifier la date de début d'une tâche
 */
public class ControleurDateDebut implements ChangeListener<LocalDate> {

    /**
     * Modele de l'application
     */
    private final ModeleOllert modele;

    /**
     * Constructeur de la classe ControleurDateDebut
     * @param modeleOllert Modele de l'application
     */
    public ControleurDateDebut(ModeleOllert modeleOllert){
        this.modele = modeleOllert;
    }

    /**
     * Gère la modification de la date de début d'une tâche
     * @param observableValue observable
     * @param localDate ancienne date de début
     * @param t1 nouvelle date de début
     */
    @Override
    public void changed(ObservableValue<? extends LocalDate> observableValue, LocalDate localDate, LocalDate t1) {
        // FIXME : A vérifier !
        modele.getTacheEnGrand().setDateDebut(t1);
    }
}
