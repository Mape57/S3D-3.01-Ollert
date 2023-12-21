package mvc.controleur.liste;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import mvc.modele.ModeleOllert;
import mvc.vue.liste.VueListe;

public class ControlleurAjouterTache implements EventHandler<ActionEvent> {
	private ModeleOllert modele;

	public ControlleurAjouterTache(ModeleOllert modele) {
		this.modele = modele;
	}

	@Override
	public void handle(ActionEvent event) {
		Button btn = (Button) event.getSource();
		// bouton -> HBox-header -> VueListe
		VueListe vl = (VueListe) btn.getParent().getParent();
		this.modele.addTache(vl.getListe(), "Tache 1");
	}
}
