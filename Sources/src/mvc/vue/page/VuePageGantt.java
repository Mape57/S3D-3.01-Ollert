package mvc.vue.page;

import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import mvc.modele.ModeleOllert;
import mvc.modele.Sujet;
import ollert.Page;
import ollert.tache.Comparateur.ComparateurDateDebut;
import ollert.tache.ListeTaches;
import ollert.tache.TachePrincipale;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.awt.Point;

/**
 * Classe de la vue représentant une page sous forme de tableau
 */
public class VuePageGantt extends HBox implements VuePage {

    private Canvas canvas;

    private LocalDate dateDebutCalendrier;
    private LocalDate dateFinCalendrier;

    private final int ORIGIN_X = 20;
    private final int ORIGIN_Y = 20;
    private final int HAUTEUR_TACHE = 30;
    private final int LARGEUR_JOUR = 40;

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

        // Dessin du diagramme de Gantt sur le Canvas
        ArrayList<TachePrincipale> tachesPrincipalesSansAntecents = this.getTachesPrincipalesSansAntecedentTriParDateDebut(page);
        this.draw(canvas.getGraphicsContext2D(), tachesPrincipalesSansAntecents);
        // Ajout du Canvas à la HBox
        centre.getChildren().add(canvas);

    }

    public ArrayList<TachePrincipale> getTachesPrincipalesSansAntecedentTriParDateDebut(Page page) {
        List<ListeTaches> listes = page.getListes();
        // On rassemble les taches de toutes les listes triées par date de début
        ArrayList<TachePrincipale> taches = new ArrayList<>();
        for (ListeTaches lt : listes) {
            for (TachePrincipale t : lt.getTaches()) {
                if (t.getDateDebut() != null && t.getDateFin() != null) {
                    if (t.getAntecedents().isEmpty()) {
                        taches.add(t);
                    } else {
                        // Recherche de la date de fin du calendrier (la tâche qui termine le plus tard)
                        if (this.dateFinCalendrier == null || t.getDateFin().isAfter(this.dateFinCalendrier))
                            this.dateFinCalendrier = t.getDateFin();
                    }
                }
            }
        }

        // Tri des taches par date de début croissant
        taches.sort(new ComparateurDateDebut());

        // Date de début de calendrier après tri des tâches qui ne dépendent d'aucune autre car elle sont logiquement avant celles qui dépendent de celles-ci
        this.dateDebutCalendrier = taches.get(0).getDateDebut();

        return taches;
    }

    private void draw(GraphicsContext gc, ArrayList<TachePrincipale> taches){

        if(taches.isEmpty()){
            System.out.println("PAS DE TACHE AVEC UNE DATE DEBUT ET DE FIN");
            gc.setFill(Color.BLACK);
            gc.fillText("Graphique indisponible. Aucune tâche avec une date de début et de fin", 10, 315);
        }
        else {
            HashMap<TachePrincipale, Point> tacheCoordPourFleche = new HashMap<>();
            int coordYPinceau = ORIGIN_Y + 60;
            int largeurTache;
            int hauteurTache = HAUTEUR_TACHE;
            int largeurJour = LARGEUR_JOUR;

            for (TachePrincipale tache : taches) {

                this.dessinerTitreTache(gc, tache.getTitre(), coordYPinceau, HAUTEUR_TACHE);

                // On calcule la largeur de la tache
                largeurTache = tache.getDuree() * largeurJour;
                // On choisit une couleur aléatoire
                Color randomColor = new Color(Math.random(), Math.random(), Math.random(), 1.0);

                // On dessine la tâche
                int coordXTache = this.dessinerTache(gc, randomColor, coordYPinceau, largeurTache, tache.getDateDebut(), HAUTEUR_TACHE, LARGEUR_JOUR);
                tacheCoordPourFleche.put(tache, new Point(coordXTache, coordYPinceau+HAUTEUR_TACHE/2));

                while(!tache.getDependances().isEmpty()) {
                    for (TachePrincipale tacheDependante : tache.getDependances()) {
                        int largeurTacheAntecedent = largeurTache;
                        // Si la tache dépendante n'a pas déjà été dessinée, on dessine la tache et la fleche
                        if (!tacheCoordPourFleche.containsKey(tacheDependante)) {
                            coordYPinceau += hauteurTache + 20;
                            largeurTache = tacheDependante.getDuree() * largeurJour;
                            this.dessinerTitreTache(gc, tacheDependante.getTitre(), coordYPinceau, HAUTEUR_TACHE);
                            coordXTache = this.dessinerTache(gc, randomColor, coordYPinceau, largeurTache, tacheDependante.getDateDebut(), HAUTEUR_TACHE, LARGEUR_JOUR);
                            tacheCoordPourFleche.put(tacheDependante, new Point(coordXTache, coordYPinceau + HAUTEUR_TACHE / 2));
                        }
                        Point pDepart = tacheCoordPourFleche.get(tache);
                        Point pArrivee = tacheCoordPourFleche.get(tacheDependante);
                        this.dessinerFleche(gc, pDepart, pArrivee, largeurTacheAntecedent);
                    }
                    tache = tache.getDependances().get(0);

                }

                // On décale le pinceau
                coordYPinceau += hauteurTache + 20;
            }
            this.dessinerAxeDates(gc);
        }
    }

    private int dessinerTache(GraphicsContext gc, Color color, int coordYPinceau, int largeurTache, LocalDate dateDebut, int hauteurTache, int largeurJour){

        // Espace de 200 pixels pour commencer à dessiner les taches
        int coordXPinceau = ORIGIN_X + 200;

        long decalageJours = ChronoUnit.DAYS.between(dateDebutCalendrier, dateDebut);
        coordXPinceau += decalageJours * largeurJour;

        gc.setFill(color);
        gc.fillRect(coordXPinceau, coordYPinceau, largeurTache, hauteurTache);

        return coordXPinceau;
    }

    private void dessinerTitreTache(GraphicsContext gc, String titre, int coordYPinceau, int hauteurTache){
        gc.setFill(Color.BLACK);

        // Décalage de 20 pixel à chaque début de ligne

        // Affichage du titre de la tache
        if (titre.length() > 20) {
            titre = titre.substring(0, 20) + "...";
        }

        // Calcul de la hauteur du texte pour le centrage par rapport à la tâche
        Text text = new Text(titre);
        double height = text.getLayoutBounds().getHeight();
        // Centrer le texte verticalement par rapport à la tache
        double textY = coordYPinceau + hauteurTache / 2 + height / 2;
        gc.fillText(titre, ORIGIN_X, textY);
    }

    private void dessinerFleche(GraphicsContext gc, Point pDepart, Point pArrivee, int largeurTacheAntecedent){
        gc.setStroke(Color.YELLOW);
        gc.setLineWidth(4);
        gc.strokeLine(pDepart.getX()+largeurTacheAntecedent, pDepart.getY(), pArrivee.getX(), pArrivee.getY());
    }

    private void dessinerAxeDates(GraphicsContext gc){
        int coordXPinceau = ORIGIN_X + 200; // décalage initial de 200 pixels
        int coordYPinceau = ORIGIN_Y; // position de l'axe des dates
        int largeurJour = LARGEUR_JOUR;
        gc.setFill(Color.BLACK);
        gc.setFont(new Font("Arial", 12));

        // Parcourir chaque jour entre dateDebutCalendrier et dateFinCalendrier
        for (LocalDate date = dateDebutCalendrier; !date.isAfter(dateFinCalendrier); date = date.plusDays(1)) {
            // Dessiner une marque pour chaque jour
            gc.fillText(String.valueOf(date.getDayOfMonth()), coordXPinceau, coordYPinceau);
            // Dessiner le nom du mois au début de chaque mois
            if (date.getDayOfMonth() == 1) {
                gc.fillText(date.getMonth().getDisplayName(TextStyle.FULL, Locale.FRANCE), coordXPinceau, coordYPinceau - 20);
            }
            coordXPinceau += largeurJour;
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
        return new ArrayList<>();
    }
}