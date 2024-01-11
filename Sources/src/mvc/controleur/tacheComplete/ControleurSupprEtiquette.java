package mvc.controleur.tacheComplete;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import mvc.modele.ModeleOllert;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe ControleurSupprEtiquette qui permet de supprimer une étiquette
 */
public class ControleurSupprEtiquette implements EventHandler<ActionEvent> {

	/**
	 * Modele de l'application
	 */
	private final ModeleOllert modele;
	// FIXME: A vérifier
	private Boolean modeSuppression;
	private List<Label> selected;

	/**
	 * Constructeur de la classe ControleurModification
	 *
	 * @param modeleOllert Modele de l'application
	 */
	public ControleurSupprEtiquette(ModeleOllert modeleOllert, List<Label> l) {
		this.modele = modeleOllert;
		this.modeSuppression = false;
		this.selected = new ArrayList<>();
		for (Label x : l) {
			x.setOnMouseClicked(e -> {
				if (modeSuppression) {
					if (!selected.contains(x)) {
						selected.add(x);
						x.setStyle("-fx-background-color: red;");
					} else {
						selected.remove(x);
						x.setStyle("-fx-background-color: purple;");
					}
				}
			});
		}
	}

	@Override
	public void handle(ActionEvent actionEvent) {
		modeSuppression = !modeSuppression;
		if (!modeSuppression) {
			for (Label u : selected) {
				modele.getTacheComplete().supprimerEtiquette(u.getText());
				u.setDisable(true);
				u.setVisible(false);
			}
			selected = new ArrayList<>();
		}
		((Button) actionEvent.getSource()).setText(modeSuppression ? "Valider" : "Supprimer");
	}
}
