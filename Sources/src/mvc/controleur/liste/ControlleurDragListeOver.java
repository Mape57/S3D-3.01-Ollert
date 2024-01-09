package mvc.controleur.liste;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import mvc.modele.ModeleOllert;

public class ControlleurDragListeOver implements EventHandler<DragEvent> {
	private ModeleOllert modeleControle;
	public ControlleurDragListeOver(ModeleOllert modeleControle) {
		this.modeleControle = modeleControle;
	}
	@Override
	public void handle(DragEvent dragEvent) {
		modeleControle.setDraggedListe(null);
	}
}
