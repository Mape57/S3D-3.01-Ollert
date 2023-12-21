package mvc.vue.tache.contenu;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mvc.modele.Sujet;
import mvc.vue.Observateur;
import javafx.scene.control.Button;

/**
 * VueAjouterSousTache représente la vue du bouton pour ajouter une sous tâche
 */
public class VueAjouterSousTache extends Button implements Observateur {
    /**
     * Constructeur de la classe VueAjouterSousTache
     * Charge l'icône ajouter dans les ressources pour la façade du bouton
     */
    public VueAjouterSousTache(){
        Image image = new Image("file:Sources/ressource/images/icones/ajouter.png");
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
