package mvc.vue.liste;

import javafx.scene.Node;
import javafx.scene.layout.VBox;
import mvc.modele.ModeleOllert;
import mvc.modele.Sujet;
import mvc.vue.Observateur;
import mvc.vue.liste.contenu.VueTitreListe;
import mvc.vue.tache.VueTache;
import ollert.ListeTaches;
import ollert.TachePrincipale;

import java.util.ArrayList;
import java.util.List;

public class VueListeTableau extends VBox implements VueListe {
	private List<Observateur> observateurs;
	private ListeTaches liste;

	public VueListeTableau(ListeTaches liste) {
		this.observateurs = new ArrayList<>();
		this.liste = liste;

		VueTitreListe vtl = new VueTitreListe();
		this.getChildren().add(vtl);
		this.observateurs.add(vtl);
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
		// LE PREMIER CHILDREN EST LE TITRE DE LA TACHE
		for (int i = 1; i < this.liste.obtenirNbTache() + 1; i++) {
			TachePrincipale l = this.liste.obtenirTache(i - 1);
			if (i < this.getChildren().size()) {
				VueTache vt = (VueTache) this.getChildren().get(i);
				if (!vt.getTache().equals(l)) {
					VueTache vl_tmp = modele.getFabrique().creerVueTache(l);
					this.getChildren().add(i, (Node) vl_tmp);
				}
				vt.actualiser(modele);
			} else {
				VueTache vl_tmp = modele.getFabrique().creerVueTache(l);
				this.getChildren().add((Node) vl_tmp);
				vl_tmp.actualiser(modele);
			}
		}
		this.notifierObservateurs();
	}

	public ListeTaches getListe() {
		return this.liste;
	}
}
