package mvc.vue.liste.contenu;

import javafx.scene.control.TextField;
import mvc.Sujet;
import mvc.vue.Observateur;
import mvc.vue.liste.VueListe;

public class VueTitreListe extends TextField implements Observateur {
	@Override
	public void actualiser(Sujet sujet) {
		VueListe vue = (VueListe) sujet;
		this.setText(vue.getListe().getTitre());
	}
}
