package mvc.controleur.liste;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import mvc.fabrique.FabriqueVueTableau;
import mvc.modele.ModeleOllert;
import mvc.vue.liste.VueListe;
import mvc.vue.page.VuePageTableau;

import java.util.Optional;

public class ControlleurModifierTitre implements EventHandler<ActionEvent> {
	private ModeleOllert modele;

	public ControlleurModifierTitre(ModeleOllert modele) {
		this.modele = modele;
	}

	@Override
	public void handle(ActionEvent event) {
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
			if (vl.getParent() instanceof VuePageTableau){
				HBox parent = (HBox) vl.getParent();
				indice = parent.getChildren().indexOf(vl);
			}else{
				VBox parent = (VBox) vl.getParent();
				indice = parent.getChildren().indexOf(vl);
			}
			modele.getDonnee().getListes().get(indice).setTitre(result.get());
			modele.notifierObservateurs();
		}
	}
}
