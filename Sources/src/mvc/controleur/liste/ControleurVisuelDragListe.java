package mvc.controleur.liste;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import mvc.modele.ModeleOllert;
import mvc.vue.liste.VueListe;

public class ControleurVisuelDragListe implements EventHandler<MouseEvent> {
	private ModeleOllert modele;

	public ControleurVisuelDragListe(ModeleOllert modele) {
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

		this.modele.setDraggedListe(modele.getDonnee().getListeTaches(((VueListe) node).getLocalisation().get(0)));
	}
}
