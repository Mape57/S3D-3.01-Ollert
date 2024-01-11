package mvc.controleur.page;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import mvc.modele.ModeleOllert;
import ollert.Page;
import ollert.Sauvegarde;

import java.io.File;
import java.util.Optional;

/**
 * Contrôleur permettant de charger un fichier
 */
public class ControleurNew implements EventHandler<ActionEvent> {
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
	 * @param modele Modele de l'application
	 * @param primaryStage Stage principal de l'application
	 */
	public ControleurNew(ModeleOllert modele, Stage primaryStage) {
		this.modele = modele;
		this.primaryStage = primaryStage;
	}

	/**
	 * Gère le chargement d'un fichier
	 * @param event action de l'utilisateur (clic sur le bouton)
	 */
	@Override
	public void handle(ActionEvent event) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Nouvelle Page");
		dialog.setHeaderText(null);
		dialog.setContentText("Nom de la Page :");

		Optional<String> result = dialog.showAndWait();

		if (result.isPresent()) {
			if (result.get().length() > 0){
				File dossier = new File(Sauvegarde.DIR);
				File[] fichiers = dossier.listFiles();

				boolean valide = true;
				for (File fichier : fichiers) {
					String f = fichier.getName().split("\\.")[0];
					if (f.equals(result.get())){
						valide = false;
					}
				}


				if (valide){
					Page page = new Page(result.get());
					modele.setDonnee(page);
					Sauvegarde.sauvegarderPage(page);
				}else{
					Alert alert = new Alert(Alert.AlertType.WARNING);
					alert.setTitle("Erreur");
					alert.setContentText("Erreur nom de page existant ou vide");

					ButtonType buttonTypeAnnuler = new ButtonType("OK", ButtonBar.ButtonData.CANCEL_CLOSE);
					alert.getButtonTypes().setAll(buttonTypeAnnuler);

					Optional<ButtonType> result2 = alert.showAndWait();
				}
			}
		}
	}
}
