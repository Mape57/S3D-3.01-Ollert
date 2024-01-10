package mvc.controleur.liste;

import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.VBox;
import mvc.modele.ModeleOllert;
import mvc.vue.liste.VueListe;
import mvc.vue.tache.VueTacheTableauPrincipale;
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
		// TODO prevenir l'affichage du separateur autour de la tache draggee

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

			VueTacheTableauPrincipale vueTache = (VueTacheTableauPrincipale) listeVueTaches.getChildren().get(i);

			// on recupere la hauteur scroller (partie invisible au dessus de la zone des taches)
			double scrolledHeight = scrollPane.getVvalue() * (scrollPane.getContent().getBoundsInLocal().getHeight() - scrollPane.getViewportBounds().getHeight());
			if (vueTache.getLayoutY() + vueTache.getHeight() + 20 > dragEvent.getY() + scrolledHeight) {
				List<Integer> indices = vueTache.getLocalisation();

				if (separatorFound)
					indices.set(indices.size() - 1, indices.get(indices.size() - 1) - 1);

				Tache<?> tache = this.modele.getTache(indices);
				if (indices.get(indices.size() - 1) != 0) {
					List<Integer> indicePre = new ArrayList<>(indices);
					indicePre.set(indicePre.size() - 1, indicePre.get(indicePre.size() - 1) - 1);
				}

				// si on est au dessus de la tache deplassee ou la tache la suivant (ne pas afficher le separateur car il n'y pas de deplacement)
				if (tache == modele.getDraggedTache()) {
					modele.setIndicesDragged(null);
					return;
				}

				if (vueTache.getLayoutY() + vueTache.getHeight() * 3 / 4 + 20 < dragEvent.getY() + scrolledHeight) {
					indices.set(indices.size() - 1, indices.get(indices.size() - 1) + 1);
				} else if (vueTache.getLayoutY() + vueTache.getHeight() / 4 + 20 < dragEvent.getY() + scrolledHeight) {
					indices.add(0);
				}

				modele.setIndicesDragged(indices);
				break;
			}
		}
	}
}
