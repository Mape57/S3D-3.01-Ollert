package mvc.vue.tache.contenu;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mvc.controleur.tache.ControleurAjouterSousTache;
import mvc.modele.Sujet;
import mvc.vue.Observateur;
import javafx.scene.control.Button;
import mvc.vue.tache.VueTache;

/**
 * VueAjouterSousTache représente la vue du bouton pour ajouter une sous tâche
 */
public class VueAjouterSousTache extends Button implements Observateur {
    /**
     * Constructeur de la classe VueAjouterSousTache
     * Charge l'icône ajouter dans les ressources pour la façade du bouton
     */
    public VueAjouterSousTache(){

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
        if(!vueTache.getTache().getSousTaches().isEmpty()) {
            image = new Image("file:Sources/ressource/images/icones/ajouter-noir.png");
        } else {
            image = new Image("file:Sources/ressource/images/icones/ajouter-blanc.png");
        }

        this.setBackground(null);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(25);
        imageView.setFitHeight(25);
        this.setGraphic(imageView);
        this.setOnAction(new ControleurAjouterSousTache(vueTache));
    }
}
