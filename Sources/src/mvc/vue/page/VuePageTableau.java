package mvc.vue.page;

import javafx.scene.layout.HBox;
import mvc.Sujet;
import mvc.vue.Observateur;
import mvc.vue.tache.contenu.VueTitreTache;
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
		this.notifierObservateurs();
	}

	public Page getPage() {
		return this.page;
	}
}
