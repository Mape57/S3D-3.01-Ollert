package mvc.controleur.tache;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import mvc.modele.ModeleOllert;
import mvc.vue.structure.VueTache;
import ollert.donnee.tache.Tache;

import java.util.Optional;

/**
 * Contrôleur de la deuxième partie d'ajout d'antécédents (le clic sur une tâche après avoir cliqué sur les menottes) à une tâche
 */
public class ControleurAddAntecedents implements EventHandler<MouseEvent> {

	/**
	 * Modele de l'application
	 */
	private final ModeleOllert modele;

	/**
	 * Constructeur du contrôleur
	 *
	 * @param modeleControle Modele de l'application
	 */
	public ControleurAddAntecedents(ModeleOllert modeleControle) {
		this.modele = modeleControle;
	}

	/**
	 * Gère l'ajout d'antécédents à une tâche (clic sur une tâche après avoir cliqué sur les menottes)
	 *
	 * @param event action de l'utilisateur
	 */
	@Override
	public void handle(MouseEvent event) {
		VueTache vt = (VueTache) event.getSource();

		Tache t = (Tache) modele.getTache(vt.getLocalisation());

		if (modele.getListeAnt().contains(t)) {
			t.supprimerDependance(modele.getTacheCible());
		} else {
			// verification tache selection se termine avant le debut de la tache cible
			if (t.getDateDebut() != null && t.getDateFin() != null && modele.getTacheCible().getDateDebut().isAfter(t.getDateFin())) {
				t.ajouterDependance(modele.getTacheCible());
			} else {
				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.setTitle("Erreur");
				alert.setContentText("Ajout dépendance impossible :  date non conforme");

				ButtonType buttonTypeAnnuler = new ButtonType("OK", ButtonBar.ButtonData.CANCEL_CLOSE);
				alert.getButtonTypes().setAll(buttonTypeAnnuler);

				Optional<ButtonType> result2 = alert.showAndWait();
			}
		}


		modele.notifierObservateurs();

	}
}
