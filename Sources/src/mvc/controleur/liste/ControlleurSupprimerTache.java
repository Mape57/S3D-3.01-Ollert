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

public class ControlleurSupprimerTache implements EventHandler<ActionEvent> {
	private ModeleOllert modele;

	public ControlleurSupprimerTache(ModeleOllert modele) {
		this.modele = modele;
	}

	@Override
	public void handle(ActionEvent event) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Confirmation de Suppression");
		alert.setHeaderText(null);
		alert.setContentText("Voulez-vous vraiment supprimer cet élément ?");

		ButtonType buttonTypeValider = new ButtonType("Valider");
		ButtonType buttonTypeAnnuler = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
		alert.getButtonTypes().setAll(buttonTypeValider, buttonTypeAnnuler);

		Optional<ButtonType> result = alert.showAndWait();

		if (result.isPresent() && result.get() == buttonTypeValider) {
			Button btn = (Button) event.getSource();
			VueListe vl = (VueListe) btn.getParent().getParent();
			this.modele.removeListeTache(vl.getListe());
		} else {
			System.out.println("Suppression annulée.");
		}
	}
}
