package mvc.controleur.liste;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import fabrique.FabriqueVueTableau;
import mvc.modele.ModeleOllert;
import mvc.vue.structure.VueListe;

import java.util.Optional;

/**
 * Controlleur permettant d'ajouter une tâche à une liste
 */
public class ControleurAjouterTache implements EventHandler<ActionEvent> {
	/**
	 * Modele de l'application
	 */
	private final ModeleOllert modele;

	/**
	 * Constructeur du contrôleur
	 * @param modele Modele de l'application
	 */
	public ControleurAjouterTache(ModeleOllert modele) {
		this.modele = modele;
	}

	/**
	 * Gère l'ajout d'une tâche à une liste
	 * @param event action de l'utilisateur (clic sur le bouton)
	 */
	@Override
	public void handle(ActionEvent event) {
		Button btn = (Button) event.getSource();
		VueListe vl;
		if (modele.getFabrique() instanceof FabriqueVueTableau){
			vl = (VueListe) btn.getParent().getParent();
		} else{
			vl = (VueListe) btn.getParent().getParent().getParent();
		}

		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Saisie de Tâche");
		dialog.setHeaderText(null);
		dialog.setContentText("Veuillez entrer le nom de la tâche :");

		Optional<String> result = dialog.showAndWait();

		if (result.isPresent()) {
			int indice;
			if (vl.getParent() instanceof HBox){
				HBox parent = (HBox)vl.getParent();
				indice = parent.getChildren().indexOf(vl);
			}else{
				VBox parent = (VBox)vl.getParent();
				indice = parent.getChildren().indexOf(vl);
			}

			this.modele.getDonnee().getListes().get(indice).addTache(result.get());
			this.modele.notifierObservateurs();
		}
	}
}
