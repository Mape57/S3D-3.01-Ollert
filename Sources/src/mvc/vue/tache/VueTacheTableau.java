package mvc.vue.tache;

import javafx.scene.layout.GridPane;
import mvc.modele.Sujet;
import mvc.vue.Observateur;
import mvc.vue.tache.contenu.VueTitreTache;
import ollert.TachePrincipale;

import java.util.ArrayList;
import java.util.List;

public class VueTacheTableau extends GridPane implements VueTache {
	private List<Observateur> observateurs;
	private TachePrincipale tache;

	public VueTacheTableau(TachePrincipale tache) {
		this.observateurs = new ArrayList<>();
		this.tache = tache;

		// Ajout du titre de la tache
		VueTitreTache vtl = new VueTitreTache();
		this.getChildren().add(vtl);
		this.ajouterObservateur(vtl);
		VueTitreTache vtl2 = new VueTitreTache();
		this.getChildren().add(vtl2);
		this.ajouterObservateur(vtl2);
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
