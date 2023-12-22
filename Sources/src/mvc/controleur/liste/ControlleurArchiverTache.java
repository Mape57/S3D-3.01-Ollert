package mvc.controleur.liste;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import mvc.modele.ModeleOllert;
import mvc.vue.liste.VueListe;

import java.util.Optional;

public class ControlleurArchiverTache implements EventHandler<ActionEvent> {
	private ModeleOllert modele;

	public ControlleurArchiverTache(ModeleOllert modele) {
		this.modele = modele;
	}

	@Override
	public void handle(ActionEvent event) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Confirmation l'archivage");
		alert.setHeaderText(null);
		alert.setContentText("Voulez-vous vraiment archiver cet élément ?");

		ButtonType buttonTypeValider = new ButtonType("Valider");
		ButtonType buttonTypeAnnuler = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
		alert.getButtonTypes().setAll(buttonTypeValider, buttonTypeAnnuler);

		Optional<ButtonType> result = alert.showAndWait();

		if (result.isPresent() && result.get() == buttonTypeValider) {

			// ATTENTION METTRE A JOUR POUR ARCHIVER ET PAS SUPPRIMER
			Button btn = (Button) event.getSource();
			VueListe vl = (VueListe) btn.getParent().getParent();
			this.modele.removeListeTache(vl.getListe());
		}
	}
}
