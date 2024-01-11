package fabrique;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import mvc.controleur.liste.ControleurAjouterTache;
import mvc.controleur.liste.ControleurModifierTitre;
import mvc.controleur.liste.ControleurSupprimerListeTache;
import mvc.controleur.page.affichage.ControleurGantt;
import mvc.controleur.tache.ControleurOuvertureComplete;
import mvc.ModeleOllert;
import mvc.vue.principale.tableur.VueListeTableur;
import mvc.vue.principale.tableur.VuePageTableur;
import mvc.vue.structure.VueTache;
import mvc.vue.principale.tableur.VueTacheTableur;

/**
 * Implementation de la FabriqueVue pour un affichage tableur
 * : affichage classique (colonnes)
 */
public class FabriqueVueTableur extends FabriqueVue {

	/**
	 * Constructeur de la fabrique de vue tableur
	 * @param modeleOllert Modele de l'application
	 */
	public FabriqueVueTableur(ModeleOllert modeleOllert) {
		super(modeleOllert);
	}

	/**
	 * Crée la vue d'une tache sous forme tableur
	 * @return Vue de la tache
	 */
	@Override
	public VueTacheTableur creerVueTache() {
		VueTacheTableur vueTacheTableur = new VueTacheTableur();
		vueTacheTableur.setOnMouseClicked(new ControleurOuvertureComplete(this.modeleOllert));

		HBox tache = new HBox();
		HBox titre = new HBox();

		titre.getChildren().add(creerLabel("1", 280, 40));
		tache.getChildren().add(titre);

		for (int i = 2; i <= 6; i++) {
			tache.getChildren().add(creerLabel(Integer.toString(i), 200, 40));
		}

		vueTacheTableur.getChildren().add(tache);

		return vueTacheTableur;
	}

	/**
	 * Crée la vue d'une liste sous forme tableur
	 * @return Vue de la liste
	 */
	@Override
	public VueListeTableur creerVueListe() {
		VueListeTableur vueListeTableur = new VueListeTableur();

		// Création du bandeau
		HBox bandeau = new HBox();
		bandeau.setStyle("-fx-background-color: #a0a0a0;");

		HBox titre = new HBox();
		titre.setPrefWidth(280);
		titre.setPrefHeight(40);
		titre.setStyle("-fx-border-style: solid; -fx-border-color: black; -fx-border-width: 1 1 1 1; -fx-padding: 10;");
		Label l1 = creerLabel("Nom de la liste", 200, 40);
		l1.setStyle("-fx-padding: 0 0 0 0;");


		Button btn_modif = creerBouton("crayon-blanc.png", "crayon-noir.png", new ControleurModifierTitre(this.modeleOllert));

		Button btn_ajout = creerBouton("ajouter-blanc.png", "ajouter-noir.png", new ControleurAjouterTache(this.modeleOllert));

		Button btn_supp = creerBouton("delete-blanc.png", "delete-noir.png", new ControleurSupprimerListeTache(this.modeleOllert, vueListeTableur));

		Button btn_archiv = creerBouton("archive-blanc.png", "archive-noir.png", new ControleurGantt(this.modeleOllert));



		titre.getChildren().addAll(l1, btn_modif, btn_ajout, btn_supp, btn_archiv);

		Label l2 = creerLabel("Debut", 200, 50);
		Label l3 = creerLabel("Echéance", 200, 50);
		Label l4 = creerLabel("Membres",200, 50);
		Label l5 = creerLabel("Etiquettes",200, 50);
		Label l6 = creerLabel("Priorité",200, 50);


		bandeau.getChildren().addAll(titre, l2, l3, l4, l5, l6);
		vueListeTableur.getChildren().add(bandeau);

		// Création du contenu
		VBox centre = new VBox();
		centre.setStyle("-fx-background-color: #e9e9e9");
		vueListeTableur.getChildren().add(centre);

		return vueListeTableur;
	}

	/**
	 * Crée un label avec un texte, une largeur et une hauteur
	 * @param texte Texte du label
	 * @param largeur Largeur du label
	 * @param hauteur Hauteur du label
	 * @return Label créé aux bonnes dimensions
	 */
	private Label creerLabel(String texte, int largeur, int hauteur) {
		Label label = new Label(texte);
		label.setPrefWidth(largeur);
		label.setPrefHeight(hauteur);
		label.setStyle("-fx-border-style: solid; -fx-border-color: black; -fx-border-width: 1 1 1 1; -fx-padding: 10;");
		return label;
	}

	/**
	 * Crée un bouton avec ses animations (icône noire ou blanche selon le survol)
	 * @param imageBlanc Image du bouton en blanc
	 * @param imageNoir Image du bouton en noir
	 * @param action Contrôleur du bouton
	 * @return Bouton créé
	 */
	private Button creerBouton(String imageBlanc, String imageNoir, EventHandler<ActionEvent> action) {
		String cheminDossierIcones = "file:Sources/ressource/images/icones/";
		Button btn = new Button();
		btn.setStyle("-fx-background-color: transparent;");
		ImageView view = new ImageView(new Image(cheminDossierIcones + imageBlanc));
		view.setFitHeight(20);
		view.setPreserveRatio(true);
		btn.setGraphic(view);
		btn.setOnAction(action);

		btn.setOnMouseEntered(event -> {
			ImageView v = new ImageView(new Image(cheminDossierIcones + imageNoir));
			v.setFitHeight(20);
			v.setPreserveRatio(true);
			btn.setGraphic(v);
		});
		btn.setOnMouseExited(event -> {
			ImageView v = new ImageView(new Image(cheminDossierIcones + imageBlanc));
			v.setFitHeight(20);
			v.setPreserveRatio(true);
			btn.setGraphic(v);
		});

		return btn;
	}


	/**
	 * Crée la vue d'une page sous forme tableur
	 * @return Vue de la page
	 */
	@Override
	public VuePageTableur creerVuePage() {
		return new VuePageTableur();
	}

	/**
	 * Crée la vue d'une sous-tache sous forme tableur
	 *
	 * @return Vue de la sous-tache
	 */
	@Override
	public VueTache creerVueSousTache() {
		return null;
	}
}
