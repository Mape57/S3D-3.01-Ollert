package mvc.controleur.page;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import mvc.fabrique.FabriqueVueTableur;
import mvc.modele.ModeleOllert;
import mvc.vue.page.VuePage;


/**
 * Contrôleur pour l'affichage Tableur
 */
public class ControlleurTableur implements EventHandler<ActionEvent> {
	/**
	 * Modele de l'application
	 */
	private ModeleOllert modele;

	/**
	 * Constructeur du contrôleur
	 * @param modele Modele de l'application
	 */
	public ControlleurTableur(ModeleOllert modele) {
		this.modele = modele;
	}

	/**
	 * Change l'affichage Tableur au click du bouton Tableur
	 * @param event l'événement utilisateur (click du bouton Tableur)
	 */
	@Override
	public void handle(ActionEvent event) {
		modele.setTacheCible(null);
		modele.setListeAnt(null);

		// On change la fabrique de vue et on affiche tous les éléments de la page
		modele.setFabrique(new FabriqueVueTableur(modele));
		Button src = (Button) event.getSource();
		BorderPane racine = (BorderPane) src.getParent().getParent();

		// On supprime l'ancienne vue de la liste des observateurs
		VuePage vp = (VuePage) racine.getCenter();
		modele.supprimerObservateur(vp);

		// On crée la nouvelle vue et on l'ajoute à la liste des observateurs
		vp = modele.getFabrique().creerVuePage();
		racine.setCenter((Node) vp);
		modele.ajouterObservateur(vp);
		modele.notifierObservateurs();
	}
}
