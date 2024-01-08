package mvc.controleur.liste;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import mvc.modele.ModeleOllert;
import mvc.vue.liste.VueListe;
import ollert.tache.ListeTaches;

import java.util.Optional;

public class ControlleurAjouterTache implements EventHandler<ActionEvent> {
	private ModeleOllert modele;

	public ControlleurAjouterTache(ModeleOllert modele) {
		this.modele = modele;
	}

	@Override
	public void handle(ActionEvent event) {
		Button btn = (Button) event.getSource();
		VueListe vl = (VueListe) btn.getParent().getParent();

		int indice;
		if (vl.getParent() instanceof HBox){
			HBox parent = (HBox)vl.getParent();
			indice = parent.getChildren().indexOf(vl);
		}else{
			VBox parent = (VBox)vl.getParent();
			indice = parent.getChildren().indexOf(vl);
		}

		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Saisie de Tâche");
		dialog.setHeaderText(null);
		dialog.setContentText("Veuillez entrer le nom de la tâche :");

		Optional<String> result = dialog.showAndWait();

		if (result.isPresent()) {
			this.modele.getDonnee().getListes().get(indice).addTache(result.get());
			this.modele.notifierObservateurs();
		}
	}
}