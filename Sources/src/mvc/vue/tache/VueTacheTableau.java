package mvc.vue.tache;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import mvc.fabrique.FabriqueVueTableau;
import mvc.controleur.tache.*;
import mvc.modele.ModeleOllert;
import mvc.modele.Sujet;
import mvc.vue.Observateur;
import mvc.vue.PlaceurSeparateur;
import mvc.vue.VuePrincipale;
import mvc.vue.liste.VueListeTableau;
import mvc.vue.tache.contenu.*;
import ollert.tache.TachePrincipale;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Classe de la vue représentant une tâche sous forme de tableau
 * La vue est à la fois modèle (pour actualiser le contenu) et observateur (lors de la modification de son titre)
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
	 * @param sujet le modèle à partir duquel la vue est actualisée
	 */
	@Override
	public void actualiser(Sujet sujet) {

		ModeleOllert modele = (ModeleOllert) sujet;
		List<Integer> indices = this.getLocalisation();

		TachePrincipale tache = (TachePrincipale) modele.getTache(indices);

		VBox listeTaches = (VBox) this.getChildren().get(this.getChildren().size() - 1);
		for (int i = 0; i < listeTaches.getChildren().size(); i++) {
			Node n = listeTaches.getChildren().get(i);
			if (n instanceof Separator) {
				listeTaches.getChildren().remove(i);
				break;
			}
		}

		if (PlaceurSeparateur.placerSeparateur(modele, listeTaches, this))
			return;

		// mise a jour des donnees (-1 pour la liste de sous tache)

		for (int i = 0; i < this.getChildren().size() - 1; i++)
			((Observateur) this.getChildren().get(i)).actualiser(sujet);

		// mise a jour des sous taches
		listeTaches.getChildren().clear();


		if (modele.getListeAnt() != null){
			this.setOnMouseClicked(new ControleurAddAntecedents(modele));
			this.setOnDragDetected(null);
			this.setOnDragDone(null);
			this.getParentPrincipale().setOnDragDetected(null);

			if (modele.getListeAnt().contains(tache)){
				this.setStyle("-fx-background-color: #e2e2e2; -fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-padding: 5px;");
			}
		}

		for (int i = 0; i < tache.getSousTaches().size(); i++) {
			VueSousTacheTableau vueSousTache = new FabriqueVueTableau(modele).creerVueSousTache();
			listeTaches.getChildren().add(vueSousTache);
			vueSousTache.actualiser(sujet);
		}
	}

	public List<Integer> getLocalisation() {
		ArrayList<Integer> loc = new ArrayList<>();
		VuePrincipale parent;
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
