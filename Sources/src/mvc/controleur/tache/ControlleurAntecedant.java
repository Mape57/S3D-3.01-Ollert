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

public class ControlleurAntecedant implements EventHandler<ActionEvent> {

    private ModeleOllert modele;
    public ControlleurAntecedant(ModeleOllert modeleControle) {
        this.modele = modeleControle;
    }

    @Override
    public void handle(ActionEvent event) {
        System.out.println("Dependance");

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
        }else{
            VBox parent = (VBox)vueListe.getParent();
            indiceVL = parent.getChildren().indexOf(vueListe);

            VueListe vl = (VueListe)parent.getChildren().get(indiceVL);
            VBox vb = (VBox)vl.getChildren().get(1);
            indiceVT = vb.getChildren().indexOf(vueTache);
        }
        TachePrincipale t = this.modele.getDonnee().getListes().get(indiceVL).getTache(indiceVT);

        System.out.println(t.getDependances());
    }
}
