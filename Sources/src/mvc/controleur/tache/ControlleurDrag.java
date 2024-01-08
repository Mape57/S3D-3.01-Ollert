package mvc.controleur.tache;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.input.*;
import mvc.modele.ModeleOllert;
import mvc.vue.tache.VueTache;

public class ControlleurDrag implements EventHandler<MouseEvent> {
	private ModeleOllert modele;

	public ControlleurDrag(ModeleOllert modele) {
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

		this.modele.setDragged((VueTache) node);
	}
}
