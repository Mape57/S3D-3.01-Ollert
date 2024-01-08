package mvc.controleur.page;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import mvc.modele.ModeleOllert;
import mvc.vue.liste.VueListe;
import mvc.vue.page.VuePage;
import mvc.vue.page.VuePageTableau;

import java.util.Optional;

public class ControlleurAjouterListe implements EventHandler<ActionEvent> {
	private ModeleOllert modele;

	public ControlleurAjouterListe(ModeleOllert modele) {
		this.modele = modele;
	}

	@Override
	public void handle(ActionEvent event) {

		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Saisie liste tache");
		dialog.setHeaderText(null);
		dialog.setContentText("nom liste tache :");

		Optional<String> result = dialog.showAndWait();

		if (result.isPresent()) {
			this.modele.addListe(result.get());
		}

	}
}
