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


public class ControlleurVisuelDragTache implements EventHandler<MouseEvent> {
	/**
	 * Modele de l'application
	 */
	private ModeleOllert modele;

	/**
	 * Constructeur du contrôleur
	 * @param modele Modele de l'application
	 */
	public ControlleurVisuelDragTache(ModeleOllert modele) {
		this.modele = modele;
	}

	/**
	 * @param mouseEvent Événement de souris
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
