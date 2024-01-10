package mvc.vue.tache;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import mvc.controleur.tache.interfac.ControleurArchiver;
import mvc.controleur.tache.interfac.ControleurSupprimer;
import mvc.modele.ModeleOllert;
import mvc.modele.Sujet;
import mvc.vue.Observateur;
import mvc.vue.tache.contenu.vueinterface.*;

import java.util.List;

public class VueTacheInterface extends GridPane implements VueTache {

    /**
     * Constructeur de la classe VueTacheInterface
     */
    public VueTacheInterface(ModeleOllert modeleOllert){

        VueTitreInterface vueTitreInterface = new VueTitreInterface();
        Label l2 = new Label("Date de début :");
        VueDateDebut vueDateDebut = new VueDateDebut();
        Label l3 = new Label("Date de fin :");
        VueDateFin vueDateFin = new VueDateFin();
        Label l1 = new Label("Description de la tâche :");
        VueDescriptionInterface vueDescriptionInterface = new VueDescriptionInterface();
        VueEtiquettesInterface vueEtiquettesInterface = new VueEtiquettesInterface();
        VueMembresInterface vueMembresInterface = new VueMembresInterface();
        Label l4 = new Label("Priorité de la tâche");
        l4.setPadding(new Insets(20, 10, 20, 20));
        VuePrioriteInterface vuePrioriteInterface = new VuePrioriteInterface();
        VueDependanceInterface vueDependanceInterface = new VueDependanceInterface();
        //VueSousTacheInterface vueSousTacheInterface = new VueSousTacheInterface();
        Button archiver = new Button("Archiver");
        archiver.setOnAction(new ControleurArchiver(modeleOllert));
        Button supprimer = new Button("Supprimer");
        supprimer.setOnAction(new ControleurSupprimer(modeleOllert));

        this.add(vueTitreInterface, 0, 0);
        this.add(vueDateDebut, 10, 1);
        this.add(vueDateFin, 10, 2);
        this.add(vueDescriptionInterface, 0, 2);
        this.add(vueMembresInterface, 0, 3);
        this.add(vueEtiquettesInterface, 0, 4);
        this.add(vuePrioriteInterface, 0, 6);
        this.add(vueDependanceInterface, 0, 7);
        //this.add(vueSousTacheInterface, 0, 8);
        this.add(archiver, 10, 9);
        this.add(supprimer, 11, 9);
        this.add(l1, 0, 1);
        this.add(l2, 9, 1);
        this.add(l3, 9, 2);
        this.add(l4, 0, 5);
        this.setGridLinesVisible(true);

        GridPane.setColumnSpan(vueTitreInterface, this.getColumnCount());

        GridPane.setValignment(l3, Pos.TOP_CENTER.getVpos());
        GridPane.setValignment(vueDateFin, Pos.TOP_CENTER.getVpos());

        GridPane.setColumnSpan(vueMembresInterface, this.getColumnCount());
        GridPane.setColumnSpan(vueEtiquettesInterface, this.getColumnCount());
        GridPane.setColumnSpan(vuePrioriteInterface, this.getColumnCount());
        GridPane.setHalignment(vuePrioriteInterface, HPos.CENTER);

        GridPane.setMargin(archiver, new Insets(10, 20, 20, 20));
        GridPane.setMargin(supprimer, new Insets(10, 20, 20, 20));
        GridPane.setHalignment(archiver, HPos.RIGHT);

        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setHgrow(javafx.scene.layout.Priority.ALWAYS);
        this.getColumnConstraints().add(columnConstraints);



        this.setHgap(10);
        this.setVgap(10);
        this.setStyle("-fx-background-color: #a0a19b; -fx-border-color: black; -fx-border-width: 2px;");
    }

    @Override
    public void actualiser(Sujet sujet) {
        // -  pour les deux boutons qui ne sont pas des observateurs
        for (int i = 0; i < this.getChildren().size() - 7; i++) {
            ((Observateur) this.getChildren().get(i)).actualiser(sujet);
        }
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


}
