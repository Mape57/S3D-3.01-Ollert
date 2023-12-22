package mvc.vue.page;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import mvc.controleur.page.ControlleurAjouterListe;
import javafx.scene.layout.StackPane;
import mvc.fabrique.FabriqueVueTableau;
import mvc.modele.ModeleOllert;
import mvc.modele.Sujet;
import mvc.vue.Observateur;
import mvc.vue.liste.VueListe;
import mvc.vue.liste.VueListeTableau;
import ollert.Page;
import ollert.tache.ListeTaches;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe de la vue représentant une page sous forme de tableau
 */
public class VuePageTableau extends HBox implements VuePage {
	/**
	 * Liste des observateurs (les vues des listes de la page (VueListeTableau))
	 */
	private List<Observateur> observateurs;
	/**
	 * Page réelle que représente la vue
	 */
	private Page page;

	private static final int TAILLE_HEADER = 0;
	private static final int TAILLE_FOOTER = 0;

	/**
	 * HBox contenant les vues des listes de la page
	 */
	private HBox hBox;

	/**
	 * Constructeur de la classe VuePageTableau
	 * @param page Page réelle que représente la vue
	 */
	public VuePageTableau(Page page, ModeleOllert modeleControle) {
		this.observateurs = new ArrayList<>();
		this.page = page;
		this.setSpacing(10);
		this.setPadding(new Insets(10));
		this.setStyle("-fx-background-color: red;");
		this.hBox = new HBox();
		this.hBox.setSpacing(10);
		this.hBox.setPadding(new Insets(10));
		this.hBox.setStyle("-fx-background-color: red;");
		this.getChildren().add(this.hBox);

		//footer listeTaches
//		VBox footer = new VBox();
//		footer.setStyle("-fx-background-color: green; -fx-padding: 10px;");
//		Button btn_ajouter = new Button("Ajouter liste");
//		footer.getChildren().addAll(btn_ajouter);
//		btn_ajouter.setOnAction(new ControlleurAjouterListe(modeleControle));
//		this.getChildren().add(footer);
		this.notifierObservateurs();
	}

	/**
	 * Ajoute un observateur à la liste des observateurs
	 * @param observateur L'observateur à ajouter
	 */
	@Override
	public void ajouterObservateur(Observateur observateur) {
		this.observateurs.add(observateur);
	}

	/**
	 * Supprime un observateur de la liste des observateurs
	 * @param observateur L'observateur à supprimer
	 */
	@Override
	public void supprimerObservateur(Observateur observateur) {
		this.observateurs.remove(observateur);
	}

	/**
	 * Notifie les observateurs de la mise à jour de la vue
	 */
	@Override
	public void notifierObservateurs() {
		for (Observateur observateur : this.observateurs)
			observateur.actualiser(this);
	}

	/**
	 * Actualise la vue
	 * @param sujet le modèle à partir duquel la vue est actualisée
	 */
	@Override
	public void actualiser(Sujet sujet) {
		ModeleOllert modele = (ModeleOllert) sujet;

		for (int i = 0; i < this.page.sizeListe(); i++) {
			ListeTaches l = this.page.getListeTaches(i);
			// la taille ne correspond pas : creation d'une Vue Liste
			if (i >= (this.hBox.getChildren().size() - (TAILLE_HEADER + TAILLE_FOOTER))) {
				VueListeTableau vl_tmp = new FabriqueVueTableau().creerVueListe(l, modele);
				this.hBox.getChildren().add(i + TAILLE_HEADER, vl_tmp);
				vl_tmp.actualiser(modele);
				continue;
			}

			VueListeTableau vl = (VueListeTableau) this.hBox.getChildren().get(i + TAILLE_HEADER);
			// la Vue et la Liste ne correspondent pas : insertion d'une Vue Liste
			if (!vl.getListe().equals(l)) {
				VueListeTableau vl_tmp = new FabriqueVueTableau().creerVueListe(l, modele);
				this.hBox.getChildren().add(i + TAILLE_HEADER, vl_tmp);
				vl_tmp.actualiser(modele);
				continue;
			}

			// la Vue Liste initiale est toujours la bonne
			vl.actualiser(modele);
		}

		// TODO a tester
		// nombre de liste de la page < nombre de liste de la vue : suppression des vues en trop
		if (this.hBox.getChildren().size() - (TAILLE_HEADER + TAILLE_FOOTER) > this.page.sizeListe())
			this.hBox.getChildren().remove(this.page.sizeListe(), this.hBox.getChildren().size());

		this.notifierObservateurs();
	}

	/**
	 * @return page réelle que représente la vue
	 */
	public Page getPage() {
		return this.page;
	}
}
