package mvc.controleur.tache;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import mvc.fabrique.FabriqueVueTableau;
import mvc.modele.ModeleOllert;
import mvc.vue.liste.VueListe;
import mvc.vue.tache.VueTache;
import ollert.tache.TachePrincipale;

/**
 * Contrôleur de la première partie d'ajout d'antécédents (clic sur les menottes) à une tâche pour indiquer que cette tâche va être dépendante d'une autre
 */
public class ControleurAntecedents implements EventHandler<ActionEvent> {

    /**
     * Modele de l'application
     */
    private final ModeleOllert modele;

    /**
     * Constructeur du contrôleur
     * @param modeleControle Modele de l'application
     */
    public ControleurAntecedents(ModeleOllert modeleControle) {
        this.modele = modeleControle;
    }

    /**
     * Gère l'ajout d'antécédents à une tâche (clic sur les menottes)
     * @param event action de l'utilisateur
     */
    @Override
    public void handle(ActionEvent event) {

        VueTache vueTache = (VueTache) ((Button) event.getSource()).getParent();
        VueListe vueListe;
        if (modele.getFabrique() instanceof FabriqueVueTableau){
            vueListe = (VueListe) ((ScrollPane) vueTache.getParent().getProperties().get("scrollPane")).getParent();
        }else{
            vueListe = (VueListe)vueTache.getParent().getParent();
        }

        int indiceVL;
        int indiceVT;
        if (vueListe.getParent() instanceof HBox){
            HBox parent = (HBox)vueListe.getParent();
            indiceVL = parent.getChildren().indexOf(vueListe);
            VBox listeTaches = (VBox)((ScrollPane) vueListe.getChildren().get(1)).getContent();
            indiceVT = listeTaches.getChildren().indexOf(vueTache);
            indiceVT = listeTaches.getChildren().indexOf(vueTache);
        }else{
            VBox parent = (VBox)vueListe.getParent();
            indiceVL = parent.getChildren().indexOf(vueListe);

            VueListe vl = (VueListe)parent.getChildren().get(indiceVL);
            VBox vb = (VBox)vl.getChildren().get(1);
            indiceVT = vb.getChildren().indexOf(vueTache);
        }
        TachePrincipale t = this.modele.getDonnee().getListes().get(indiceVL).getTache(indiceVT);


        //System.out.println("Antecedents ");
        if (modele.getListeAnt() == null){
            modele.setListeAnt(t.getAntecedents());
            modele.setTacheCible(t);
        }else{
            if (modele.getTacheCible()==t){
                modele.setTacheCible(null);
                modele.setListeAnt(null);
            }else{
                modele.setListeAnt(t.getAntecedents());
                modele.setTacheCible(t);
            }

        }

        //System.out.println(t.getAntecedents());
        modele.notifierObservateurs();
    }
}
