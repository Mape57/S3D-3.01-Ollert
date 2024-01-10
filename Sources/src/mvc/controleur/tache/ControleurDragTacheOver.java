package mvc.controleur.tache;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import mvc.modele.ModeleOllert;

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
		modeleControle.setDraggedTache(null);
	}
}
