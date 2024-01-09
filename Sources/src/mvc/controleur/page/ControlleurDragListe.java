package mvc.controleur.page;

import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import mvc.modele.ModeleOllert;
import mvc.vue.liste.VueListe;
import mvc.vue.liste.VueListeTableau;
import mvc.vue.page.VuePageTableau;
import mvc.vue.tache.VueTacheTableau;
import ollert.tache.ListeTaches;
import ollert.tache.Tache;

import java.util.List;

public class ControlleurDragListe implements EventHandler<DragEvent> {
	private ModeleOllert modele;

	public ControlleurDragListe(ModeleOllert modeleControle) {
		this.modele = modeleControle;
	}

	@Override
	public void handle(DragEvent dragEvent) {
		if (modele.getDraggedTache() != null) return;

		ScrollPane scrollPane = (ScrollPane) dragEvent.getSource();
		HBox page = (HBox) scrollPane.getContent();

		for (int i = 0; i < page.getChildren().size(); i++) {
			VueListeTableau vueListe = (VueListeTableau) page.getChildren().get(i);

			// TODO : deplacement en fonction du scroll
			//ScrollPane scrollPane = (ScrollPane) listeVueTaches.getProperties().get("scrollPane");
			// double scrolledHeight = scrollPane.getVvalue() * (scrollPane.getContent().getBoundsInLocal().getHeight() - scrollPane.getViewportBounds().getHeight());

			if (vueListe.getLayoutX() + vueListe.getWidth() > dragEvent.getX()) {
				int indices = vueListe.getLocalisation().get(0);
				ListeTaches liste = this.modele.getDonnee().getListeTaches(indices);
				// on deplace si la liste n'est pas celle qui est drag
				if (liste != modele.getDraggedListe()) {
					modele.deplacerListeDraggedAvant(liste);
				}
				break;
			}
		}
	}
}
