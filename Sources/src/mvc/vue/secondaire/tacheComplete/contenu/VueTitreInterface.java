package mvc.vue.secondaire.tacheComplete.contenu;

import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import mvc.controleur.tacheComplete.ControleurTitre;
import mvc.modele.ModeleOllert;
import mvc.modele.Sujet;
import mvc.vue.structure.Observateur;
import ollert.donnee.tache.Tache;

public class VueTitreInterface extends TextField implements Observateur {

    public VueTitreInterface(){
        this.setHeight(20);
        this.setWidth(40);
        this.setFont(new Font("Arial", 15));
    }

    @Override
    public void actualiser(Sujet sujet) {
        ModeleOllert modele = (ModeleOllert) sujet;
        Tache<?> tache = modele.getTacheComplete();
        this.setText(tache.getTitre());
        this.textProperty().addListener(new ControleurTitre(modele));
    }
}
