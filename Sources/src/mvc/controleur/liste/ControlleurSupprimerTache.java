package mvc.controleur.liste;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import mvc.modele.ModeleOllert;
import mvc.vue.liste.VueListe;

public class ControlleurSupprimerTache implements EventHandler<ActionEvent> {
	private ModeleOllert modele;

	public ControlleurSupprimerTache(ModeleOllert modele) {
		this.modele = modele;
	}

	@Override
	public void handle(ActionEvent event) {
		Button btn = (Button) event.getSource();
		VueListe vl = (VueListe) btn.getParent().getParent();
		this.modele.removeListeTache(vl.getListe());
	}
}
