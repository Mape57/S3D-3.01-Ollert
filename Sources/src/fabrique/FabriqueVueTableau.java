package fabrique;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import mvc.controleur.dragdrop.liste.ControleurDebuterDragListe;
import mvc.controleur.dragdrop.liste.ControleurDragListe;
import mvc.controleur.dragdrop.liste.ControleurRelacherDragListe;
import mvc.controleur.dragdrop.tache.ControleurDebuterDragTache;
import mvc.controleur.dragdrop.tache.ControleurDragTache;
import mvc.controleur.dragdrop.tache.ControleurRelacherDragTache;
import mvc.controleur.liste.ControleurAjouterTache;
import mvc.controleur.liste.ControleurModifierTitre;
import mvc.controleur.liste.ControleurSupprimerListeTache;
import mvc.controleur.tache.ControleurOuvertureComplete;
import mvc.modele.ModeleOllert;
import mvc.vue.principale.tableau.VueListeTableau;
import mvc.vue.principale.tableau.VuePageTableau;
import mvc.vue.principale.tableau.tache.VueSousTacheTableau;
import mvc.vue.principale.tableau.tache.VueTacheTableau;
import ollert.tool.ParentScrollPane;

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
	 *
	 * @return Vue de la tache
	 */
	@Override
	public VueTacheTableau creerVueTache() {
		VueTacheTableau vueTacheTableau = new VueTacheTableau();

		vueTacheTableau.setOnDragDetected(new ControleurDebuterDragTache(this.modeleOllert));
		vueTacheTableau.setOnDragDone(new ControleurRelacherDragTache(this.modeleOllert));
		vueTacheTableau.setOnMouseClicked(new ControleurOuvertureComplete(this.modeleOllert));

		return vueTacheTableau;
	}

	/**
	 * Crée la vue d'une liste sous forme de tableau
	 *
	 * @return Vue de la liste
	 */
	@Override
	public VueListeTableau creerVueListe() {
		VueListeTableau vueListeTableau = new VueListeTableau();

		vueListeTableau.setOnDragDetected(new ControleurDebuterDragListe(this.modeleOllert));
		vueListeTableau.setOnDragDone(new ControleurRelacherDragListe(this.modeleOllert));

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
		btn_supprimer.setOnAction(new ControleurSupprimerListeTache(this.modeleOllert));
		footer.getChildren().addAll(btn_archiver, btn_supprimer);
		vueListeTableau.getChildren().add(footer);

		return vueListeTableau;
	}

	/**
	 * Crée la vue d'une page sous forme de tableau
	 *
	 * @return Vue de la page
	 */
	@Override
	public VuePageTableau creerVuePage() {
		// centre de la page
		ParentScrollPane centre = new ParentScrollPane();
		HBox hb = new HBox();
		hb.setStyle("-fx-padding: 10px;-fx-spacing: 20px;");
		centre.setContentAndChildrenProp(hb);
		centre.setFitToHeight(true);

		VuePageTableau vuePageTableau = new VuePageTableau();
		vuePageTableau.getChildren().add(centre);
		vuePageTableau.setOnDragOver(new ControleurDragListe(this.modeleOllert));
		return vuePageTableau;
	}

	/**
	 * Crée la vue d'une sous-tache sous forme de tableau
	 *
	 * @return Vue de la tache
	 */
	@Override
	public VueSousTacheTableau creerVueSousTache() {
		VueSousTacheTableau vueSousTacheTableau = new VueSousTacheTableau();

		vueSousTacheTableau.setOnMouseClicked(new ControleurOuvertureComplete(this.modeleOllert));
		vueSousTacheTableau.setOnDragDetected(new ControleurDebuterDragTache(this.modeleOllert));
		vueSousTacheTableau.setOnDragDone(new ControleurRelacherDragTache(this.modeleOllert));

		return vueSousTacheTableau;
	}
}
