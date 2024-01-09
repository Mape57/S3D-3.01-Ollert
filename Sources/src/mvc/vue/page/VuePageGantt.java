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
 * Classe de la vue représentant la page contenant des listes de tâches sous forme de diagramme de Gantt
 */
public class VuePageGantt extends HBox implements VuePage {

    private final Canvas canvas;

    private LocalDate dateDebutCalendrier;
    private LocalDate dateFinCalendrier;

    private final int ORIGIN_X = 20;
    private final int ORIGIN_Y = 40;
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

    /**
     * Récupère les tâches principales sans antécédent triées par date de début (qui seront à la base du diagramme de Gantt)
     * @param page Page ayant les listes de tâches
     * @return ArrayList des tâches principales sans antécédent triées par date de début
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

    /**
     * Dessine le diagramme de Gantt
     * @param gc GraphicsContext
     * @param tachesPrincipalesSansAntecedent liste des taches principales sans antecedent triées par date de début
     */
    private void draw(GraphicsContext gc, ArrayList<TachePrincipale> tachesPrincipalesSansAntecedent){

        // Si aucune tache n'a de date de début et de fin, on affiche un message d'erreur
        if(tachesPrincipalesSansAntecedent.isEmpty()){
            System.out.println("PAS DE TACHE AVEC UNE DATE DEBUT ET DE FIN");
            gc.setFill(Color.BLACK);
            gc.fillText("Graphique indisponible. Aucune tâche avec une date de début et de fin", 10, 315);
        }
        // Sinon, on dessine le diagramme de Gantt
        else {
            /*
               On stocke les tâches déjà dessinées avec les coordonnées du milieu de l'arrête gauche pour pouvoir dessiner les flèches
               On sait donc s'il faut dessiner une tâche ou si elle est déjà présente.
               Si elle est déjà présente, il faut dessiner la flèche dont on a la destination (les coordonnées du milieu de l'arrête gauche de la tâche)
             */
            HashMap<TachePrincipale, Point> tacheCoordPourFleche = new HashMap<>();
            int coordYPinceau = ORIGIN_Y + 20; // Gère la position verticale du pinceau pour dessiner les tâches
            int largeurTache;
            int hauteurTache = HAUTEUR_TACHE;
            int largeurJour = LARGEUR_JOUR;

            // On dessine les tâches principales sans antécédent dont les tâches dépendantes vont découler
            for (TachePrincipale tache : tachesPrincipalesSansAntecedent) {

                // On dessine le titre de la tâche
                this.dessinerTitreTache(gc, tache.getTitre(), coordYPinceau, HAUTEUR_TACHE);

                // On calcule la largeur de la tache
                largeurTache = tache.getDuree() * largeurJour;
                // On choisit une couleur aléatoire
                Color randomColor = new Color(Math.random(), Math.random(), Math.random(), 1.0);

                // puis, on dessine la tâche
                int coordXTache = this.dessinerTache(gc, randomColor, coordYPinceau, largeurTache, tache.getDateDebut(), HAUTEUR_TACHE, LARGEUR_JOUR);
                // On indique que la tâche a bien été dessinée la stockant dans la HashMap avec les coordonnées du milieu de l'arrête gauche pour la flèche
                tacheCoordPourFleche.put(tache, new Point(coordXTache, coordYPinceau+HAUTEUR_TACHE/2));

                // On va dessiner chaque tâche dépendante et chacune de ses dépendances
                while(!tache.getDependances().isEmpty()) {
                    for (TachePrincipale tacheDependante : tache.getDependances()) {
                        // On stocke la largeur de la tache antécédente pour dessiner la flèche à la bonne position
                        int largeurTacheAntecedent = largeurTache;
                        // Si la tache dépendante n'a pas déjà été dessinée, on dessine la tache
                        if (!tacheCoordPourFleche.containsKey(tacheDependante)) {
                            coordYPinceau += hauteurTache + 20;
                            largeurTache = tacheDependante.getDuree() * largeurJour;
                            this.dessinerTitreTache(gc, tacheDependante.getTitre(), coordYPinceau, HAUTEUR_TACHE);
                            coordXTache = this.dessinerTache(gc, randomColor, coordYPinceau, largeurTache, tacheDependante.getDateDebut(), HAUTEUR_TACHE, LARGEUR_JOUR);
                            tacheCoordPourFleche.put(tacheDependante, new Point(coordXTache, coordYPinceau + HAUTEUR_TACHE / 2));
                        }
                        // On dessine la flèche
                        Point pDepart = tacheCoordPourFleche.get(tache);
                        Point pArrivee = tacheCoordPourFleche.get(tacheDependante);
                        this.dessinerFleche(gc, pDepart, pArrivee, largeurTacheAntecedent);
                    }
                    tache = tache.getDependances().get(0);

                }

                // On décale le pinceau en vertical
                coordYPinceau += hauteurTache + 20;
            }

            // On dessine aussi l'axe de temporalité
            this.dessinerAxeDates(gc);
        }
    }

