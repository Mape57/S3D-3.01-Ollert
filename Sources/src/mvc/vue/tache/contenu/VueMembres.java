package mvc.vue.tache.contenu;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import mvc.modele.ModeleOllert;
import mvc.modele.Sujet;
import mvc.vue.Observateur;
import mvc.vue.tache.VueTache;
import ollert.tache.Tache;
import ollert.tache.donneesTache.Utilisateur;

/**
 * VueMembres représente la vue de la HBox avec tous les icones des membres d'une tâche
 * (boutons pour plus tard peut-être visualiser les infos relatives d'un membre
 */
public class VueMembres extends HBox implements Observateur {
    /**
     * Constructeur de la classe VueMembres
     */
    public VueMembres(){
        this.setSpacing(5);
    }

    /**
     * Actualise la vue courante
     * @param sujet le modèle à partir duquel la vue est actualisée
     */
    @Override
    public void actualiser(Sujet sujet) {
        ModeleOllert modele = (ModeleOllert) sujet;
        VueTache vueTache = (VueTache) this.getParent();
        Tache<?> tache = modele.getTache(vueTache.getLocalisation());

        // Réinitialise la vue à zéro (supprimer les membres de l'affichage) avant de réafficher les nouveaux membres
        this.getChildren().clear();
        for(Utilisateur utilisateur : tache.getMembres()){
            Button membre = new Button();
            Image image = utilisateur.getIcone();
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(25);
            imageView.setFitHeight(25);
            membre.setBackground(null);
            membre.setGraphic(imageView);
            this.getChildren().add(membre);
        }
        this.setBackground(null);     
    }
}
