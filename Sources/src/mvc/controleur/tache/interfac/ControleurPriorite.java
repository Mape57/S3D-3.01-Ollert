package mvc.controleur.tache.interfac;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import mvc.modele.ModeleOllert;

import static ollert.tache.donneesTache.Priorite.*;

public class ControleurPriorite implements EventHandler<ActionEvent> {

    private ModeleOllert modele;

    /**
     * Constructeur de la classe ControleurModification
     */
    public ControleurPriorite(ModeleOllert modele) {
        this.modele = modele;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        switch (((Button)actionEvent.getSource()).getText()){
            case "Faible":
                modele.getTacheEnGrand().setPriorite(FAIBLE);
                break;
            case "Moyenne":
                modele.getTacheEnGrand().setPriorite(MOYENNE);
                break;
            case "Elevée":
                modele.getTacheEnGrand().setPriorite(ELEVEE);
                break;
        }
        modele.notifierObservateurs();
    }
}
