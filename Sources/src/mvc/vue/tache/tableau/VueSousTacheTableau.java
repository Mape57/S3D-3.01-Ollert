package mvc.vue.tache.tableau;

import javafx.scene.Node;
import javafx.scene.layout.VBox;
import mvc.vue.liste.VueListeTableau;
import mvc.vue.tache.contenu.*;

/**
 * Classe de la vue représentant une tâche sous forme de tableau
 * La vue est à la fois modèle (pour actualiser le contenu) et observateur (lors de la modification de son titre)
 */
public class VueSousTacheTableau extends VueTacheTableauAbstraite {
	/**
	 * Constructeur de la classe VueTacheTableau
	 */
	public VueSousTacheTableau() {
		this.setPrefWidth(VueListeTableau.WIDTH - 20 - 18);

		// Ajout des vues du contenu de la tâche
		VuePriorite vuePriorite = new VuePriorite();
		VueCalendrier vueCalendrier = new VueCalendrier();
		VueTitre vueTitre = new VueTitre();
		VueEtiquettes vueEtiquettes = new VueEtiquettes();
		VueMembres vueMembres = new VueMembres();
		this.addRow(0, vuePriorite, vueCalendrier);
		this.addRow(1, vueTitre);
		this.addRow(2, vueEtiquettes, vueMembres);
		VBox listeTaches = new VBox();
		listeTaches.setStyle("-fx-spacing: 10px;");
		this.add(listeTaches, 0, 3, 3, 3);

		this.setHgap(10);
		this.setVgap(10);
		this.setStyle("-fx-background-color: #a0a19b; -fx-border-color: black; -fx-border-width: 2px; -fx-padding: 5px; -fx-border-radius: 5px;");
	}

	@Override
	public Node getParentPrincipale() {
		return this.getParent().getParent();
	}
}
