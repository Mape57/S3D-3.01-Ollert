package mvc.controleur.dragdrop.liste;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.HBox;
import mvc.modele.ModeleOllert;
import mvc.vue.principale.tableau.VueListeTableau;
import mvc.vue.principale.tableau.VuePageTableau;
import ollert.donnee.ListeTaches;

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
		VuePageTableau vuePage = (VuePageTableau) dragEvent.getSource();
		HBox page = vuePage.getChildrenPrincipale();

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
