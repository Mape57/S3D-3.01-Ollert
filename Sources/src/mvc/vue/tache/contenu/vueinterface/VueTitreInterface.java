package mvc.vue.tache.contenu.vueinterface;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import mvc.controleur.tache.interfac.ControlleurTitre;
import mvc.modele.ModeleOllert;
import mvc.modele.Sujet;
import mvc.vue.Observateur;
import mvc.vue.tache.VueTache;
import mvc.vue.tache.VueTacheInterface;
import ollert.tache.TachePrincipale;

public class VueTitreInterface extends TextField implements Observateur {

    public VueTitreInterface(){
        this.setHeight(20);
        this.setWidth(40);
        this.setFont(new Font("Arial", 15));
    }

    @Override
    public void actualiser(Sujet sujet) {
        ModeleOllert modele = (ModeleOllert) sujet;
        TachePrincipale tache = (TachePrincipale) modele.getTacheEnGrand();
        this.setText(tache.getTitre());
        this.textProperty().addListener(new ControlleurTitre(modele));
    }
}
