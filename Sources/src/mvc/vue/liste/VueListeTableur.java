package mvc.vue.liste;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import mvc.controleur.liste.*;
import mvc.controleur.page.ControlleurGantt;
import mvc.fabrique.FabriqueVueTableau;
import mvc.fabrique.FabriqueVueTableur;
import mvc.modele.ModeleOllert;
import mvc.modele.Sujet;
import mvc.vue.page.ParentScrollPane;
import mvc.vue.tache.VueTacheTableau;
import mvc.vue.tache.VueTacheTableur;
import ollert.Page;
import ollert.tache.ListeTaches;
import ollert.tache.Tache;
import ollert.tache.TachePrincipale;
import ollert.tache.donneesTache.Etiquette;
import ollert.tache.donneesTache.Priorite;
import ollert.tache.donneesTache.Utilisateur;

import java.util.List;

/**
 * Classe de la vue représentant une liste de tâches sous forme de tableau
 */
public class VueListeTableur extends VBox implements VueListe {

	/**
	 * Constructeur de la classe VueListeTableau
	 */
	public VueListeTableur(ModeleOllert modeleControle) {

		this.setStyle("-fx-background-color: white; -fx-padding: 20;");


		// Création du bandeau
		HBox bandeau = new HBox();
		bandeau.setStyle("-fx-background-color: #a0a0a0;");

			HBox titre = new HBox();
				titre.setPrefWidth(280);
				titre.setPrefHeight(50);
				titre.setStyle("-fx-border-style: solid; -fx-border-color: black; -fx-border-width: 1 1 1 1; -fx-padding: 10;");
				Label l1 = new Label("Nom de la liste");
				l1.setStyle("-fx-padding: 0 0 0 0;");


				Button btn_modif = new Button();
				btn_modif.setStyle("-fx-background-color: transparent;");
				ImageView view = new ImageView(new Image("file:Sources/ressource/images/icones/crayon-blanc.png"));
				view.setFitHeight(20);
				view.setPreserveRatio(true);
				btn_modif.setGraphic(view);
				btn_modif.setOnAction(new ControlleurGantt(modeleControle));

				btn_modif.setOnMouseEntered(event -> {
					ImageView v = new ImageView(new Image("file:Sources/ressource/images/icones/crayon-noir.png"));
					v.setFitHeight(20);
					v.setPreserveRatio(true);
					btn_modif.setGraphic(v);
				});
				btn_modif.setOnMouseExited(event -> {
					ImageView v = new ImageView(new Image("file:Sources/ressource/images/icones/crayon-blanc.png"));
					v.setFitHeight(20);
					v.setPreserveRatio(true);
					btn_modif.setGraphic(v);
				});
				btn_modif.setOnAction(new ControlleurModifierTitre(modeleControle));



				Button btn_ajout = new Button();
				btn_ajout.setStyle("-fx-background-color: transparent;");
				view = new ImageView(new Image("file:Sources/ressource/images/icones/ajouter-blanc.png"));
				view.setFitHeight(20);
				view.setPreserveRatio(true);
				btn_ajout.setGraphic(view);
				btn_ajout.setOnAction(new ControlleurGantt(modeleControle));

				btn_ajout.setOnMouseEntered(event -> {
					ImageView v = new ImageView(new Image("file:Sources/ressource/images/icones/ajouter-noir.png"));
					v.setFitHeight(20);
					v.setPreserveRatio(true);
					btn_ajout.setGraphic(v);
				});
				btn_ajout.setOnMouseExited(event -> {
					ImageView v = new ImageView(new Image("file:Sources/ressource/images/icones/ajouter-blanc.png"));
					v.setFitHeight(20);
					v.setPreserveRatio(true);
					btn_ajout.setGraphic(v);
				});
				btn_ajout.setOnAction(new ControlleurAjouterTache(modeleControle));


				Button btn_supp = new Button();
				btn_supp.setStyle("-fx-background-color: transparent;");
				view = new ImageView(new Image("file:Sources/ressource/images/icones/delete-blanc.png"));
				view.setFitHeight(20);
				view.setPreserveRatio(true);
				btn_supp.setGraphic(view);
				btn_supp.setOnAction(new ControlleurGantt(modeleControle));

				btn_supp.setOnMouseEntered(event -> {
					ImageView v = new ImageView(new Image("file:Sources/ressource/images/icones/delete-noir.png"));
					v.setFitHeight(20);
					v.setPreserveRatio(true);
					btn_supp.setGraphic(v);
				});
				btn_supp.setOnMouseExited(event -> {
					ImageView v = new ImageView(new Image("file:Sources/ressource/images/icones/delete-blanc.png"));
					v.setFitHeight(20);
					v.setPreserveRatio(true);
					btn_supp.setGraphic(v);
				});
				btn_supp.setOnAction(new ControlleurSupprimerTache(modeleControle, this));


				Button btn_archiv = new Button();
				btn_archiv.setStyle("-fx-background-color: transparent;");
				view = new ImageView(new Image("file:Sources/ressource/images/icones/archive-blanc.png"));
				view.setFitHeight(20);
				view.setPreserveRatio(true);
				btn_archiv.setGraphic(view);
				btn_archiv.setOnAction(new ControlleurGantt(modeleControle));

				btn_archiv.setOnMouseEntered(event -> {
					ImageView v = new ImageView(new Image("file:Sources/ressource/images/icones/archive-noir.png"));
					v.setFitHeight(20);
					v.setPreserveRatio(true);
					btn_archiv.setGraphic(v);
				});
				btn_archiv.setOnMouseExited(event -> {
					ImageView v = new ImageView(new Image("file:Sources/ressource/images/icones/archive-blanc.png"));
					v.setFitHeight(20);
					v.setPreserveRatio(true);
					btn_archiv.setGraphic(v);
				});


			titre.getChildren().addAll(l1, btn_modif, btn_ajout, btn_supp, btn_archiv);

			Label l2 = new Label("Debut");
			l2.setPrefWidth(200);
			l2.setPrefHeight(50);
			l2.setStyle("-fx-border-style: solid; -fx-border-color: black; -fx-border-width: 1 1 1 1; -fx-padding: 10;");

			Label l3 = new Label("Echéance");
			l3.setPrefWidth(200);
			l3.setPrefHeight(50);
			l3.setStyle("-fx-border-style: solid; -fx-border-color: black; -fx-border-width: 1 1 1 1; -fx-padding: 10;");


			Label l4 = new Label("Membres");
			l4.setPrefWidth(200);
			l4.setPrefHeight(50);
			l4.setStyle("-fx-border-style: solid; -fx-border-color: black; -fx-border-width: 1 1 1 1; -fx-padding: 10;");


			Label l5 = new Label("Etiquettes");
			l5.setPrefWidth(200);
			l5.setPrefHeight(50);
			l5.setStyle("-fx-border-style: solid; -fx-border-color: black; -fx-border-width: 1 1 1 1; -fx-padding: 10;");


			Label l6 = new Label("Priorité");
			l6.setPrefWidth(200);
			l6.setPrefHeight(50);
			l6.setStyle("-fx-border-style: solid; -fx-border-color: black; -fx-border-width: 1 1 1 1; -fx-padding: 10;");


		bandeau.getChildren().addAll(titre, l2, l3, l4, l5, l6);
		this.getChildren().add(bandeau);

		// Création du contenu
		VBox centre = new VBox();
		centre.setStyle("-fx-background-color: #e9e9e9");
		this.getChildren().add(centre);
	}

