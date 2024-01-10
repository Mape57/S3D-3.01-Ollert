package ollert;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Pair;
import ollert.tache.TachePrincipale;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class DiagGantt extends Canvas {

    private LocalDate dateDebutCalendrier;
    private LocalDate dateFinCalendrier;
    private final int ORIGIN_X = 20;
    private final int ORIGIN_Y = 40;
    private final int HAUTEUR_TACHE = 20;
    private final int LARGEUR_JOUR = 30;

    public DiagGantt(){
        super();
        this.getGraphicsContext2D().setFill(Color.WHITE);
    }

    public DiagGantt(double width, double height, Color backgroundColor){
        super(width, height);
        this.getGraphicsContext2D().setFill(backgroundColor);
    }

    /**
     * Dessine le diagramme de Gantt
     * @param gc GraphicsContext
     * @param tachesPrincipalesSansAntecedent liste des tâches principales sans antecedent triées par date de début
     */
    public void draw(GraphicsContext gc, ArrayList<TachePrincipale> tachesPrincipalesSansAntecedent){

        // Suppression de l'affichage d'avant (éviter de garder des tâches supprimées)
        gc.clearRect(0, 0, this.getWidth(), this.getHeight());


        // Si aucune tache n'a de date de début et de fin, on affiche un message d'erreur
        if(tachesPrincipalesSansAntecedent.isEmpty()){
            System.out.println("PAS DE TACHE AVEC UNE DATE DEBUT ET DE FIN");
            gc.setFill(Color.BLACK);
            gc.fillText("Graphique indisponible. Aucune tâche avec une date de début et de fin", 10, 315);
        }
        // Sinon, on dessine le diagramme de Gantt
        else {
            /*
               On stocke les tâches déjà dessinées avec les coordonnées du milieu de l'arête gauche pour pouvoir dessiner les flèches
               On sait donc s'il faut dessiner une tâche ou si elle est déjà présente.
               Si elle est déjà présente, il faut dessiner la flèche dont on a la destination (les coordonnées du milieu de l'arête gauche de la tâche)
             */
            HashMap<TachePrincipale, Point> tacheCoordPourFleche = new HashMap<>();
            int coordYPinceau = ORIGIN_Y + 20; // Gère la position verticale du pinceau pour dessiner les tâches
            int largeurTache;
            int hauteurTache = HAUTEUR_TACHE;
            int largeurJour = LARGEUR_JOUR;

            // On dessine les tâches principales sans antécédents dont les tâches dépendantes vont découler
            for (TachePrincipale tache : tachesPrincipalesSansAntecedent) {

                // On dessine le titre de la tâche
                this.dessinerTitreTache(gc, tache.getTitre(), coordYPinceau);

                // On calcule la largeur de la tache
                largeurTache = tache.getDuree() * largeurJour;
                // On choisit une couleur aléatoire
                Color randomColor = new Color(Math.random(), Math.random(), Math.random(), 1.0);

                // puis, on dessine la tâche
                int coordXTache = this.dessinerTache(gc, randomColor, coordYPinceau, largeurTache, tache.getDateDebut());
                // On indique que la tâche a bien été dessinée la stockant dans la HashMap avec les coordonnées du milieu de l'arête gauche pour la flèche
                tacheCoordPourFleche.put(tache, new Point(coordXTache, coordYPinceau+HAUTEUR_TACHE/2));

                // Créer une liste pour stocker toutes les tâches et leurs dépendances
                ArrayList<Pair<TachePrincipale, TachePrincipale>> dependances = new ArrayList<>();

                while(!tache.getDependances().isEmpty()) {
                    for (TachePrincipale tacheDependante : tache.getDependances()) {
                        int largeurTacheAntecedent = largeurTache;
                        if (!tacheCoordPourFleche.containsKey(tacheDependante)) {
                            coordYPinceau += hauteurTache + 20;
                            largeurTache = tacheDependante.getDuree() * largeurJour;
                            this.dessinerTitreTache(gc, tacheDependante.getTitre(), coordYPinceau);
                            coordXTache = this.dessinerTache(gc, randomColor, coordYPinceau, largeurTache, tacheDependante.getDateDebut());
                            tacheCoordPourFleche.put(tacheDependante, new Point(coordXTache, coordYPinceau + HAUTEUR_TACHE / 2));
                        }
                        // Ajouter la paire de tâches à la liste des dépendances
                        dependances.add(new Pair<>(tache, tacheDependante));
                    }
                    tache = tache.getDependances().get(0);
                }

                // Dessiner les flèches pour toutes les dépendances
                for (Pair<TachePrincipale, TachePrincipale> dependance : dependances) {
                    tache = dependance.getKey();
                    TachePrincipale tacheDependante = dependance.getValue();
                    Point pDepart = tacheCoordPourFleche.get(tache);
                    Point pArrivee = tacheCoordPourFleche.get(tacheDependante);
                    this.dessinerFleche(gc, pDepart, pArrivee, tache.getDuree() * largeurJour);
                }

                // On décale le pinceau en vertical
                coordYPinceau += hauteurTache + 20;
            }

            // On dessine aussi l'axe de temporalité
            this.dessinerAxeDates(gc);
        }
    }

    private int dessinerTache(GraphicsContext gc, Color color, int coordYPinceau, int largeurTache, LocalDate dateDebut){

        // Espace de 200 pixels pour commencer à dessiner les taches (décalage par rapport aux titres de tâche)
        int coordXPinceau = ORIGIN_X + 200;

        // Calcul du décalage en jours entre la date de début de la tâche et la date de début du calendrier
        int decalageJours = (int) ChronoUnit.DAYS.between(dateDebutCalendrier, dateDebut);
        // Calcul de la position en X du pinceau pour dessiner la tâche
        coordXPinceau += decalageJours * LARGEUR_JOUR;

        // Dessin de la tâche
        gc.setFill(color);
        gc.fillRect(coordXPinceau, coordYPinceau, largeurTache, HAUTEUR_TACHE);

        return coordXPinceau;
    }

    private void dessinerTitreTache(GraphicsContext gc, String titre, int coordYPinceau){
        gc.setFill(Color.BLACK);

        // Si le titre est trop long, on le tronque
        if (titre.length() > 20) {
            titre = titre.substring(0, 20) + "...";
        }
        // Calcul de la hauteur du texte pour le centrage par rapport à la tâche
        Text text = new Text(titre);
        double height = text.getLayoutBounds().getHeight();
        // Centrer le texte verticalement par rapport à la tâche
        double textY = coordYPinceau + (double) HAUTEUR_TACHE / 2 + height / 2;
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

    public int getLargeur(){
        return (int) ChronoUnit.DAYS.between(this.getDateDebutCalendrier(), this.getDateFinCalendrier()) * (LARGEUR_JOUR+1) + ORIGIN_X + 200;
    }

    public int getHauteur(int nbTaches){
        return 1000;
    }

    public LocalDate getDateDebutCalendrier() {
        return dateDebutCalendrier;
    }

    public LocalDate getDateFinCalendrier() {
        return dateFinCalendrier;
    }

    public void setDateDebutCalendrier(LocalDate dateDebutCalendrier) {
        this.dateDebutCalendrier = dateDebutCalendrier;
    }

    public void setDateFinCalendrier(LocalDate dateFinCalendrier) {
        this.dateFinCalendrier = dateFinCalendrier;
    }
}
