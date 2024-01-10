package mvc.vue.liste;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import mvc.fabrique.FabriqueVueTableau;
import mvc.modele.ModeleOllert;
import mvc.modele.Sujet;
import mvc.vue.PlaceurSeparateur;
import mvc.vue.tache.VueTacheTableauPrincipale;
import mvc.vue.tache.tableau.VueTacheTableauAbstraite;
import ollert.Page;
import ollert.tache.ListeTaches;
import ollert.tache.Tache;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe de la vue représentant une liste de tâches sous forme de tableau
 */
public class VueListeTableau extends VBox implements VueListe {
	public static final int WIDTH = 400;

	/**
	 * Constructeur de la classe VueListeTableau
	 */
	public VueListeTableau() {
		this.setStyle(
				"-fx-padding: 10;" +
				"-fx-spacing: 10;" +
				"-fx-border-style: solid; -fx-border-color: black; -fx-border-width: 1 1 1 1;" +
				"-fx-background-color: #baa898"
		);

		this.setPrefWidth(WIDTH);
	}

	/**
	 * Actualise la vue courante
	 * @param sujet le modèle à partir duquel la vue est actualisée
	 */
	@Override
	public void actualiser(Sujet sujet) {
		ModeleOllert modele = (ModeleOllert) sujet;
		Page page = modele.getDonnee();
		HBox parent = (HBox) this.getParent();
		int indice = parent.getChildren().indexOf(this);

		// maj centre
		ScrollPane scrollPane = (ScrollPane) this.getChildren().get(1);
		VBox listeTaches = (VBox) scrollPane.getContent();

		// suppression du separateur si il existe
		for (int i = 0; i < listeTaches.getChildren().size(); i++) {
			Node n = listeTaches.getChildren().get(i);
			if (n instanceof Separator) {
				listeTaches.getChildren().remove(i);
			}
		}

		if (modele.getDraggedTache() != null)
			for (Node n : listeTaches.getChildren())
				((VueTacheTableauAbstraite) n).actualiser(sujet);

		if(PlaceurSeparateur.placerSeparateur(modele, listeTaches, this))
			return;

		ListeTaches lt = page.getListeTaches(indice);
		listeTaches.getChildren().clear();
		for (Tache<?> t : lt.getTaches()) {
			VueTacheTableauPrincipale vt_tmp = new FabriqueVueTableau(modele).creerVueTache();
			listeTaches.getChildren().add(vt_tmp);
			vt_tmp.actualiser(sujet);
		}

		// maj top
		HBox top = (HBox) this.getChildren().get(0);
		Label titre = (Label) top.getChildren().get(0);
		titre.setText(page.getListes().get(indice).getTitre());

	}

	public List<Integer> getLocalisation() {
		ArrayList<Integer> loc = new ArrayList<>();
		Parent parent = this.getParent();
		loc.add(parent.getChildrenUnmodifiable().indexOf(this));
		return loc;
	}

	public Node getParentPrincipale() {
		return ((ScrollPane) this.getParent().getProperties().get("scrollPane")).getParent();
	}

	public VBox getChildrenPrincipale() {
		return (VBox) ((ScrollPane) this.getChildren().get(1)).getContent();
	}
}
