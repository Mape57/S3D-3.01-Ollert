package mvc.fabrique;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import mvc.controleur.liste.*;
import mvc.controleur.page.ControleurDragListe;
import mvc.controleur.tache.ControleurDragTacheOver;
import mvc.controleur.tache.ControleurModification;
import mvc.controleur.tache.ControleurVisuelDragTache;
import mvc.modele.ModeleOllert;
import mvc.vue.liste.VueListeTableau;
import mvc.vue.page.ParentScrollPane;
import mvc.vue.page.VuePageTableau;
import mvc.vue.tache.tableau.VueSousTacheTableau;
import mvc.vue.tache.VueTacheTableauPrincipale;

/**
 * Implementation de la FabriqueVue pour un affichage en tableau
 * : affichage classique (colonnes)
 */
public class FabriqueVueTableau extends FabriqueVue {

	public FabriqueVueTableau(ModeleOllert modeleOllert) {
		super(modeleOllert);
	}

	/**
	 * Crée la vue d'une tache sous forme de tableau
	 * @return Vue de la tache
	 */
	@Override
	public VueTacheTableauPrincipale creerVueTache() {
		VueTacheTableauPrincipale vueTacheTableau = new VueTacheTableauPrincipale();

		vueTacheTableau.setOnDragDetected(new ControleurVisuelDragTache(this.modeleOllert));
		vueTacheTableau.setOnDragDone(new ControleurDragTacheOver(this.modeleOllert));
		vueTacheTableau.setOnMouseClicked(new ControleurModification(this.modeleOllert));

		return vueTacheTableau;
	}

	/**
	 * Crée la vue d'une liste sous forme de tableau
	 * @return Vue de la liste
	 */
	@Override
	public VueListeTableau creerVueListe() {
		VueListeTableau vueListeTableau = new VueListeTableau();

		vueListeTableau.setOnDragDetected(new ControleurVisuelDragListe(this.modeleOllert));

		// header de la liste
		HBox header = new HBox();
		Label titreListe = new Label();
		Button btn_modif = new Button("Modif");
		btn_modif.setOnAction(new ControleurModifierTitre(this.modeleOllert));
		Button btn_ajouter = new Button("Ajouter");
		btn_ajouter.setOnAction(new ControleurAjouterTache(this.modeleOllert));
		header.getChildren().addAll(titreListe, btn_modif, btn_ajouter);
		vueListeTableau.getChildren().add(header);


		ParentScrollPane centre = new ParentScrollPane();
		centre.setStyle(" -fx-padding: 20px;");
		VBox listeTaches = new VBox();
		listeTaches.setStyle("-fx-spacing: 10px;");
		centre.setOnDragOver(new ControleurDragTache(this.modeleOllert));
		centre.setContentAndChildrenProp(listeTaches);
		vueListeTableau.getChildren().add(centre);


		// footer de la liste
		HBox footer = new HBox();
		Button btn_archiver = new Button("Archiver");
		Button btn_supprimer = new Button("Supprimer");
		btn_supprimer.setOnAction(new ControleurSupprimerListeTache(this.modeleOllert, vueListeTableau));
		footer.getChildren().addAll(btn_archiver, btn_supprimer);
		vueListeTableau.getChildren().add(footer);

		return vueListeTableau;
	}

	/**
	 * Crée la vue d'une page sous forme de tableau
	 * @return Vue de la page
	 */
	@Override
	public VuePageTableau creerVuePage() {
		// centre de la page
		ParentScrollPane centre = new ParentScrollPane();
		HBox hb = new HBox();
		hb.setStyle("-fx-padding: 10px;-fx-spacing: 20px;");
		centre.setContentAndChildrenProp(hb);
		centre.setOnDragOver(new ControleurDragListe(this.modeleOllert));
		centre.setFitToHeight(true);

		VuePageTableau vuePageTableau = new VuePageTableau();
		vuePageTableau.getChildren().add(centre);
		return vuePageTableau;
	}

	@Override
	public VueSousTacheTableau creerVueSousTache() {
		VueSousTacheTableau vueSousTacheTableau = new VueSousTacheTableau();

		vueSousTacheTableau.setOnMouseClicked(new ControleurModification(this.modeleOllert));
		vueSousTacheTableau.setOnDragDetected(new ControleurVisuelDragTache(this.modeleOllert));
		vueSousTacheTableau.setOnDragDone(new ControleurDragTacheOver(this.modeleOllert));

		return vueSousTacheTableau;
	}
}
