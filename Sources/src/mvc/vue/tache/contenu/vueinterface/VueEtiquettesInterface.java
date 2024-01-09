package mvc.vue.tache.contenu.vueinterface;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import mvc.controleur.tache.interfac.ControlleurAjoutEtiquette;
import mvc.controleur.tache.interfac.ControlleurSupprEtiquette;
import mvc.modele.ModeleOllert;
import mvc.modele.Sujet;
import mvc.vue.Observateur;
import mvc.vue.tache.VueTacheInterface;
import ollert.tache.TachePrincipale;
import ollert.tache.donneesTache.Utilisateur;

import java.util.ArrayList;
import java.util.List;

public class VueEtiquettesInterface extends GridPane implements Observateur {

    public VueEtiquettesInterface(){
        Label etiquettes = new Label("Etiquettes de la tâche");
        Button supprTag = new Button("Supprimer");
        Button ajoutTag = new Button("Ajouter");
        this.add(etiquettes, 0, 0);
        this.add(ajoutTag, 9, 0);
        this.add(supprTag, 10, 0);
    }

    @Override
    public void actualiser(Sujet sujet) {
        ModeleOllert modele = (ModeleOllert) sujet;
        TachePrincipale tache = (TachePrincipale) modele.getTacheEnGrand();
        int x = 0;
        int y = 1;
        List<Label> list = new ArrayList<>();
        for (Utilisateur u : tache.getMembres()) {
            if (x==10){
                x=0;
                y++;
                Label label = new Label(u.getPseudo());
                this.add(label, x, y);
                list.add(label);
            }
            else {
                Label label = new Label(u.getPseudo());
                this.add(label, x, y);
                list.add(label);
                x++;
            }
        }
        ((Button)this.getChildren().get(1)).setOnAction(new ControlleurAjoutEtiquette(modele));
        ((Button)this.getChildren().get(2)).setOnAction(new ControlleurSupprEtiquette(modele, list));
    }
}
