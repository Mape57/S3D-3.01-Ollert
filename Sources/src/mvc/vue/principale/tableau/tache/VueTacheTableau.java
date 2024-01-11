package mvc.vue.principale.tableau.tache;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import mvc.controleur.tache.ControleurAddAntecedents;
import mvc.modele.ModeleOllert;
import mvc.modele.Sujet;
import mvc.vue.principale.tableau.VueListeTableau;
import mvc.vue.secondaire.*;
import ollert.donnee.tache.TacheAbstraite;

/**
 * Classe de la vue représentant une tâche sous forme de tableau
 */
public class VueTacheTableau extends VueTacheTableauAbstraite {

	/**
	 * Constructeur de la classe VueTacheTableau
	 */
	public VueTacheTableau() {
		this.setPrefWidth(VueListeTableau.WIDTH - 80);

		// Ajout des vues du contenu de la tâche
		VuePriorite vuePriorite = new VuePriorite();
		VueAntecedents vueDependance = new VueAntecedents();
		VueCalendrier vueCalendrier = new VueCalendrier();
		VueTitre vueTitre = new VueTitre();
		VueEtiquettes vueEtiquettes = new VueEtiquettes();
		VueMembres vueMembres = new VueMembres();
		this.addRow(0, vuePriorite, vueDependance, vueCalendrier);
		this.add(vueTitre, 0, 1, 3, 1);
		this.addRow(2, vueEtiquettes, vueMembres);
		VBox listeTaches = new VBox();
		listeTaches.setStyle("-fx-spacing: 10px;");
		this.add(listeTaches, 0, 3, 3, 3);

		this.setHgap(10);
		this.setVgap(10);
		this.setStyle("-fx-background-color: #e2e2e2; -fx-border-color: black; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-padding: 5px;");
	}

	/**
	 * Actualise la vue courante
	 *
	 * @param sujet le modèle à partir duquel la vue est actualisée
	 */
	@Override
	public void actualiser(Sujet sujet) {
		super.actualiser(sujet);

		// TODO retirer les controlleurs d'ici
		ModeleOllert modele = (ModeleOllert) sujet;
		TacheAbstraite<?> tache = modele.getTache(this.getLocalisation());
		if (modele.getListeAnt() != null) {
			this.setOnMouseClicked(new ControleurAddAntecedents(modele));
			this.setOnDragDetected(null);
			this.setOnDragDone(null);
			this.getParentPrincipale().setOnDragDetected(null);

			if (modele.getTacheCible() == tache) {
				this.setStyle("-fx-background-color: #e2e2e2; -fx-border-color: #0044ff; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-padding: 5px;");
			}

			if (modele.getListeAnt().contains(tache)) {
				this.setStyle("-fx-background-color: #e2e2e2; -fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-padding: 5px;");
			}
		}
	}

	@Override
	public Node getParentPrincipale() {
		return ((ScrollPane) this.getParent().getProperties().get("scrollPane")).getParent();
	}

}
