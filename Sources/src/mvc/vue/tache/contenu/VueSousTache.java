package mvc.vue.tache.contenu;

import javafx.scene.layout.VBox;
import mvc.modele.ModeleOllert;
import mvc.modele.Sujet;
import mvc.vue.Observateur;
import mvc.vue.tache.VueTache;
import mvc.vue.tache.VueTacheTableau;
import ollert.tache.SousTache;
import ollert.tache.Tache;

public class VueSousTache extends VBox implements Observateur {
	@Override
	public void actualiser(Sujet sujet) {
		ModeleOllert modele = (ModeleOllert) sujet;
		VueTache vueTache = (VueTache) this.getParent();

		Tache<?> tache = modele.getTache(vueTache.getLocalisation());
		this.getChildren().clear();
		for (SousTache st : tache.getSousTaches()) {
			VueTacheTableau vue = new VueTacheTableau(modele);
			this.getChildren().add(vue);
			vue.actualiser(modele);
		}
	}
}
