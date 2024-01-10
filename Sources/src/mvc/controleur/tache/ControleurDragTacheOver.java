package mvc.controleur.tache;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import mvc.modele.ModeleOllert;
import mvc.vue.tache.VueTache;
import mvc.vue.tache.VueTacheTableau;
import mvc.vue.tache.VueTacheTableauAbstraite;

/**
 * Contrôleur pour le relâchement du drag d'une tache
 */
public class ControleurDragTacheOver implements EventHandler<DragEvent> {
	/**
	 * Modele de l'application
	 */
	private final ModeleOllert modeleControle;

	/**
	 * Constructeur du contrôleur
	 * @param modeleControle Modele de l'application
	 */
	public ControleurDragTacheOver(ModeleOllert modeleControle) {
		this.modeleControle = modeleControle;
	}

	/**
	 * Gère le relâchement du drag d'une tâche
	 * @param mouseEvent Événement de souris (déposer = relâchement du clic)
	 */
	@Override
	public void handle(DragEvent mouseEvent) {
		((VueTacheTableauAbstraite) mouseEvent.getSource()).setStyle("-fx-background-color: #e2e2e2; -fx-border-color: black; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-padding: 5px;");
		if (modeleControle.getDraggedTache() == null || modeleControle.getIndicesDragged() == null) return;

		if (modeleControle.getIndicesDragged().size() > 2)
			modeleControle.deplacerDraggedVersSousTache();
		else
			modeleControle.deplacerDraggedVersTache();
	}
}
