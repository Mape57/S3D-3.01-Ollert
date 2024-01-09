package mvc.controleur.page;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import mvc.fabrique.FabriqueVueTableur;
import mvc.modele.ModeleOllert;
import mvc.vue.page.VuePage;

public class ControlleurTableur implements EventHandler<ActionEvent> {
	private ModeleOllert modele;

	public ControlleurTableur(ModeleOllert modele) {
		this.modele = modele;
	}

	@Override
	public void handle(ActionEvent event) {
		modele.setFabrique(new FabriqueVueTableur(modele));
		Button src = (Button) event.getSource();
		BorderPane racine = (BorderPane) src.getParent().getParent();

		VuePage vp = (VuePage) racine.getCenter();
		modele.supprimerObservateur(vp);

		vp = modele.getFabrique().creerVuePage();
		racine.setCenter((Node) vp);
		modele.ajouterObservateur(vp);
		modele.notifierObservateurs();
	}
}
