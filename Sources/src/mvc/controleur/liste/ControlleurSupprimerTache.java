package mvc.controleur.liste;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import mvc.modele.ModeleOllert;
import mvc.vue.liste.VueListe;
import ollert.Page;
import ollert.tache.ListeTaches;

import java.util.Optional;

public class ControlleurSupprimerTache implements EventHandler<ActionEvent> {
	private ModeleOllert modele;
	private VueListe vueListe;

	public ControlleurSupprimerTache(ModeleOllert modele, VueListe vl) {
		this.modele = modele;
		this.vueListe = vl;
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
			int indice;
			if (vueListe.getParent() instanceof HBox){
				HBox parent = (HBox) vueListe.getParent();
				indice = parent.getChildren().indexOf(vueListe);
			}else{
				VBox parent = (VBox) vueListe.getParent();
				indice = parent.getChildren().indexOf(vueListe);
			}
			ListeTaches lt = modele.getDonnee().getListeTaches(indice);
			modele.getDonnee().removeListeTaches(lt);
			this.modele.notifierObservateurs();
		}
	}
}
