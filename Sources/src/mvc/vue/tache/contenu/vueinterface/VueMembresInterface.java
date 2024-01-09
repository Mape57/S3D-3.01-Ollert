package mvc.vue.tache.contenu.vueinterface;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import mvc.controleur.tache.interfac.ControlleurAjoutMembre;
import mvc.controleur.tache.interfac.ControlleurSupprMembre;
import mvc.modele.ModeleOllert;
import mvc.modele.Sujet;
import mvc.vue.Observateur;
import mvc.vue.tache.VueTacheInterface;
import ollert.tache.TachePrincipale;
import ollert.tache.donneesTache.Etiquette;
import ollert.tache.donneesTache.Utilisateur;

import java.util.ArrayList;
import java.util.List;

public class VueMembresInterface extends GridPane implements Observateur {

    public VueMembresInterface(){
        Label membres = new Label("Membres participants");
        Button supprMembre = new Button("Supprimer");
        Button ajoutMembre = new Button("Ajouter");
        this.add(membres, 0, 0);
        this.add(ajoutMembre, 9, 0);
        this.add(supprMembre, 10, 0);
    }

    @Override
    public void actualiser(Sujet sujet) {
        ModeleOllert modele = (ModeleOllert) sujet;
        VueTacheInterface vueTacheInterface = (VueTacheInterface) this.getParent();
        TachePrincipale tache = (TachePrincipale) modele.getTache(vueTacheInterface.getLocalisation());
        int x = 0;
        int y = 1;
        List<Label> list = new ArrayList<>();
        for (Etiquette u : tache.getTags()) {
            if (x==10){
                x=0;
                y++;
                Label label = new Label(u.getValeur());
                this.add(label, x, y);
                list.add(label);
            }
            else {
                Label label = new Label(u.getValeur());
                this.add(label, x, y);
                list.add(label);
                x++;
            }
        }
        ((Button)this.getChildren().get(1)).setOnAction(new ControlleurAjoutMembre(modele));
        ((Button)this.getChildren().get(2)).setOnAction(new ControlleurSupprMembre(modele, list));
    }
}
