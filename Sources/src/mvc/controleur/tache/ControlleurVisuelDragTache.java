package mvc.controleur.tache;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.input.*;
import mvc.modele.ModeleOllert;
import mvc.vue.tache.VueTache;
import ollert.tache.TachePrincipale;

public class ControlleurVisuelDragTache implements EventHandler<MouseEvent> {
	private ModeleOllert modele;

	public ControlleurVisuelDragTache(ModeleOllert modele) {
		this.modele = modele;
	}

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
