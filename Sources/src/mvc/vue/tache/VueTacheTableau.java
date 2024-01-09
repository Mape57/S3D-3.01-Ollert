package mvc.vue.tache;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import mvc.controleur.tache.ControlleurModification;
import mvc.controleur.tache.ControlleurDragTacheOver;
import mvc.controleur.tache.ControlleurVisuelDragTache;
import mvc.modele.ModeleOllert;
import mvc.modele.Sujet;
import mvc.vue.Observateur;
import mvc.vue.VuePrincipale;
import mvc.vue.liste.VueListeTableau;
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
	public VueTacheTableau(ModeleOllert modeleControle) {
		this.setPrefWidth(VueListeTableau.WIDTH - 20 - 18);

		this.setOnDragDetected(new ControlleurVisuelDragTache(modeleControle));
		this.setOnDragDone(new ControlleurDragTacheOver(modeleControle));

		// Ajout des vues du contenu de la tâche
		VuePriorite vuePriorite = new VuePriorite();
		VueDependance vueDependance = new VueDependance();
		VueCalendrier vueCalendrier = new VueCalendrier();
		VueTitre vueTitre = new VueTitre();
		VueEtiquettes vueEtiquettes = new VueEtiquettes();
		VueMembres vueMembres = new VueMembres();
		this.addRow(0, vuePriorite, vueDependance, vueCalendrier);
		this.addRow(1, vueTitre);
		this.addRow(2, vueEtiquettes, vueMembres);
		GridPane.setColumnSpan(vueTitre, this.getColumnCount());

		this.setOnMouseClicked(new ControlleurModification(modeleControle));

		this.setHgap(10);
		this.setVgap(10);
		this.setStyle("-fx-background-color: #a0a19b; -fx-border-color: black; -fx-border-width: 2px;");
	}

	/**
	 * Actualise la vue courante
	 * @param sujet le modèle à partir duquel la vue est actualisée
	 */
	@Override
	public void actualiser(Sujet sujet) {
		for (int i = 0; i < this.getChildren().size(); i++)
			((Observateur) this.getChildren().get(i)).actualiser(sujet);
	}

	/**
	 * @return tache réelle que représente la vue
	 */
	public TachePrincipale getTache() {
		return null;
	}

	public List<Integer> getLocalisation() {
		ArrayList<Integer> loc = new ArrayList<>();
		VuePrincipale parent;
		// boucle en cas de sous tache
		parent = (VuePrincipale) this.getParentPrincipale();
		loc.add(0, ((Parent) parent.getChildrenPrincipale()).getChildrenUnmodifiable().indexOf(this));
		parent = (VuePrincipale) this.getParentPrincipale();
		loc.addAll(0, parent.getLocalisation());
		return loc;
	}


	public Node getParentPrincipale() {
		return ((ScrollPane) this.getParent().getProperties().get("scrollPane")).getParent();
	}

	public Node getChildrenPrincipale() {
		return null;
	}
}
