package mvc.vue.liste;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import mvc.controleur.liste.*;
import mvc.controleur.page.ControlleurGantt;
import mvc.fabrique.FabriqueVueTableau;
import mvc.fabrique.FabriqueVueTableur;
import mvc.modele.ModeleOllert;
import mvc.modele.Sujet;
import mvc.vue.page.ParentScrollPane;
import mvc.vue.tache.VueTacheTableau;
import mvc.vue.tache.VueTacheTableur;
import ollert.Page;
import ollert.tache.ListeTaches;
import ollert.tache.Tache;
import ollert.tache.TachePrincipale;
import ollert.tache.donneesTache.Etiquette;
import ollert.tache.donneesTache.Priorite;
import ollert.tache.donneesTache.Utilisateur;

import java.util.List;

/**
 * Classe de la vue représentant une liste de tâches sous forme de tableau
 */
public class VueListeTableur extends VBox implements VueListe {

	/**
	 * Constructeur de la classe VueListeTableau
	 */
	public VueListeTableur() {

		this.setStyle("-fx-background-color: white; -fx-padding: 20;");

	}

	/**
	 * Actualise la vue courante
	 * @param sujet le modèle à partir duquel la vue est actualisée
	 */
	@Override
	public void actualiser(Sujet sujet) {
		ModeleOllert modele = (ModeleOllert) sujet;
		Page page = modele.getDonnee();
		VBox parent = (VBox) this.getParent();
		int indice = parent.getChildren().indexOf(this);

		// maj bandeau
		HBox bandeau = (HBox) this.getChildren().get(0);
		HBox titre = (HBox) bandeau.getChildren().get(0);
		Label l1 = (Label) titre.getChildren().get(0);
		l1.setText(page.getListeTaches(indice).getTitre());


		// maj contenu
		ListeTaches lt = page.getListeTaches(indice);
		VBox centre = (VBox) this.getChildren().get(1);
		centre.getChildren().clear();
		for (Tache t : lt.getTaches()) {
			VueTacheTableur vt_tmp = new FabriqueVueTableur(modele).creerVueTache();
			centre.getChildren().add(vt_tmp);
			vt_tmp.actualiser(modele);
		}


	}

	@Override
	public Node getChildrenPrincipale() {
		return null;
	}

	@Override
	public Node getParentPrincipale() {
		return null;
	}

	@Override
	public List<Integer> getLocalisation() {
		return null;
	}
}
