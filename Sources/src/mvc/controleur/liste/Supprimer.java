package mvc.controleur.liste;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import mvc.modele.ModeleOllert;

public class Supprimer implements EventHandler<ActionEvent> {

	private ModeleOllert modele;

	public Supprimer(ModeleOllert modele) {
		this.modele = modele;
	}


	public void handle(ActionEvent e) {
		System.out.println("aaaaaaaaaaaaaaaa");
	}
}
