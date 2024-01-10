package mvc.controleur.tache;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import mvc.modele.ModeleOllert;
import mvc.vue.tache.VueTache;
import ollert.tache.TachePrincipale;

/**
 * Contrôleur pour le visuel du drag d'une tâche (barre qui s'affiche lors du survol pour montrer la destination théorique à l'instant T du glisser)
 */
public class ControleurVisuelDragTache implements EventHandler<MouseEvent> {
	/**
	 * Modele de l'application
	 */
	private final ModeleOllert modele;

	/**
	 * Constructeur du contrôleur
	 * @param modele Modele de l'application
	 */
	public ControleurVisuelDragTache(ModeleOllert modele) {
		this.modele = modele;
	}

	/**
	 * Gère l'animation de l'avant drop d'une tâche
	 * @param mouseEvent Événement de souris (clic)
	 */
	@Override
	public void handle(MouseEvent mouseEvent) {
		Node node = (Node) mouseEvent.getSource();
		Dragboard db = node.startDragAndDrop(TransferMode.MOVE);
		ClipboardContent content = new ClipboardContent();
		content.putImage(node.snapshot(new SnapshotParameters(), null));
		db.setContent(content);
		mouseEvent.consume();

		this.modele.setDraggedTache((TachePrincipale) modele.getTache(((VueTache) node).getLocalisation()));
	}
}
