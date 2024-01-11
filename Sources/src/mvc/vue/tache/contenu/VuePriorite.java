package mvc.vue.tache.contenu;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import mvc.modele.ModeleOllert;
import mvc.modele.Sujet;
import mvc.vue.Observateur;
import mvc.vue.VuePrincipale;
import ollert.tache.Tache;

/**
 * Classe de la vue représentant la priorité d'une tâche
 */
public class VuePriorite extends Label implements Observateur {
    /**
     * Constructeur de la classe VuePriorite
     */
    public VuePriorite() {
        this.setFont(new Font("Arial", 15));
        this.setPadding(new Insets(5,5,5,5));
    }
    /**
     * actualise la vue courante en fonction de la priorité de la tâche
     * @param sujet le modèle à partir duquel la vue est actualisée
     */
    @Override
    public void actualiser(Sujet sujet) {
        ModeleOllert modele = (ModeleOllert) sujet;
        VuePrincipale vueTache = (VuePrincipale) this.getParent();
        Tache<?> tache = modele.getTache(vueTache.getLocalisation());
        switch(tache.getPriorite()) {
            case FAIBLE:
                this.setText("Priorité : Faible");
                this.setStyle("-fx-background-color: yellow;");
                break;
            case MOYENNE:
                this.setText("Priorité : Moyenne");
                this.setStyle("-fx-background-color: orange;");
                break;
            case ELEVEE:
                this.setText("Priorité : Elevée");
                this.setStyle("-fx-background-color: red;");
                break;
            default:
                this.setText("Priorité : A définir");
                this.setStyle("-fx-background-color: gray;");
        }
    }
}
