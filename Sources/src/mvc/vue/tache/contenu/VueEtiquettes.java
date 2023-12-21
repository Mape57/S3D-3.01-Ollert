package mvc.vue.tache.contenu;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import mvc.modele.Sujet;
import mvc.vue.Observateur;
import mvc.vue.tache.VueTache;
import ollert.tache.donneesTache.Etiquette;

/**
 * VueEtiquettes représente la vue de la HBox avec toutes les étiquettes (Labels) relatives à une tâche
 */
public class VueEtiquettes extends HBox implements Observateur {
    /**
     * Constructeur de la classe VueEtiquettes
     */
    public VueEtiquettes() {
        this.setSpacing(5);
    }

    /**
     * Actualise la vue courante
     * @param sujet le modèle à partir duquel la vue est actualisée
     */
    @Override
    public void actualiser(Sujet sujet) {
        VueTache vueTache = (VueTache) sujet;
        // Réinitialise la vue à zéro (supprimer les étiquettes de l'affichage) avant de réafficher les nouvelles étiquettes
        this.getChildren().clear();
        for(Etiquette etiquette : vueTache.getTache().getTags()){
            Label tag = new Label(etiquette.getValeur());
            tag.setFont(new Font("Arial", 15));
            tag.setPadding(new Insets(5,5,5,5));
            tag.setStyle("-fx-background-color: purple;");
            tag.autosize();
            this.getChildren().add(tag);
        }
    }
}
