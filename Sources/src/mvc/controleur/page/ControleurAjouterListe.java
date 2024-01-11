package mvc.controleur.page;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextInputDialog;
import mvc.modele.ModeleOllert;

import java.util.Optional;

/**
 * Contrôleur permettant d'ajouter une liste de tâche
 */
public class ControleurAjouterListe implements EventHandler<ActionEvent> {
	/**
	 * Modele de l'application
	 */
	private final ModeleOllert modele;

	/**
	 * Constructeur du contrôleur
	 *
	 * @param modele Modele de l'application
	 */
	public ControleurAjouterListe(ModeleOllert modele) {
		this.modele = modele;
	}

	/**
	 * Gère l'ajout d'une liste de tâche
	 *
	 * @param event action de l'utilisateur (clic sur le bouton)
	 */
	@Override
	public void handle(ActionEvent event) {

		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Saisie liste tache");
		dialog.setHeaderText(null);
		dialog.setContentText("nom liste tache :");

		Optional<String> result = dialog.showAndWait();

		if (result.isPresent()) {

			if (result.get().length() > 0) {
				this.modele.addListeTache(result.get());
			}
		}


	}
}
