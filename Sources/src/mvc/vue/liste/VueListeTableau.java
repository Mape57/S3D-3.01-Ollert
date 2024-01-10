package mvc.vue.liste;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import mvc.controleur.liste.*;
import mvc.fabrique.FabriqueVueTableau;
import mvc.modele.ModeleOllert;
import mvc.modele.Sujet;
import mvc.vue.page.ParentScrollPane;
import mvc.vue.tache.VueTacheTableau;
import mvc.vue.tache.VueTacheTableauAbstraite;
import ollert.Page;
import ollert.tache.ListeTaches;
import ollert.tache.Tache;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
		VBox centre = (VBox) scrollPane.getContent();

		// suppression du separateur si il existe
		for (int i = 0; i < centre.getChildren().size(); i++) {
			Node n = centre.getChildren().get(i);
			if (n instanceof Separator) {
				centre.getChildren().remove(i);
				break;
			}
		}

		// copy modele.getIndicesDragged()
		List<Integer> indicesDragged = null;
		if (modele.getIndicesDragged() != null)
			indicesDragged = new ArrayList<>(modele.getIndicesDragged());

		// on ajoute le separateur si on est en drag
		if (modele.getDraggedTache() != null) {
			if (indicesDragged == null)
				return;

			// si le deplacement en vers la liste actuelle
			if (Objects.equals(indicesDragged.subList(0, indicesDragged.size() - 1), this.getLocalisation())) {
				indicesDragged.remove(0);
				Separator separator = new Separator();
				separator.setStyle("-fx-border-style: solid; -fx-border-width: 1px; -fx-background-color: black;");

				// si le deplacement est vers une liste vide
				if (centre.getChildren().isEmpty()) {
					centre.getChildren().add(separator);
					return;
				}

				if (indicesDragged.get(0) >= centre.getChildren().size()) {
					centre.getChildren().add(centre.getChildren().size(), separator);
					return;
				}

				// on recupere la vue de la tache precedente
				VueTacheTableauAbstraite n = (VueTacheTableauAbstraite) centre.getChildren().get(indicesDragged.get(0));
				if (indicesDragged.size() > 1)
					indicesDragged.remove(0);

				// si on recherche une sous tache on continue
				while (indicesDragged.size() > 1)
					n = (VueTacheTableauAbstraite) ((VBox) n.getChildrenPrincipale()).getChildren().get(indicesDragged.remove(0));

				// on ajoute le separateur
				((VBox) n.getParent()).getChildren().add(indicesDragged.get(0), separator);
			}
			// ne pas mettre a jour le contenu
			return;
		}

		ListeTaches lt = page.getListeTaches(indice);
		centre.getChildren().clear();
		for (Tache<?> t : lt.getTaches()) {
			VueTacheTableau vt_tmp = new FabriqueVueTableau(modele).creerVueTache();
			centre.getChildren().add(vt_tmp);
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
