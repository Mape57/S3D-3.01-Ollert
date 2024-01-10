package mvc.controleur.liste;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import mvc.modele.ModeleOllert;
import mvc.vue.liste.VueListe;
import ollert.tache.ListeTaches;

import java.util.Optional;

/**
 * Contrôleur pour la suppression d'une liste
 */
public class ControleurSupprimerTache implements EventHandler<ActionEvent> {
	/**
	 * Modele de l'application
	 */
	private final ModeleOllert modele;
	/**
	 * Vue de la liste l'origine de la demande de suppression
	 */
	private VueListe vueListe;

	/**
	 * Constructeur du contrôleur
	 * @param modele Modele de l'application
	 * @param vl Vue de la liste l'origine de la demande de suppression
	 */
	public ControleurSupprimerTache(ModeleOllert modele, VueListe vl) {
		this.modele = modele;
		this.vueListe = vl;
	}

	/**
	 * Gère la suppression d'une liste
	 * @param event action de l'utilisateur
	 */
	@Override
	public void handle(ActionEvent event) {
		// On affiche une boîte de dialogue pour demander confirmation de la suppression
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Confirmation de Suppression");
		alert.setHeaderText(null);
		alert.setContentText("Voulez-vous vraiment supprimer cet élément ?");

		ButtonType buttonTypeValider = new ButtonType("Valider");
		ButtonType buttonTypeAnnuler = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
		alert.getButtonTypes().setAll(buttonTypeValider, buttonTypeAnnuler);

		Optional<ButtonType> result = alert.showAndWait();

		// Si l'utilisateur a confirmé la suppression, on supprime la liste
		if (result.isPresent() && result.get() == buttonTypeValider) {
			int indice;
			Pane parent = (Pane) vueListe.getParent();
			indice = parent.getChildren().indexOf(vueListe);
			ListeTaches lt = modele.getDonnee().getListeTaches(indice);
			modele.getDonnee().removeListeTaches(lt);
			this.modele.notifierObservateurs();
		}
	}
}
