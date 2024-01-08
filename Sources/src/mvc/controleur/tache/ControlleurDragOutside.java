package mvc.controleur.tache;

import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.DragEvent;
import mvc.modele.ModeleOllert;
import mvc.vue.liste.VueListeTableau;
import mvc.vue.tache.VueTacheTableau;
import ollert.tache.ListeTaches;
import ollert.tache.TachePrincipale;

import java.util.List;

public class ControlleurDragOutside implements EventHandler<DragEvent> {
	private ModeleOllert modele;
	public ControlleurDragOutside(ModeleOllert modele) {
		this.modele = modele;
	}

	@Override
	public void handle(DragEvent dragEvent) {
		VueTacheTableau vueTache = (VueTacheTableau) dragEvent.getSource();
		VueListeTableau vueListe = (VueListeTableau) ((ScrollPane) vueTache.getParent().getProperties().get("scrollPane")).getParent();
		List<Integer> indicesListe = vueListe.getLocalisation();
		List<Integer> indicesTache = vueTache.getLocalisation();

		if (dragEvent.getY() > vueTache.getHeight() -1) {
			System.out.println("bas");
			modele.deplacerTache((TachePrincipale) this.modele.getParent(indicesTache), (ListeTaches) this.modele.getParent(indicesListe), 0, 1);
		} else if (dragEvent.getX() > vueTache.getWidth() - 1) {
			System.out.println("droite");
			modele.deplacerTache((TachePrincipale) this.modele.getParent(indicesTache), (ListeTaches) this.modele.getParent(indicesListe), 1, 0);
		}
	}
}
