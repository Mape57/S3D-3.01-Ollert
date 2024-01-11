package mvc.vue.principale.tableau;

import fabrique.FabriqueVueTableau;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import mvc.modele.ModeleOllert;
import mvc.modele.Sujet;
import mvc.vue.principale.tableau.tache.VueTacheTableau;
import mvc.vue.principale.tableau.tache.VueTacheTableauAbstraite;
import mvc.vue.structure.VueListe;
import ollert.donnee.ListeTaches;
import ollert.donnee.Page;
import ollert.donnee.tache.Tache;
import ollert.tool.PlaceurSeparateur;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe de la vue représentant une liste de tâches sous forme de tableau
 */
public class VueListeTableau extends VBox implements VueListe {
	/**
	 * Largeur d'une liste
	 */
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
	 *
	 * @param sujet le modèle à partir duquel la vue est actualisée
	 */
	@Override
	public void actualiser(Sujet sujet) {
		ModeleOllert modele = (ModeleOllert) sujet;
		Page page = modele.getDonnee();
		HBox parent = (HBox) this.getParent();
		int indice = parent.getChildren().indexOf(this);

		ScrollPane scrollPane = (ScrollPane) this.getChildren().get(1);
		VBox listeTaches = (VBox) scrollPane.getContent();

		// suppression du separateur si il existe
		for (int i = 0; i < listeTaches.getChildren().size(); i++) {
			Node n = listeTaches.getChildren().get(i);
			if (n instanceof Separator) {
				listeTaches.getChildren().remove(i);
				break;
			}
		}

		// si on a une tache en deplacement on actualise simplement le contenu (pas de suppression)
		if (modele.getDraggedTache() != null)
			for (Node n : listeTaches.getChildren())
				((VueTacheTableauAbstraite) n).actualiser(sujet);

		// on ajoute le separateur si besoin et on quitte si on l'ajoute
		if (PlaceurSeparateur.placerSeparateur(modele, listeTaches, this))
			return;

		// on supprime et recrée les taches
		ListeTaches lt = page.getListeTaches(indice);
		listeTaches.getChildren().clear();
		for (Tache<?> t : lt.getTaches()) {
			VueTacheTableau vt_tmp = new FabriqueVueTableau(modele).creerVueTache();
			listeTaches.getChildren().add(vt_tmp);
			vt_tmp.actualiser(sujet);
		}

		// on met a jour le titre
		HBox top = (HBox) this.getChildren().get(0);
		Label titre = (Label) top.getChildren().get(0);
		titre.setText(page.getListes().get(indice).getTitre());

	}

	@Override
	public List<Integer> getLocalisation() {
		ArrayList<Integer> loc = new ArrayList<>();
		Parent parent = this.getParent();
		loc.add(parent.getChildrenUnmodifiable().indexOf(this));
		return loc;
	}

	@Override
	public Node getParentPrincipale() {
		return ((ScrollPane) this.getParent().getProperties().get("scrollPane")).getParent();
	}

	@Override
	public VBox getChildrenPrincipale() {
		return (VBox) ((ScrollPane) this.getChildren().get(1)).getContent();
	}
}
