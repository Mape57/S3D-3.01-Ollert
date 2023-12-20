package mvc.vue.tache.contenu;

import mvc.modele.Sujet;
import mvc.vue.Observateur;
import mvc.vue.tache.VueTache;

import javafx.scene.control.TextField;

public class VueTitreTache extends TextField implements Observateur {
	@Override
	public void actualiser(Sujet sujet) {
		VueTache vue = (VueTache) sujet;
		this.setText(vue.getTache().getTitre());
	}
}
