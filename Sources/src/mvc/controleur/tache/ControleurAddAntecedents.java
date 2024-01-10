package mvc.controleur.tache;

import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import mvc.modele.ModeleOllert;
import mvc.vue.liste.VueListe;
import mvc.vue.tache.VueTache;
import ollert.tache.TachePrincipale;

/**
 * Contrôleur de la deuxième partie d'ajout d'antécédents (le clic sur une tâche après avoir cliqué sur les menottes) à une tâche
 */
public class ControleurAddAntecedents implements EventHandler<MouseEvent> {

    /**
     * Modele de l'application
     */
    private final ModeleOllert modele;

    /**
     * Constructeur du contrôleur
     * @param modeleControle Modele de l'application
     */
    public ControleurAddAntecedents(ModeleOllert modeleControle) {
        this.modele = modeleControle;
    }

    /**
     * Gère l'ajout d'antécédents à une tâche (clic sur une tâche après avoir cliqué sur les menottes)
     * @param event action de l'utilisateur
     */
    @Override
    public void handle(MouseEvent event) {
        VueTache vt = (VueTache)event.getSource();
        VueListe vl = (VueListe)vt.getParentPrincipale();
        ScrollPane sp = (ScrollPane) vl.getChildren().get(1);
        VBox vb = (VBox)sp.getContent();
        int indice = vb.getChildren().indexOf(vt);

        HBox hb = (HBox)vl.getParent();
        int indiceListe = hb.getChildren().indexOf(vl);

        TachePrincipale t = modele.getDonnee().getListes().get(indiceListe).getTaches().get(indice);

        if (modele.getListeAnt().contains(t)) {
            t.supprimerDependance(modele.getTacheCible());
        }else{
            // verification tache selection se termine avant le debut de la tache cible
            if (t.getDateDebut()!=null && t.getDateFin()!=null && modele.getTacheCible().getDateDebut().isAfter(t.getDateFin())){
                System.out.println("Date valide");
                t.ajouterDependance(modele.getTacheCible());
            }else{
                System.out.println("non");
            }
        }



        modele.notifierObservateurs();

    }
}