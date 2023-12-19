package mvc.vue.liste;

import javafx.scene.layout.VBox;
import mvc.Sujet;
import mvc.vue.Observateur;
import ollert.ListeTaches;

import java.util.ArrayList;
import java.util.List;

public class VueListeTableau extends VBox implements VueListe {
	private List<Observateur> observateurs;
	private ListeTaches liste;

	public VueListeTableau(ListeTaches liste) {
		this.observateurs = new ArrayList<>();
		this.liste = liste;
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

	public ListeTaches getListe() {
		return this.liste;
	}
}