    private int dessinerTache(GraphicsContext gc, Color color, int coordYPinceau, int largeurTache, LocalDate dateDebut, int hauteurTache, int largeurJour){

        // Espace de 200 pixels pour commencer à dessiner les taches (décalage par rapport aux titres de tâche)
        int coordXPinceau = ORIGIN_X + 200;

        // Calcul du décalage en jours entre la date de début de la tâche et la date de début du calendrier
        long decalageJours = ChronoUnit.DAYS.between(dateDebutCalendrier, dateDebut);
        // Calcul de la position en X du pinceau pour dessiner la tâche
        coordXPinceau += decalageJours * largeurJour;

        // Dessin de la tâche
        gc.setFill(color);
        gc.fillRect(coordXPinceau, coordYPinceau, largeurTache, hauteurTache);

        return coordXPinceau;
    }

    private void dessinerTitreTache(GraphicsContext gc, String titre, int coordYPinceau, int hauteurTache){
        gc.setFill(Color.BLACK);

        // Si le titre est trop long, on le tronque
        if (titre.length() > 20) {
            titre = titre.substring(0, 20) + "...";
        }
        // Calcul de la hauteur du texte pour le centrage par rapport à la tâche
        Text text = new Text(titre);
        double height = text.getLayoutBounds().getHeight();
        // Centrer le texte verticalement par rapport à la tache
        double textY = coordYPinceau + (double) hauteurTache / 2 + height / 2;
        gc.fillText(titre, ORIGIN_X, textY);
    }

    /**
     * Dessine une flèche entre deux tâches
     * @param gc GraphicsContext pour dessiner
     * @param pAntecedent Point X,Y du milieu de l'arête gauche de la tâche antécédente
     * @param pTache Point X,Y du milieu de l'arête gauche de la tâche dépendante de la tâche antécédente
     * @param largeurTacheAntecedent largeur de la tâche antécédente
     */
    private void dessinerFleche(GraphicsContext gc, Point pAntecedent, Point pTache, int largeurTacheAntecedent){
        gc.setStroke(Color.GREEN);
        gc.setLineWidth(4); // Taille de la ligne

        // On ajoute la largeur de la tache antécédente aux coordonnées pour partir de la droite de la tâche et pas de la gauche.
        gc.strokeLine(pAntecedent.getX()+largeurTacheAntecedent, pAntecedent.getY(), pTache.getX(), pTache.getY());

        // Dessin la pointe de la flèche
        double size = 5.0; // Taille de la pointe ici
        double endX = pTache.getX();
        double endY = pTache.getY();
        gc.strokeLine(endX, endY, endX + size, endY);
        gc.strokeLine(endX, endY - size, endX + size, endY);
        gc.strokeLine(endX, endY + size, endX + size, endY);
    }

    /**
     * Dessine l'axe des dates
     * @param gc GraphicsContext pour dessiner
     */
    private void dessinerAxeDates(GraphicsContext gc){
        int coordXPinceau = ORIGIN_X + 200; // décalage initial de 200 pixels
        int coordYPinceau = ORIGIN_Y; // position de l'axe des dates
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
            coordXPinceau += LARGEUR_JOUR;
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