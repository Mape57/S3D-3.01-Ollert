package mvc.controleur.tache;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import mvc.modele.ModeleOllert;
import mvc.vue.liste.VueListeTableau;
import mvc.vue.tache.VueTacheTableau;

public class ControlleurDragOutside implements EventHandler<DragEvent> {
	private ModeleOllert modele;
	public ControlleurDragOutside(ModeleOllert modele) {
		this.modele = modele;
	}

	@Override
	public void handle(DragEvent dragEvent) {
		VueTacheTableau vueTache = (VueTacheTableau) dragEvent.getSource();
		VueListeTableau vueListe = (VueListeTableau) vueTache.getParent();
		// if cursor is under the target

		if (dragEvent.getY() > vueTache.getHeight() -1) {
			modele.deplacerTache(vueTache.getTache(), vueListe.getListe(), 1);
		}
	}
}
