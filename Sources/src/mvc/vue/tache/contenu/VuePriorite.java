package mvc.vue.tache.contenu;

import javafx.scene.control.TextField;
import mvc.modele.Sujet;
import mvc.vue.Observateur;
import mvc.vue.tache.VueTache;

/**
 * Classe de la vue représentant la priorité d'une tâche
 */
public class VuePriorite extends TextField implements Observateur {
    /**
     * Constructeur de la classe VuePriorite
     */
    public VuePriorite() {
        this.setEditable(false);
    }

    /**
     * actualise la vue courante en fonction de la priorité de la tâche
     * @param sujet le modèle à partir duquel la vue est actualisée
     */
    @Override
    public void actualiser(Sujet sujet) {
        VueTache vue = (VueTache) sujet;
        switch(vue.getTache().getPriorite()) {
            case FAIBLE:
                this.setText("Priorité : Faible");
                this.setStyle("-fx-background-color: red;");
                break;
            case MOYENNE:
                this.setText("Priorité : Moyenne");
                this.setStyle("-fx-background-color: orange;");
                break;
            case ELEVEE:
                this.setText("Priorité : Elevée");
                this.setStyle("-fx-background-color: yellow;");
                break;
            default:
                this.setText("Priorité : A définir");
                this.setStyle("-fx-background-color: gray;");
        }
    }
}
