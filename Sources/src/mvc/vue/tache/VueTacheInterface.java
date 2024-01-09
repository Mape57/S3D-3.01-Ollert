package mvc.vue.tache;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import mvc.controleur.tache.interfac.ControlleurArchiver;
import mvc.controleur.tache.interfac.ControlleurSupprEtiquette;
import mvc.controleur.tache.interfac.ControlleurSupprimer;
import mvc.modele.ModeleOllert;
import mvc.modele.Sujet;
import mvc.vue.Observateur;
import mvc.vue.tache.contenu.vueinterface.*;
import ollert.tache.TachePrincipale;

import java.util.List;

public class VueTacheInterface extends GridPane implements VueTache {

    /**
     * Constructeur de la classe VueTacheInterface
     */
    public VueTacheInterface(ModeleOllert modeleOllert){

        VueTitreInterface vueTitreInterface = new VueTitreInterface();
        VueDateDebut vueDateDebut = new VueDateDebut();
        VueDateFin vueDateFin = new VueDateFin();
        VueDescriptionInterface vueDescriptionInterface = new VueDescriptionInterface();
        VueEtiquettesInterface vueEtiquettesInterface = new VueEtiquettesInterface();
        VueMembresInterface vueMembresInterface = new VueMembresInterface();
        VuePrioriteInterface vuePrioriteInterface = new VuePrioriteInterface();
        Button archiver = new Button("Archiver");
        archiver.setOnAction(new ControlleurArchiver(modeleOllert));
        Button supprimer = new Button("Supprimer");
        supprimer.setOnAction(new ControlleurSupprimer(modeleOllert));

        this.add(vueTitreInterface, 0, 0);
        this.add(vueDateDebut, 10, 1);
        this.add(vueDateFin, 10, 2);
        this.add(vueDescriptionInterface, 0, 2);
        this.add(vueMembresInterface, 0, 3);
        this.add(vueEtiquettesInterface, 0, 4);
        this.add(vuePrioriteInterface, 0, 5);
        this.add(archiver, 9, 6);
        this.add(supprimer, 10, 6);
        GridPane.setColumnSpan(vueTitreInterface, this.getColumnCount());



        this.setHgap(10);
        this.setVgap(10);
        this.setStyle("-fx-background-color: #a0a19b; -fx-border-color: black; -fx-border-width: 2px;");
    }

    @Override
    public void actualiser(Sujet sujet) {
        for (int i = 0; i < this.getChildren().size(); i++)
            ((Observateur) this.getChildren().get(i)).actualiser(sujet);
    }

    @Override
    public Node getChildrenPrincipale() {
        return null;
    }

    @Override
    public Node getParentPrincipale() {
        return null;
    }

    @Override
    public List<Integer> getLocalisation() {
        return null;
    }

    @Override
    public TachePrincipale getTache() {
        return null;
    }


}