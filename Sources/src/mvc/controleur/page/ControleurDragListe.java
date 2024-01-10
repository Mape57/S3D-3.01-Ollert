package mvc.controleur.page;

import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.HBox;
import mvc.modele.ModeleOllert;
import mvc.vue.liste.VueListeTableau;
import ollert.tache.ListeTaches;

/**
 * Contrôleur du drag/glisser (premier click maintenu) de liste
 */
public class ControleurDragListe implements EventHandler<DragEvent> {
	/**
	 * Modele de l'application
	 */
	private final ModeleOllert modele;

	/**
	 * Constructeur du contrôleur
	 * @param modeleControle Modele de l'application
	 */
	public ControleurDragListe(ModeleOllert modeleControle) {
		this.modele = modeleControle;
	}

	/**
	 * Gère le drag d'une liste
	 * @param dragEvent Action de drag/glisser
	 */
	@Override
	public void handle(DragEvent dragEvent) {
		if (modele.getDraggedTache() != null) return;

		ScrollPane scrollPane = (ScrollPane) dragEvent.getSource();
		HBox page = (HBox) scrollPane.getContent();

		for (int i = 0; i < page.getChildren().size(); i++) {
			VueListeTableau vueListe = (VueListeTableau) page.getChildren().get(i);

			// TODO : deplacement en fonction du scroll
			// ScrollPane scrollPane = (ScrollPane) listeVueTaches.getProperties().get("scrollPane");
			// double scrolledHeight = scrollPane.getVvalue() * (scrollPane.getContent().getBoundsInLocal().getHeight() - scrollPane.getViewportBounds().getHeight());

			if (vueListe.getLayoutX() + vueListe.getWidth() > dragEvent.getX()) {
				int indices = vueListe.getLocalisation().get(0);
				ListeTaches liste = this.modele.getDonnee().getListeTaches(indices);
				// on déplace si la liste n'est pas celle qui est drag
				if (liste != modele.getDraggedListe()) {
					modele.deplacerListeDraggedAvant(liste);
				}
				break;
			}
		}
	}
}
