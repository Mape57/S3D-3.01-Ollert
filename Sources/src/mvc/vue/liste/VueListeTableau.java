package mvc.vue.liste;

import javafx.scene.Node;
import javafx.scene.layout.VBox;
import mvc.ModeleOllert;
import mvc.Sujet;
import mvc.vue.Observateur;
import mvc.vue.liste.contenu.VueTitreListe;
import mvc.vue.page.VuePageTableau;
import mvc.vue.tache.VueTache;
import mvc.vue.tache.VueTacheTableau;
import ollert.ListeTaches;
import ollert.Tache;
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
		ModeleOllert modele = (ModeleOllert) sujet;
		System.out.println("actualisation");
		// LE PREMIER CHILDREN EST LE TITRE DE LA TACHE
		for (int i = 1; i < this.liste.obtenirNbTache() + 1; i++) {
			TachePrincipale l = this.liste.obtenirTache(i - 1);
			if (i < this.getChildren().size()) {
				VueTache vt = (VueTache) this.getChildren().get(i);
				if (!vt.getTache().equals(l)) {
					System.out.println("creation if");
					VueTache vl_tmp = modele.getFabrique().creerVueTache(l);
					this.getChildren().add(i, (Node) vl_tmp);
				}
				System.out.println("wtf");
				vt.actualiser(modele);
			} else {
				System.out.println("creation catch");
				VueTache vl_tmp = modele.getFabrique().creerVueTache(l);
				this.getChildren().add((Node) vl_tmp);
			}
		}
		this.notifierObservateurs();
	}

	public ListeTaches getListe() {
		return this.liste;
	}
}
