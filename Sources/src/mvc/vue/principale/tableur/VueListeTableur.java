package mvc.vue.principale.tableur;

import mvc.vue.structure.VueListe;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import fabrique.FabriqueVueTableur;
import mvc.vue.structure.Sujet;
import mvc.ModeleOllert;
import ollert.donnee.Page;
import ollert.donnee.ListeTaches;
import ollert.donnee.tache.TacheAbstraite;

import java.util.ArrayList;
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
		for (TacheAbstraite t : lt.getTaches()) {
			VueTacheTableur vt_tmp = new FabriqueVueTableur(modele).creerVueTache();
			centre.getChildren().add(vt_tmp);
			vt_tmp.actualiser(modele);
		}


	}

	@Override
	public VBox getChildrenPrincipale() {
		return (VBox) this.getChildren().get(this.getChildren().size() - 1);
	}

	@Override
	public VuePageTableur getParentPrincipale() {
		return (VuePageTableur) this.getParent().getProperties().get("scrollPane");
	}

	@Override
	public List<Integer> getLocalisation() {
		ArrayList<Integer> loc = new ArrayList<>();
		Parent parent = this.getParent();
		loc.add(parent.getChildrenUnmodifiable().indexOf(this));
		return loc;
	}
}
