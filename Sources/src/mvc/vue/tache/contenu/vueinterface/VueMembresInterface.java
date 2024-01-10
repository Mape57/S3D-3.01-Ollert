package mvc.vue.tache.contenu.vueinterface;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import mvc.controleur.tache.interfac.ControlleurAjoutMembre;
import mvc.controleur.tache.interfac.ControlleurSupprMembre;
import mvc.modele.ModeleOllert;
import mvc.modele.Sujet;
import mvc.vue.Observateur;
import mvc.vue.tache.VueTacheInterface;
import ollert.tache.TachePrincipale;
import ollert.tache.donneesTache.Etiquette;
import ollert.tache.donneesTache.Utilisateur;

import java.util.ArrayList;
import java.util.List;

public class VueMembresInterface extends GridPane implements Observateur {

    public VueMembresInterface(){
        Label membres = new Label("Membres participants");
        Button supprMembre = new Button("Supprimer");
        Button ajoutMembre = new Button("Ajouter");
        this.add(membres, 0, 0);
        this.add(ajoutMembre, 9, 0);
        this.add(supprMembre, 10, 0);
        this.setHgap(50);
        this.setPadding(new Insets(20));
    }

    @Override
    public void actualiser(Sujet sujet) {
        ModeleOllert modele = (ModeleOllert) sujet;
        TachePrincipale tache = (TachePrincipale) modele.getTacheEnGrand();
        FlowPane flowPane  = new FlowPane();
        flowPane.setPrefWrapLength(Double.MAX_VALUE);
        flowPane.setPrefWidth(1200);
        flowPane.setHgap(50);
        flowPane.setVgap(10);
        flowPane.setPadding(new Insets(10));
        GridPane.setColumnSpan(flowPane, 9);
        this.add(flowPane, 0,1);
        List<Label> list = new ArrayList<>();
        for (Utilisateur u : tache.getMembres()) {
            Label label = new Label(u.getPseudo());
            flowPane.getChildren().add(label);
            list.add(label);
        }
        ((Button)this.getChildren().get(1)).setOnAction(new ControlleurAjoutMembre(modele));
        ((Button)this.getChildren().get(2)).setOnAction(new ControlleurSupprMembre(modele, list));
    }
}
