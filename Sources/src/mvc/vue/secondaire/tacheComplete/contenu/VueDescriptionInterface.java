package mvc.vue.secondaire.tacheComplete.contenu;

import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import mvc.controleur.tacheComplete.ControleurDescription;
import mvc.modele.ModeleOllert;
import mvc.modele.Sujet;
import mvc.vue.structure.Observateur;
import ollert.donnee.tache.TacheAbstraite;

public class VueDescriptionInterface extends TextArea implements Observateur {

    public VueDescriptionInterface(){
        this.setHeight(20);
        this.setWidth(40);
        this.setFont(new Font("Arial", 15));
        this.setWrapText(true);
        this.setMaxWidth(600);
    }

    @Override
    public void actualiser(Sujet sujet) {
        ModeleOllert modele = (ModeleOllert) sujet;
        TacheAbstraite<?> tache = modele.getTacheEnGrand();
        this.setText(tache.getDescription());
        this.textProperty().addListener(new ControleurDescription(modele));
    }
}
