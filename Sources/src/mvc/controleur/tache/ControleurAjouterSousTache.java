package mvc.controleur.tache;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import mvc.vue.tache.VueTache;

/**
 * ControleurAjouterSousTache est le contrôleur du bouton pour ajouter une sous tâche
 */
public class ControleurAjouterSousTache implements EventHandler<ActionEvent> {

    /**
     * VueTache réelle que représente le modèle/vue VueTache contenant les observateurs/vues du contenu de la tâche
     */
    private VueTache modele;

    /**
     * Constructeur de la classe ControleurModification
     */
    public ControleurAjouterSousTache(VueTache modele) {
        this.modele = modele;
    }

    /**
     * Ajoute une sous-tâche à la tâche réelle et notifie les observateurs
     * @param event Evènement déclencheur (click)
     */
    @Override
    public void handle(ActionEvent event) {
        modele.getTache().addSousTache("Nouvelle sous-tâche");
        modele.notifierObservateurs();
    }
}