	/**
	 * Actualise la vue courante
	 * @param sujet le modèle à partir duquel la vue est actualisée
	 */
	@Override
	public void actualiser(Sujet sujet) {
		ModeleOllert modele = (ModeleOllert) sujet;
		Page page = modele.getDonnee();
		VBox parent = (VBox) this.getParent();
		int indice = parent.getChildren().indexOf(this);

		// maj bandeau
		HBox bandeau = (HBox) this.getChildren().get(0);
		HBox titre = (HBox) bandeau.getChildren().get(0);
		Label l1 = (Label) titre.getChildren().get(0);
		l1.setText(page.getListeTaches(indice).getTitre());


		// maj contenu
		ListeTaches lt = page.getListeTaches(indice);
		VBox centre = (VBox) this.getChildren().get(1);
		centre.getChildren().clear();
		for (Tache t : lt.getTaches()) {
			VueTacheTableur vt_tmp = new FabriqueVueTableur().creerVueTache(modele);
			centre.getChildren().add(vt_tmp);
			vt_tmp.actualiser(modele);
		}


	}

	@Override
	public Node getChildrenPrincipale() {
		return null;
	}

	@Override
	public Node getParentPrincipale() {
		return null;
	}

	@Override
	public List<Integer> getLocalisation() {
		return null;
	}

	@Override
	public ListeTaches getListe() {
		return null;
	}
}
