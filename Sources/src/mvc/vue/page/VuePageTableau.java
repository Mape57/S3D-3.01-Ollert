package mvc.vue.page;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import mvc.controleur.liste.ControlleurModifierTitre;
import mvc.controleur.page.ControlleurAjouterListe;
import mvc.controleur.page.ControlleurTableau;
import mvc.controleur.page.ControlleurTableur;
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
	 * Constructeur de la classe VuePageTableau
	 */
	public VuePageTableau(ModeleOllert modeleControle) {

		// centre de la page
		// la classe ParentScrollPane permet de creer un lien avec son contenu allant dans les deux sens
		// ainsi en recuperant la propriete "scrollPane" d'un noeud, on peut recuperer le scrollPane qui le contient
		class ParentScrollPane extends ScrollPane {
			public void setContentAndChildrenProp(Node node) {
				super.setContent(node);
				node.getProperties().put("scrollPane", this);
			}
		}

		ParentScrollPane centre = new ParentScrollPane();
		centre.setContentAndChildrenProp(new HBox());

		centre.setFitToHeight(true);
		this.getChildren().add(centre);
	}

	/**
	 * Actualise la vue
	 * @param sujet le modèle à partir duquel la vue est actualisée
	 */
	@Override
	public void actualiser(Sujet sujet) {
		ModeleOllert modele = (ModeleOllert) sujet;
		HBox centre = (HBox) ((ScrollPane) this.getChildren().get(0)).getContent();
		centre.getChildren().clear();

		Page page = (Page)modele.getDonnee();
		List<ListeTaches> liste = page.getListes();

		for (ListeTaches l : liste) {
			VueListeTableau vl_tmp = new FabriqueVueTableau().creerVueListe(modele);
			centre.getChildren().add(vl_tmp);
			vl_tmp.actualiser(modele);
		}
	}

	/**
	 * @return page réelle que représente la vue
	 */
	public Page getPage() {
		return null;
	}

	@Override
	public List<Integer> getLocalisation() {
		return new ArrayList<>();
	}
}
