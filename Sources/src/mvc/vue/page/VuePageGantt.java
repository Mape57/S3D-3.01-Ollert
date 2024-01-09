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
import javafx.scene.text.Text;
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
import ollert.tache.Comparateur.ComparateurDateDebut;
import ollert.tache.ListeTaches;
import ollert.tache.TachePrincipale;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * Classe de la vue représentant une page sous forme de tableau
 */
public class VuePageGantt extends HBox implements VuePage {

    private Canvas canvas;

    /**
     * Constructeur de la classe VuePageTableau
     */
    public VuePageGantt(ModeleOllert modeleControle) {

        this.canvas = new Canvas(1500,500);
        this.canvas.getGraphicsContext2D().setFill(Color.WHITE);

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

        Page page = (Page)modele.getDonnee();
        List<ListeTaches> listes = page.getListes();



        // Dessin du diagramme de Gantt sur le Canvas
        this.draw(canvas.getGraphicsContext2D(), listes);
        // Ajout du Canvas à la HBox
        centre.getChildren().add(canvas);

    }

    private void draw(GraphicsContext gc, List<ListeTaches> listes){


        // On rassemble les taches de toutes les listes triées par date de début
        ArrayList<TachePrincipale> taches = new ArrayList<>();
        for(ListeTaches lt : listes){
            for(TachePrincipale t : lt.getTaches()){
                if(t.getDateDebut() != null && t.getDateFin() != null && t.getAntecedents().isEmpty()) {
                    System.out.println(t.getTitre());
                    taches.add(t);
                }
            }
        }

        // Tri des taches par date de début croissant
        taches.sort(new ComparateurDateDebut());

        System.out.println(taches.toString());

        if(taches.isEmpty()){
            System.out.println("PAS DE TACHE AVEC UNE DATE DEBUT ET DE FIN");
            gc.setFill(Color.BLACK);
            gc.fillText("Graphique indisponible. Aucune tâche avec une date de début et de fin", 10, 315);
        }
        else {

            int coordXPinceau = 0;
            int coordYPinceau = 20;
            int largeurTache;
            int hauteurTache = 30;
            int largeurJour = 40;

            for (TachePrincipale tache : taches) {
                System.out.println(tache.getTitre());
                gc.setFill(Color.BLACK);
                coordXPinceau = 20;
                String titre = tache.getTitre();
                if (titre.length() > 20) {
                    titre = titre.substring(0, 20) + "...";
                }

                // Calcul de la hauteur du texte pour le centrage
                Text text = new Text(titre);
                double height = text.getLayoutBounds().getHeight();
                // Centrer le texte verticalement par rapport à la tache
                double textY = coordYPinceau + hauteurTache / 2 + height / 2;
                gc.fillText(titre, coordXPinceau, textY);

                coordXPinceau += 200;
                // On calcule la largeur de la tache
                largeurTache = tache.getDuree() * largeurJour;
                // On dessine la tache
                double rouge = Math.random();
                double vert = Math.random();
                double bleu = Math.random();

                // Créer une nouvelle couleur avec ces valeurs
                Color randomColor = new Color(rouge, vert, bleu, 1.0);
                gc.setFill(randomColor);
                gc.fillRect(coordXPinceau, coordYPinceau, largeurTache, hauteurTache);

                for (TachePrincipale tacheDependante : tache.getDependances()) {
                    largeurTache = tacheDependante.getDuree() * largeurJour;
                    coordXPinceau += largeurTache;
                    coordYPinceau += hauteurTache + 20;

                    gc.setFill(Color.BLACK);
                    titre = tacheDependante.getTitre();
                    if (titre.length() > 20) {
                        titre = titre.substring(0, 20) + "...";
                    }

                    // Calcul de la hauteur du texte pour le centrage
                    text = new Text(titre);
                    height = text.getLayoutBounds().getHeight();
                    // Centrer le texte verticalement par rapport à la tache
                    textY = coordYPinceau + hauteurTache / 2 + height / 2;
                    gc.fillText(titre, 20, textY);

                    coordXPinceau += 100;
                    // On calcule la largeur de la tache
                    // On dessine la tache
                    // Créer une nouvelle couleur avec ces valeurs
                    randomColor = new Color(rouge, vert, bleu, 1.0);
                    gc.setFill(randomColor);
                    gc.fillRect(coordXPinceau, coordYPinceau, largeurTache, hauteurTache);
                }

                // On décale le pinceau
                coordYPinceau += hauteurTache + 20;
            }
        }
    }


    /**
     * @return page réelle que représente la vue
     */
    public Page getPage() {
        return null;
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