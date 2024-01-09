package mvc.controleur.liste;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.VBox;
import mvc.modele.ModeleOllert;
import mvc.vue.liste.VueListe;
import mvc.vue.liste.VueListeTableau;
import mvc.vue.tache.VueTacheTableau;
import ollert.tache.ListeTaches;
import ollert.tache.Tache;

import java.util.List;

public class ControlleurDragTache implements EventHandler<DragEvent> {
	private ModeleOllert modele;
	public ControlleurDragTache(ModeleOllert modeleControle) {
		this.modele = modeleControle;
	}

	@Override
	public void handle(DragEvent dragEvent) {
		ScrollPane scrollPane = (ScrollPane) dragEvent.getSource();
		VBox listeVueTaches = (VBox) scrollPane.getContent();

		if (listeVueTaches.getChildren().isEmpty()) {
			int indice = ((VueListe) scrollPane.getParent()).getLocalisation().get(0);

			ListeTaches liste = this.modele.getDonnee().getListeTaches(indice);
			modele.deplacerTache(liste, null);
			return;
		}

		for (int i = 0; i < listeVueTaches.getChildren().size(); i++) {
			VueTacheTableau vueTache = (VueTacheTableau) listeVueTaches.getChildren().get(i);

			// TODO : deplacement en fonction du scroll
			//ScrollPane scrollPane = (ScrollPane) listeVueTaches.getProperties().get("scrollPane");
			// double scrolledHeight = scrollPane.getVvalue() * (scrollPane.getContent().getBoundsInLocal().getHeight() - scrollPane.getViewportBounds().getHeight());


			// assure le deplacement uniquement si la souris depasse la tache
			// - evite un va et vient en cas de taille differente : min choisi entre les deux
			double height = vueTache.getHeight();
			if (i + 1 < listeVueTaches.getChildren().size())
				height = Math.min(((VueTacheTableau) listeVueTaches.getChildren().get(i + 1)).getHeight(), height);


			if (vueTache.getLayoutY() + height > dragEvent.getY()) {
				List<Integer> indices = vueTache.getLocalisation();
				Tache<?> tache = this.modele.getTache(indices);
				ListeTaches liste = this.modele.getDonnee().getListeTaches(indices.get(0));
				// on deplace si la tache n'est pas celle qui est drag
				if (tache != modele.getDragged()) {
					modele.deplacerTache(liste, tache);
				}
				break;
			}
		}
	}
}
