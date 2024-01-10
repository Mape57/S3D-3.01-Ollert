package mvc.vue.tache;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import mvc.controleur.tache.*;
import mvc.modele.ModeleOllert;
import mvc.modele.Sujet;
import mvc.vue.Observateur;
import mvc.vue.VuePrincipale;
import mvc.vue.liste.VueListeTableau;
import mvc.vue.sousTache.VueSousTacheTableau;
import mvc.vue.tache.contenu.*;
import ollert.tache.TachePrincipale;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe de la vue représentant une tâche sous forme de tableau
 * La vue est à la fois modèle (pour actualiser le contenu) et observateur (lors de la modification de son titre)
 */
public class VueTacheTableau extends GridPane implements VueTache {

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
		this.addRow(1, vueTitre);
		this.addRow(2, vueEtiquettes, vueMembres);
		this.add(new VBox(), 0, 3, 3, 3);

		this.setHgap(10);
		this.setVgap(10);
		this.setStyle("-fx-background-color: #e2e2e2; -fx-border-color: black; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-padding: 5px;");
	}

	/**
	 * Actualise la vue courante
	 * @param sujet le modèle à partir duquel la vue est actualisée
	 */
	@Override
	public void actualiser(Sujet sujet) {

		for (int i = 0; i < this.getChildren().size() - 1; i++)
			((Observateur) this.getChildren().get(i)).actualiser(sujet);

		VBox sousTaches = (VBox) this.getChildren().get(this.getChildren().size() - 1);
		ModeleOllert modele = (ModeleOllert) sujet;
		sousTaches.getChildren().clear();
		TachePrincipale tache = (TachePrincipale) modele.getTache(this.getLocalisation());


		if (modele.getListeAnt() != null){
			this.setOnMouseClicked(new ControlleurAddAntecedents(modele));
			this.setOnDragDetected(null);
			this.setOnDragDone(null);
			this.getParentPrincipale().setOnDragDetected(null);

			if (modele.getListeAnt().contains(tache)){
				this.setStyle("-fx-background-color: #e2e2e2; -fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-padding: 5px;");
			}
		}

		for (int i = 0; i < tache.getSousTaches().size(); i++) {
			VueSousTacheTableau vueSousTache = new VueSousTacheTableau();
			sousTaches.getChildren().add(vueSousTache);
			vueSousTache.actualiser(sujet);
		}
	}

	public List<Integer> getLocalisation() {
		ArrayList<Integer> loc = new ArrayList<>();
		VuePrincipale parent;
		// boucle en cas de sous tache
		parent = (VuePrincipale) this.getParentPrincipale();
		loc.add(0, ((Parent) parent.getChildrenPrincipale()).getChildrenUnmodifiable().indexOf(this));
		loc.addAll(0, parent.getLocalisation());
		return loc;
	}


	public Node getParentPrincipale() {
		return ((ScrollPane) this.getParent().getProperties().get("scrollPane")).getParent();
	}

	public Node getChildrenPrincipale() {
		return this.getChildren().get(this.getChildren().size() - 1);
	}
}
