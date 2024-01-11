package mvc.controleur.page.creation;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import mvc.modele.ModeleOllert;
import ollert.donnee.Page;
import ollert.tool.Sauvegarde;

import java.io.File;

// FIXME utile ?

/**
 * Contrôleur permettant de charger un fichier
 */
public class ControleurChargerPage implements EventHandler<ActionEvent> {
	/**
	 * Modèle de l'application
	 */
	private final ModeleOllert modele;

	/**
	 * Stage principal de l'application
	 */
	private final Stage primaryStage;

	/**
	 * Constructeur du contrôleur
	 *
	 * @param modele       Modele de l'application
	 * @param primaryStage Stage principal de l'application
	 */
	public ControleurChargerPage(ModeleOllert modele, Stage primaryStage) {
		this.modele = modele;
		this.primaryStage = primaryStage;
	}

	/**
	 * Gère le chargement d'un fichier
	 *
	 * @param event action de l'utilisateur (clic sur le bouton)
	 */
	@Override
	public void handle(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choisissez un fichier");
		File initialDirectory = new File(Sauvegarde.DIR);
		fileChooser.setInitialDirectory(initialDirectory);

		// Configurer le filtre d'extension si nécessaire
		// FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Fichiers Texte (*.txt)", "*.txt");
		// fileChooser.getExtensionFilters().add(extFilter);

		// Afficher la boîte de dialogue et attendre la sélection du fichier
		File selectedFile = fileChooser.showOpenDialog(primaryStage);

		if (selectedFile != null) {
			// Traitez le fichier sélectionné selon vos besoins
			System.out.println("Fichier sélectionné : " + selectedFile.getAbsolutePath());
			Page page = Sauvegarde.chargerPage(selectedFile.getName());
			modele.setDonnee(page);

		} else {
			System.out.println("Aucun fichier sélectionné.");
		}
	}
}
