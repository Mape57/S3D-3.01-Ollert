package mvc.vue.tache.contenu;

import javafx.scene.control.TextField;
import mvc.modele.Sujet;
import mvc.vue.Observateur;
import mvc.vue.tache.VueTache;

public class VueTitreTache extends TextField implements Observateur {
	@Override
	public void actualiser(Sujet sujet) {
		VueTache vue = (VueTache) sujet;
		this.setText(vue.getTache().getTitre());
	}
}
