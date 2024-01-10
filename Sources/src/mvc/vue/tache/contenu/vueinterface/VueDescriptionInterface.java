package mvc.vue.tache.contenu.vueinterface;

import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import mvc.controleur.tache.interfac.ControleurDescription;
import mvc.modele.ModeleOllert;
import mvc.modele.Sujet;
import mvc.vue.Observateur;
import ollert.tache.TachePrincipale;

public class VueDescriptionInterface extends TextArea implements Observateur {

    public VueDescriptionInterface(){
        this.setHeight(20);
        this.setWidth(40);
        this.setFont(new Font("Arial", 15));
        this.setWrapText(true);
    }

    @Override
    public void actualiser(Sujet sujet) {
        ModeleOllert modele = (ModeleOllert) sujet;
        TachePrincipale tache = (TachePrincipale) modele.getTacheEnGrand();
        this.setText(tache.getDescription());
        this.textProperty().addListener(new ControleurDescription(modele));
    }
}
