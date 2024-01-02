package mvc.controleur.liste;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import mvc.modele.ModeleOllert;
import mvc.vue.liste.VueListe;

import java.util.List;

public class ControlleurModifierTitre implements EventHandler<ActionEvent> {
	private ModeleOllert modele;

	public ControlleurModifierTitre(ModeleOllert modele) {
		this.modele = modele;
	}

	@Override
	public void handle(ActionEvent event) {
		System.out.println("modifier titre");
	}
}
