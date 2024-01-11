package mvc.controleur.tache;


import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import mvc.modele.ModeleOllert;
import mvc.vue.tache.VueTache;
import mvc.vue.tache.VueTacheInterface;
import mvc.vue.tache.tableau.VueTacheTableauAbstraite;
import ollert.tache.Tache;

/**
 * Contrôleur de la modification d'une tache
 */
public class ControleurModification implements EventHandler<MouseEvent> {

	/**
	 * Modele de l'application
	 */
	private ModeleOllert modele;


	/**
	 * Constructeur de la classe ControleurModification
	 *
	 * @param modele Modele de l'application
	 */
	public ControleurModification(ModeleOllert modele) {
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
		Tache<?> t = this.modele.getTache(vueTache.getLocalisation());
		setupTacheEnGrand(t);
	}

	private void setupTacheEnGrand(Tache<?> t) {
		modele.setTacheEnGrand(t);
		VueTacheInterface vueTacheInterface = new VueTacheInterface(modele);
		modele.ajouterObservateur(vueTacheInterface);
		Stage stage = getStage(vueTacheInterface);

		// Afficher la Stage
		stage.show();
		modele.notifierObservateurs();
	}

	private Stage getStage(VueTacheInterface vueTacheInterface) {
		Stage stage = new Stage();
		stage.setOnHiding(windowEvent -> {
			modele.setTacheEnGrand(null);
			modele.supprimerObservateur(vueTacheInterface);
			modele.notifierObservateurs();
		});
		stage.setScene(new Scene(vueTacheInterface, 1200, 700));  // Ajustez la taille au besoin
		return stage;
	}
}

