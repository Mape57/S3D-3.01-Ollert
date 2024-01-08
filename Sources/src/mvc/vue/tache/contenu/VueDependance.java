package mvc.vue.tache.contenu;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mvc.modele.Sujet;
import mvc.vue.Observateur;
import javafx.scene.control.Button;
import mvc.vue.tache.VueTache;

/**
 * VueDepandance représente la vue du bouton pour faire dépendre une liste d'une autre
 */
public class VueDependance extends Button implements Observateur {
    /**
     * Constructeur de la classe VueDependance
     * Charge l'icone des dépendances (menottes) dans les ressources pour la façade du bouton
     */
    public VueDependance(){
        this.setText("d");
    }

    /**
     * Actualise la vue courante
     * @param sujet le modèle à partir duquel la vue est actualisée
     */
    @Override
    public void actualiser(Sujet sujet) {
        VueTache vueTache = (VueTache) sujet;
        Image image = null;

        // Change l'icone des dépendances en fonction de si la tâche a des dépendances ou non
        if(!vueTache.getTache().getDependances().isEmpty()) {
            image = new Image("file:Sources/ressource/images/icones/menottes-noires.png");
        } else {
            image = new Image("file:Sources/ressource/images/icones/menottes-blanches.png");
        }

        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(25);
        imageView.setFitHeight(25);
        this.setGraphic(imageView);
        this.setBackground(null);
    }
}
