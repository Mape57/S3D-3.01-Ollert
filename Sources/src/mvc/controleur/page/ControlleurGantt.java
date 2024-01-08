package mvc.controleur.page;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import mvc.fabrique.FabriqueVueGantt;
import mvc.fabrique.FabriqueVueTableau;
import mvc.fabrique.FabriqueVueTableur;
import mvc.modele.ModeleOllert;
import mvc.vue.page.VuePage;
import mvc.vue.page.VuePageTableau;
import mvc.vue.page.VuePageTableur;

/**
 * Controlleur pour l'affichage en Gantt
 */
public class ControlleurGantt implements EventHandler<ActionEvent> {
    /**
     * Modele de l'application
     */
    private ModeleOllert modele;

    /**
     * Constructeur du controlleur
     * @param modele
     */
    public ControlleurGantt(ModeleOllert modele) {
        this.modele = modele;
    }

    /**
     * Change l'affichage en Gantt au click du bouton Gantt
     * @param event l'evenement utilisateur
     */
    @Override
    public void handle(ActionEvent event) {
        modele.setFabrique(new FabriqueVueGantt());
        Button src = (Button) event.getSource();
        BorderPane racine = (BorderPane) src.getParent().getParent();

        VuePage vp = modele.getFabrique().creerVuePage(modele);
        racine.setCenter((Node) vp);
        modele.ajouterObservateur(vp);
        modele.notifierObservateurs();
    }
}
