package mvc.vue.page;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import mvc.ModeleOllert;
import mvc.Sujet;
import mvc.fabrique.FabriqueVue;
import mvc.vue.Observateur;
import mvc.vue.liste.VueListe;
import ollert.ListeTaches;
import ollert.Page;

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
		ModeleOllert modele = (ModeleOllert) sujet;
		for (int i = 0; i < this.page.obtenirNbListe(); i++) {
			ListeTaches l = this.page.obtenirListe(i);

			if (i < this.getChildren().size()) {
				VueListe vl = (VueListe) this.getChildren().get(i);
				if (!vl.getListe().equals(l)) {
					VueListe vl_tmp = modele.getFabrique().creerVueListe(l);
					this.getChildren().add(i, (Node) vl_tmp);
				}
				vl.actualiser(modele);
			} else {
				VueListe vl_tmp = modele.getFabrique().creerVueListe(l);
				this.getChildren().add((Node) vl_tmp);
			}
		}

		this.notifierObservateurs();
	}

	public Page getPage() {
		return this.page;
	}
}
