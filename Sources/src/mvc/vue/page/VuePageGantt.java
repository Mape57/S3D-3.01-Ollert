package mvc.vue.page;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import mvc.controleur.liste.ControlleurModifierTitre;
import mvc.controleur.page.ControlleurAjouterListe;
import mvc.controleur.page.ControlleurTableau;
import mvc.controleur.page.ControlleurTableur;
import mvc.fabrique.FabriqueVueTableau;
import mvc.modele.ModeleOllert;
import mvc.modele.Sujet;
import mvc.vue.Observateur;
import mvc.vue.liste.VueListe;
import mvc.vue.liste.VueListeTableau;
import ollert.Page;
import ollert.tache.ListeTaches;
import ollert.tache.TachePrincipale;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * Classe de la vue représentant une page sous forme de tableau
 */
public class VuePageGantt extends HBox implements VuePage {

    private Canvas canvas;

    /**
     * Constructeur de la classe VuePageTableau
     */
    public VuePageGantt(ModeleOllert modeleControle) {

        this.canvas = new Canvas(800,800);
        this.canvas.getGraphicsContext2D().setFill(Color.WHITE);

        // centre de la page
        ParentScrollPane centre = new ParentScrollPane();
        centre.setContentAndChildrenProp(new HBox());

        this.setFillHeight(true);
        this.getChildren().add(centre);
    }

    /**
     * Actualise la vue
     * @param sujet le modèle à partir duquel la vue est actualisée
     */
    @Override
    public void actualiser(Sujet sujet) {
        ModeleOllert modele = (ModeleOllert) sujet;
        HBox centre = (HBox) ((ScrollPane) this.getChildren().get(0)).getContent();
        centre.getChildren().clear();

        Page page = (Page)modele.getDonnee();
        List<ListeTaches> liste = page.getListes();



        // Dessin du diagramme de Gantt sur le Canvas
        this.draw(canvas.getGraphicsContext2D());
        // Ajout du Canvas à la HBox
        centre.getChildren().add(canvas);

    }

    private void draw(GraphicsContext gc) {
        gc.setFill(Color.BLACK);

        // Draw time line (x-axis)
        gc.strokeLine(50, 350, 350, 350);

        // Draw task lines
        gc.setFill(Color.BLUE);
        gc.fillRect(70, 300, 50, 30); // Task 1
        gc.setFill(Color.RED);
        gc.fillRect(130, 300, 70, 30); // Task 2
        gc.setFill(Color.GREEN);
        gc.fillRect(210, 300, 120, 30); // Task 3

        // Draw task names (y-axis)
        gc.setFill(Color.BLACK);
        gc.fillText("Task 1", 10, 315);
        gc.fillText("Task 2", 10, 275);
        gc.fillText("Task 3", 10, 235);

        // Draw time duration (x-axis)
        for (int i = 0; i <= 300; i += 50) {
            gc.fillText(String.valueOf(i), 50 + i, 365);
        }
    }


    /**
     * @return page réelle que représente la vue
     */
    public Page getPage() {
        return null;
    }

    @Override
    public List<Integer> getLocalisation() {
        return new ArrayList<>();
    }
}