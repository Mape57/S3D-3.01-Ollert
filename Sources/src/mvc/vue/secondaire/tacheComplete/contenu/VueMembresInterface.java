package mvc.vue.secondaire.tacheComplete.contenu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import mvc.vue.structure.Observateur;
import mvc.controleur.tacheComplete.ControleurAjoutMembre;
import mvc.controleur.tacheComplete.ControleurSupprMembre;
import mvc.modele.ModeleOllert;
import mvc.modele.Sujet;
import ollert.donnee.tache.TacheAbstraite;
import ollert.donnee.tache.attribut.Utilisateur;

import java.io.ByteArrayInputStream;
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
        TacheAbstraite<?> tache = modele.getTacheComplete();
        FlowPane flowPane  = new FlowPane();
        flowPane.setPrefWrapLength(Double.MAX_VALUE);
        flowPane.setPrefWidth(1200);
        flowPane.setHgap(50);
        flowPane.setVgap(10);
        flowPane.setPadding(new Insets(10));
        GridPane.setColumnSpan(flowPane, 9);
        this.add(flowPane, 0,1);
        List<HBox> list = new ArrayList<>();
        for (Utilisateur u : tache.getMembres()) {
            Button membre = new Button();
            byte[] imageInByte = u.getIcone();
            Image image = new Image(new ByteArrayInputStream(imageInByte));
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(25);
            imageView.setFitHeight(25);
            membre.setBackground(null);
            membre.setGraphic(imageView);

            Label label = new Label(u.getPseudo().substring(1));
            label.setPadding(new Insets(-5, -5, -5, -5));
            HBox hBox = new HBox();
            hBox.getChildren().addAll(membre, label);
            hBox.setAlignment(Pos.CENTER);
            flowPane.getChildren().add(hBox);
            list.add(hBox);
        }
        ((Button)this.getChildren().get(1)).setOnAction(new ControleurAjoutMembre(modele));
        ((Button)this.getChildren().get(2)).setOnAction(new ControleurSupprMembre(modele, list));
    }
}
