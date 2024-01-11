package mvc.controleur.liste;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import fabrique.FabriqueVueTableau;
import mvc.ModeleOllert;
import mvc.vue.structure.VueListe;

import java.util.Optional;

/**
 * Contrôleur pour la modification du titre d'une liste
 */
public class ControleurModifierTitre implements EventHandler<ActionEvent> {
	/**
	 * Modele de l'application
	 */
	private final ModeleOllert modele;

	/**
	 * Constructeur du contrôleur
	 * @param modele Modele de l'application
	 */
	public ControleurModifierTitre(ModeleOllert modele) {
		this.modele = modele;
	}

	/**
	 * Gère la modification du titre d'une liste
	 * @param event action de l'utilisateur
	 */
	@Override
	public void handle(ActionEvent event) {
		// On affiche une boîte de dialogue pour demander le nouveau titre
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Modifier nom liste de tâche");
		dialog.setHeaderText(null);
		dialog.setContentText("");

		Optional<String> result = dialog.showAndWait();

		if (result.isPresent()) {
			Button btn = (Button) event.getSource();

			VueListe vl;
			if (modele.getFabrique() instanceof FabriqueVueTableau){
				vl = (VueListe) btn.getParent().getParent();
			}else{
				vl = (VueListe) btn.getParent().getParent().getParent();
			}

			int indice;
			VBox parent = (VBox) vl.getParent();
			indice = parent.getChildren().indexOf(vl);

			modele.getDonnee().getListes().get(indice).setTitre(result.get());
			modele.notifierObservateurs();
		}
	}
}
