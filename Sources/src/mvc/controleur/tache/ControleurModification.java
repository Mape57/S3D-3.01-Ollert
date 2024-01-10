package mvc.controleur.tache;


import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import mvc.fabrique.FabriqueVueTableau;
import mvc.modele.ModeleOllert;
import mvc.vue.liste.VueListe;
import mvc.vue.tache.VueSousTacheTableau;
import mvc.vue.tache.VueTache;
import mvc.vue.tache.VueTacheInterface;
import ollert.tache.ListeTaches;
import ollert.tache.Tache;

/**
 * Contrôleur de la modification d'une tache
 */
public class ControleurModification implements EventHandler<MouseEvent> {

    /**
     * Modele de l'application
     */
    private ModeleOllert modele;


    /**
     * Constructeur de la classe ControleurModification
     * @param modele Modele de l'application
     */
    public ControleurModification(ModeleOllert modele) {
        this.modele = modele;
    }

/**
     * Gère la modification d'une tâche (ouvre une fenêtre pour afficher la tache étendue)
     * @param event action de l'utilisateur
     */
    @Override
    public void handle(MouseEvent event) {
        if (event.getSource() instanceof VueTache) {
            VueTache vueTache = (VueTache) event.getSource();
            VueListe vueListe;
            if (modele.getFabrique() instanceof FabriqueVueTableau) {
                vueListe = (VueListe) ((ScrollPane) vueTache.getParent().getProperties().get("scrollPane")).getParent();
            } else {
                vueListe = (VueListe) vueTache.getParent().getParent();
            }

            int indiceVL;
            int indiceVT;
            if (vueListe.getParent() instanceof HBox) {
                HBox parent = (HBox) vueListe.getParent();
                indiceVL = parent.getChildren().indexOf(vueListe);
                VBox listeTaches = (VBox) ((ScrollPane) vueListe.getChildren().get(1)).getContent();
                indiceVT = listeTaches.getChildren().indexOf(vueTache);
            } else {
                VBox parent = (VBox) vueListe.getParent();
                indiceVL = parent.getChildren().indexOf(vueListe);

                VueListe vl = (VueListe) parent.getChildren().get(indiceVL);
                VBox vb = (VBox) vl.getChildren().get(1);
                indiceVT = vb.getChildren().indexOf(vueTache);
            }

            Tache<ListeTaches> t = this.modele.getDonnee().getListes().get(indiceVL).getTache(indiceVT);

            if (modele.getTacheEnGrand() == null) {

                modele.setTacheEnGrand(t);
                VueTacheInterface vueTacheInterface = new VueTacheInterface(modele);
                modele.ajouterObservateur(vueTacheInterface);
                Stage stage = new Stage();
                stage.setOnHiding(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent windowEvent) {
                        modele.setTacheEnGrand(null);
                        modele.supprimerObservateur(vueTacheInterface);
                        modele.notifierObservateurs();
                    }
                });
                stage.setScene(new Scene(vueTacheInterface, 1200, 700));  // Ajustez la taille au besoin

                // Afficher la Stage
                stage.show();
                modele.notifierObservateurs();
            }
        }
        else if (event.getSource() instanceof VueSousTacheTableau){
            VueSousTacheTableau vueSousTacheTableau = (VueSousTacheTableau) event.getSource();
            VueListe vueListe;
            if (modele.getFabrique() instanceof FabriqueVueTableau) {
                vueListe = (VueListe) ((ScrollPane) vueSousTacheTableau.getParent().getProperties().get("scrollPane")).getParent();
            } else {
                vueListe = (VueListe) vueSousTacheTableau.getParent().getParent();
            }

            int indiceVL;
            int indiceVT;
            if (vueListe.getParent() instanceof HBox) {
                HBox parent = (HBox) vueListe.getParent();
                indiceVL = parent.getChildren().indexOf(vueListe);
                VBox listeTaches = (VBox) ((ScrollPane) vueListe.getChildren().get(1)).getContent();
                indiceVT = listeTaches.getChildren().indexOf(vueSousTacheTableau);
            } else {
                VBox parent = (VBox) vueListe.getParent();
                indiceVL = parent.getChildren().indexOf(vueListe);

                VueListe vl = (VueListe) parent.getChildren().get(indiceVL);
                VBox vb = (VBox) vl.getChildren().get(1);
                indiceVT = vb.getChildren().indexOf(vueSousTacheTableau);
            }

            Tache<ListeTaches> t = this.modele.getDonnee().getListes().get(indiceVL).getTache(indiceVT);

            if (modele.getTacheEnGrand() == null) {

                modele.setTacheEnGrand(t);
                VueTacheInterface vueTacheInterface = new VueTacheInterface(modele);
                modele.ajouterObservateur(vueTacheInterface);
                Stage stage = new Stage();
                stage.setOnHiding(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent windowEvent) {
                        modele.setTacheEnGrand(null);
                        modele.supprimerObservateur(vueTacheInterface);
                        modele.notifierObservateurs();
                    }
                });
                stage.setScene(new Scene(vueTacheInterface, 1200, 700));  // Ajustez la taille au besoin

                // Afficher la Stage
                stage.show();
                modele.notifierObservateurs();
            }
        }
    }
}
