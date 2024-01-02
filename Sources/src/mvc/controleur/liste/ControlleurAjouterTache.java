package mvc.controleur.liste;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import mvc.modele.ModeleOllert;
import mvc.vue.liste.VueListe;

public class ControlleurAjouterTache implements EventHandler<ActionEvent> {
	private ModeleOllert modele;

	public ControlleurAjouterTache(ModeleOllert modele) {
		this.modele = modele;
	}

	@Override
	public void handle(ActionEvent event) {
		Button btn = (Button) event.getSource();
		VueListe vl = (VueListe) btn.getParent().getParent();

		int indice = ((HBox)vl.getParent()).getChildren().indexOf(vl);
		this.modele.getDonnee().getListes().get(indice).addTache("Test add tache");
		this.modele.notifierObservateurs();
	}
}
