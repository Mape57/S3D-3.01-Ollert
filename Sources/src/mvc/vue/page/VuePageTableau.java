package mvc.vue.page;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import mvc.modele.ModeleOllert;
import mvc.modele.Sujet;
import mvc.vue.Observateur;
import mvc.vue.liste.VueListe;
import ollert.Page;
import ollert.tache.ListeTaches;

import java.util.ArrayList;
import java.util.List;

public class VuePageTableau extends HBox implements VuePage {
	private List<Observateur> observateurs;
	private Page page;

	public VuePageTableau(Page page) {
		this.observateurs = new ArrayList<>();
		this.page = page;
	}

	@Override
	public void ajouterObservateur(Observateur observateur) {
		this.observateurs.add(observateur);
	}

	@Override
	public void supprimerObservateur(Observateur observateur) {
		this.observateurs.remove(observateur);
	}

	@Override
	public void notifierObservateurs() {
		for (Observateur observateur : this.observateurs)
			observateur.actualiser(this);
	}

	@Override
	public void actualiser(Sujet sujet) {
		// FIXME erreur lors de la mise a jour, surplus du nombre d'enfant
		ModeleOllert modele = (ModeleOllert) sujet;

		for (int i = 0; i < this.page.sizeListe(); i++) {
			ListeTaches l = this.page.getListe(i);
			// si la taille de la liste le permet
			if (i < this.getChildren().size()) {
				VueListe vl = (VueListe) this.getChildren().get(i);
				// si les listes sont differentes ils faut en ajouter une
				if (!vl.getListe().equals(l)) {
					VueListe vl_tmp = modele.getFabrique().creerVueListe(l);
					this.getChildren().add(i, (Node) vl_tmp);
				}
				vl.actualiser(modele);
			} else {
				// on creer la liste
				VueListe vl_tmp = modele.getFabrique().creerVueListe(l);
				this.getChildren().add((Node) vl_tmp);
				vl_tmp.actualiser(modele);
			}
		}

		this.notifierObservateurs();
	}

	public Page getPage() {
		return this.page;
	}
}
