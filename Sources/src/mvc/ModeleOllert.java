package mvc;

import mvc.fabrique.FabriqueVue;
import mvc.fabrique.FabriqueVueTableau;
import mvc.vue.Observateur;
import ollert.Page;

import java.util.ArrayList;
import java.util.List;

public class ModeleOllert implements Sujet {
	private Page donnee;
	private List<Observateur> observateurs;
	private FabriqueVue fabrique;

	public ModeleOllert() {
		this.donnee = new Page("defaut");
		this.observateurs = new ArrayList<>();
		this.fabrique = new FabriqueVueTableau();
	}

	public void setFabrique(FabriqueVue fabrique) {
		this.fabrique = fabrique;
		this.notifierObservateurs();
	}

	public FabriqueVue getFabrique() {
		return this.fabrique;
	}

	public void setDonnee(Page donnee) {
		this.donnee = donnee;
		this.notifierObservateurs();
	}

	public Page getDonnee() {
		return this.donnee;
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
}
