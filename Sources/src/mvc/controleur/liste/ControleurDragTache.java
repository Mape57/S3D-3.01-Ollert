package mvc.controleur.liste;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.VBox;
import mvc.modele.ModeleOllert;
import mvc.vue.liste.VueListe;
import mvc.vue.tache.VueTacheTableau;
import ollert.tache.ListeTaches;
import ollert.tache.Tache;

import java.util.ArrayList;
import java.util.List;

/**
 * Contrôleur de drag/glisser (premier click de souris maintenu) d'une tache
 */
public class ControleurDragTache implements EventHandler<DragEvent> {
	/**
	 * Modele de l'application
	 */
	private final ModeleOllert modele;

	/**
	 * Constructeur du contrôleur
	 * @param modeleControle Modele de l'application
	 */
	public ControleurDragTache(ModeleOllert modeleControle) {
		this.modele = modeleControle;
	}

	/**
	 * Gère le drag d'une tâche
	 * @param dragEvent Action de drag/glisser
	 */
	@Override
	public void handle(DragEvent dragEvent) {
		if (modele.getDraggedTache() == null) return;

		ScrollPane scrollPane = (ScrollPane) dragEvent.getSource();
		VBox listeVueTaches = (VBox) scrollPane.getContent();

		if (listeVueTaches.getChildren().isEmpty()) {
			List<Integer> indices = ((VueListe) scrollPane.getParent()).getLocalisation();
			indices.add(0);
			modele.setIndicesDragged(indices);
			return;
		}

		boolean separatorFound = false;
		for (int i = 0; i < listeVueTaches.getChildren().size(); i++) {
			// on evite le separateur (et le memorisons pour retirer le decalage qu'il provoque)
			if (listeVueTaches.getChildren().get(i) instanceof Separator) {
				separatorFound = true;
				continue;
			}

			VueTacheTableau vueTache = (VueTacheTableau) listeVueTaches.getChildren().get(i);

			// on recupere la hauteur scroller (partie invisible au dessus de la zone des taches)
			double scrolledHeight = scrollPane.getVvalue() * (scrollPane.getContent().getBoundsInLocal().getHeight() - scrollPane.getViewportBounds().getHeight());
			if (vueTache.getLayoutY() + vueTache.getHeight() / 2 > dragEvent.getY() + scrolledHeight) {
				List<Integer> indices = vueTache.getLocalisation();

				if (separatorFound)
					indices.set(indices.size() - 1, indices.get(indices.size() - 1) - 1);

				Tache<?> tache = this.modele.getTache(indices);
				Tache<?> tachePre = null;
				if (indices.get(indices.size() - 1) != 0) {
					List<Integer> indicePre = new ArrayList<>(indices);
					indicePre.set(indicePre.size() - 1, indicePre.get(indicePre.size() - 1) - 1);
					tachePre = this.modele.getTache(indicePre);
				}


				// on deplace si la tache n'est pas celle qui est drag
				if (tache != modele.getDraggedTache() && tachePre != modele.getDraggedTache())
					modele.setIndicesDragged(indices);
				else
					modele.setIndicesDragged(null);
				break;
			} else if (i == listeVueTaches.getChildren().size() - 1) {
				List<Integer> indices = vueTache.getLocalisation();
				indices.set(indices.size() - 1, indices.get(indices.size() - 1) + 1);
				modele.setIndicesDragged(indices);
				scrollPane.setVvalue(1);
				break;
			}
		}
	}
}
