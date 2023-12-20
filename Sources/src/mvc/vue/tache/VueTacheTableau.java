package mvc.vue.tache;

import javafx.scene.layout.GridPane;
import mvc.Sujet;
import mvc.vue.Observateur;
import mvc.vue.tache.contenu.VueTitreTache;
import ollert.TachePrincipale;

import java.util.ArrayList;
import java.util.List;

public class VueTacheTableau extends GridPane implements VueTache {
	private List<Observateur> observateurs;
	private TachePrincipale tache;

	public VueTacheTableau(TachePrincipale tache) {
		System.out.println("creation");
		this.observateurs = new ArrayList<>();
		this.tache = tache;

		VueTitreTache vtl = new VueTitreTache();
		this.getChildren().add(vtl);
		this.observateurs.add(vtl);
		this.notifierObservateurs();
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

	public TachePrincipale getTache() {
		return this.tache;
	}
}
