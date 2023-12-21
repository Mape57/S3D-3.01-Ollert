package mvc.vue.tache.contenu;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mvc.modele.Sujet;
import mvc.vue.Observateur;
import javafx.scene.control.Button;

/**
 * VueModifTitre représente la vue du bouton de modification du titre d'une liste
 */
public class VueDeplacement extends Button implements Observateur {
    /**
     * Constructeur de la classe VueDeplacement
     * Charge l'icone de déplacement dans les ressources pour la façade du bouton
     */
    public VueDeplacement(){
        Image image = new Image("file:Sources/ressource/images/icones/deplacement.png");
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
