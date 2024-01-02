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
		if (dragEvent.getY() > vueTache.getHeight() -1) {
			System.out.println("bas");
			modele.deplacerTache(vueTache.getTache(), vueListe.getListe(), 0, 1);
		} else if (dragEvent.getX() > vueTache.getWidth() - 1) {
			System.out.println("droite");
			modele.deplacerTache(vueTache.getTache(), vueListe.getListe(), 1, 0);
		}
	}
}
