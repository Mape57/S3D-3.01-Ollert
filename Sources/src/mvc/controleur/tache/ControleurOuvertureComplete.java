package mvc.controleur.tache;


import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import mvc.modele.ModeleOllert;
import mvc.vue.structure.VueTache;
import mvc.vue.secondaire.tacheComplete.VueTacheComplete;
import ollert.donnee.tache.TacheAbstraite;

/**
 * Contrôleur de la modification d'une tache
 */
public class ControleurOuvertureComplete implements EventHandler<MouseEvent> {

	/**
	 * Modele de l'application
	 */
	private ModeleOllert modele;


	/**
	 * Constructeur de la classe ControleurModification
	 *
	 * @param modele Modele de l'application
	 */
	public ControleurOuvertureComplete(ModeleOllert modele) {
		this.modele = modele;
	}

	/**
	 * Gère la modification d'une tâche (ouvre une fenêtre pour afficher la tache étendue)
	 *
	 * @param event action de l'utilisateur
	 */
	@Override
	public void handle(MouseEvent event) {
		if (modele.getTacheEnGrand() != null)
			return;

		VueTache vueTache = (VueTache) event.getSource();
		TacheAbstraite<?> t = this.modele.getTache(vueTache.getLocalisation());
		setupTacheEnGrand(t);
	}

	private void setupTacheEnGrand(TacheAbstraite<?> t) {
		modele.setTacheEnGrand(t);
		VueTacheComplete vueTacheComplete = new VueTacheComplete(modele);
		modele.ajouterObservateur(vueTacheComplete);
		Stage stage = getStage(vueTacheComplete);

		// Afficher la Stage
		stage.show();
		modele.notifierObservateurs();
	}

	private Stage getStage(VueTacheComplete vueTacheComplete) {
		Stage stage = new Stage();
		stage.setOnHiding(windowEvent -> {
			modele.setTacheEnGrand(null);
			modele.supprimerObservateur(vueTacheComplete);
			modele.notifierObservateurs();
		});
		stage.setScene(new Scene(vueTacheComplete, 1200, 700));  // Ajustez la taille au besoin
		return stage;
	}
}

