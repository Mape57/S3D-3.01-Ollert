package mvc.vue.page;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import mvc.modele.ModeleOllert;
import mvc.modele.Sujet;
import ollert.DiagGantt;
import ollert.Page;
import ollert.tache.Comparateur.ComparateurDateDebut;
import ollert.tache.ListeTaches;
import ollert.tache.TachePrincipale;
import java.util.*;

/**
 * Classe de la vue représentant la page contenant des listes de tâches sous forme de diagramme de Gantt
 */
public class VuePageGantt extends HBox implements VuePage {

    /**
     * Canvas du diagramme de GANTT
     */
    private final DiagGantt canvas;

    /**
     * Constructeur de la classe VuePageTableau
     */
    public VuePageGantt() {

        this.canvas = new DiagGantt(1500,500, Color.WHITE);

        // centre de la page
        ParentScrollPane centre = new ParentScrollPane();
        centre.setContentAndChildrenProp(new HBox());

        this.setWidth(800);
        this.setHeight(500);
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

        Page page = modele.getDonnee();

        // Dessin du diagramme de Gantt sur le Canvas
        ArrayList<TachePrincipale> tachesPrincipalesSansAntecedents = this.getTachesPrincipalesSansAntecedentTriParDateDebut(page);
        this.canvas.draw(canvas.getGraphicsContext2D(), tachesPrincipalesSansAntecedents);
        // Ajout du Canvas à la HBox
        centre.getChildren().add(canvas);

    }

    /**
     * Récupère les tâches principales sans antécédents triées par date de début (qui seront à la base du diagramme de Gantt)
     * @param page Page ayant les listes de tâches
     * @return ArrayList des tâches principales sans antécédents triées par date de début
     */
    public ArrayList<TachePrincipale> getTachesPrincipalesSansAntecedentTriParDateDebut(Page page) {
        List<ListeTaches> listes = page.getListes();
        // On rassemble les taches de toutes les listes triées par date de début
        ArrayList<TachePrincipale> taches = new ArrayList<>();
        for (ListeTaches lt : listes) {
            for (TachePrincipale t : lt.getTaches()) {
                // On ne prend que les tâches qui ont une date de début et de fin
                if (t.getDateDebut() != null && t.getDateFin() != null) {
                    if (t.getAntecedents().isEmpty()) {
                        taches.add(t);
                    } else {
                        // Recherche de la date de fin du calendrier (la tâche qui termine le plus tard)
                        if (this.canvas.getDateFinCalendrier() == null || t.getDateFin().isAfter(this.canvas.getDateFinCalendrier()))
                            this.canvas.setDateFinCalendrier(t.getDateFin());
                    }
                }
            }
        }

        // Tri des taches par date de début croissant
        taches.sort(new ComparateurDateDebut());

        // Date de début de calendrier après tri des tâches qui ne dépendent d'aucune autre, car elles sont logiquement avant celles qui dépendent de celles-ci
        this.canvas.setDateDebutCalendrier(taches.get(0).getDateDebut());

        return taches;
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
        return new ArrayList<>();
    }
}