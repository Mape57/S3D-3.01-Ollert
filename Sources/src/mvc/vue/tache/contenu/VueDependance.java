package mvc.vue.tache.contenu;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mvc.modele.Sujet;
import mvc.vue.Observateur;
import javafx.scene.control.Button;

/**
 * VueDepandance représente la vue du bouton pour faire dépendre une liste d'une autre
 */
public class VueDependance extends Button implements Observateur {
    /**
     * Constructeur de la classe VueDependance
     * Charge l'icone des dépendances (menottes) dans les ressources pour la façade du bouton
     */
    public VueDependance(){
        Image image = new Image("file:Sources/ressource/images/icones/menottes.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(25);
        imageView.setFitHeight(25);
        this.setBackground(null);
        this.setGraphic(imageView);
    }

    /**
     * Actualise la vue courante
     * @param sujet le modèle à partir duquel la vue est actualisée
     */
    @Override
    public void actualiser(Sujet sujet) {

    }
}
