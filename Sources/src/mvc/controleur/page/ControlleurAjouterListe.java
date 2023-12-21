package mvc.controleur.page;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import mvc.modele.ModeleOllert;
import mvc.vue.liste.VueListe;
import mvc.vue.page.VuePage;
import mvc.vue.page.VuePageTableau;

public class ControlleurAjouterListe implements EventHandler<ActionEvent> {
	private ModeleOllert modele;

	public ControlleurAjouterListe(ModeleOllert modele) {
		this.modele = modele;
	}

	@Override
	public void handle(ActionEvent event) {
		this.modele.addListe("Liste x");
	}
}
