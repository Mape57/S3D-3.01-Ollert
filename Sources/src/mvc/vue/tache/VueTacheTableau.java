package mvc.vue.tache;

import javafx.scene.layout.GridPane;
import mvc.Sujet;
import mvc.vue.Observateur;
import ollert.Tache;

import java.util.ArrayList;
import java.util.List;

public class VueTacheTableau extends GridPane implements VueTache {
	private List<Observateur> observateurs;
	private Tache tache;

	public VueTacheTableau(Tache tache) {
		this.observateurs = new ArrayList<>();
		this.tache = tache;
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

	public Tache getTache() {
		return this.tache;
	}
}
