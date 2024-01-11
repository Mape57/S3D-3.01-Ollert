package mvc.vue.principale.tableau.tache;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import fabrique.FabriqueVueTableau;
import mvc.vue.structure.Observateur;
import mvc.modele.Sujet;
import mvc.vue.structure.VuePrincipale;
import mvc.vue.structure.VueTache;
import mvc.modele.ModeleOllert;
import ollert.tool.PlaceurSeparateur;
import ollert.donnee.tache.TacheAbstraite;

import java.util.ArrayList;
import java.util.List;

public abstract class VueTacheTableauAbstraite extends GridPane implements VueTache {
	@Override
	public void actualiser(Sujet sujet) {
		ModeleOllert modele = (ModeleOllert) sujet;
		List<Integer> indices = this.getLocalisation();


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

		for (int i = 0; i < this.getChildren().size() - 1; i++)
			((Observateur) this.getChildren().get(i)).actualiser(sujet);

		listeTaches.getChildren().clear();
		TacheAbstraite<?> tache = modele.getTache(indices);
		for (int i = 0; i < tache.getSousTaches().size(); i++) {
			VueSousTacheTableau vueSousTache = new FabriqueVueTableau(modele).creerVueSousTache();
			listeTaches.getChildren().add(vueSousTache);
			vueSousTache.actualiser(sujet);
		}
	}

	@Override
	public List<Integer> getLocalisation() {
		ArrayList<Integer> loc = new ArrayList<>();
		VuePrincipale parent;
		parent = (VuePrincipale) this.getParentPrincipale();
		loc.add(0, ((Parent) parent.getChildrenPrincipale()).getChildrenUnmodifiable().indexOf(this));
		loc.addAll(0, parent.getLocalisation());
		return loc;
	}

	@Override
	public Node getChildrenPrincipale() {
		return this.getChildren().get(this.getChildren().size() - 1);
	}
}
