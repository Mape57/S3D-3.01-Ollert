package mvc.controleur.tache;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import mvc.modele.ModeleOllert;

public class ControlleurDragTacheOver implements EventHandler<DragEvent> {
	private ModeleOllert modeleControle;
	public ControlleurDragTacheOver(ModeleOllert modeleControle) {
		this.modeleControle = modeleControle;
	}

	@Override
	public void handle(DragEvent mouseEvent) {
		modeleControle.setDraggedTache(null);
	}
}
