package mvc.vue.liste;

import javafx.scene.Node;
import javafx.scene.layout.VBox;
import mvc.modele.ModeleOllert;
import mvc.modele.Sujet;
import mvc.vue.Observateur;
import mvc.vue.liste.contenu.VueTitreListe;
import mvc.vue.tache.VueTache;
import ollert.tache.ListeTaches;
import ollert.tache.TachePrincipale;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe de la vue représentant une liste de tâches sous forme de tableau
 */
public class VueListeTableau extends VBox implements VueListe {
	/**
	 * Liste des observateurs (les vues des tâches de la liste (VueTacheTableau))
	 */
	private List<Observateur> observateurs;
	/**
	 * Liste de tâches réelle que représente la vue
	 */
	private ListeTaches liste;

	/**
	 * Constructeur de la classe VueListeTableau
	 * @param liste Liste de tâches réelle que représente la vue
	 */
	public VueListeTableau(ListeTaches liste) {
		this.observateurs = new ArrayList<>();
		this.liste = liste;

		VueTitreListe vtl = new VueTitreListe();
		this.getChildren().add(vtl);
		this.observateurs.add(vtl);
	}

	/**
	 * Ajoute un observateur à la liste des observateurs
	 * @param observateur L'observateur à ajouter
	 */
	@Override
	public void ajouterObservateur(Observateur observateur) {
		this.observateurs.add(observateur);
	}

	/**
	 * Supprime un observateur de la liste des observateurs
	 * @param observateur L'observateur à supprimer
	 */
	@Override
	public void supprimerObservateur(Observateur observateur) {
		this.observateurs.remove(observateur);
	}

	/**
	 * Notifie les observateurs de la liste des observateurs
	 */
	@Override
	public void notifierObservateurs() {
		for (Observateur observateur : this.observateurs)
			observateur.actualiser(this);
	}

	/**
	 * Actualise la vue courante
	 * @param sujet le modèle à partir duquel la vue est actualisée
	 */
	@Override
	public void actualiser(Sujet sujet) {
		// FIXME erreur lors de la mise a jour, surplus du nombre d'enfant
		ModeleOllert modele = (ModeleOllert) sujet;
		// LE PREMIER CHILDREN EST LE TITRE DE LA TACHE
		for (int i = 1; i < this.liste.sizeListe() + 1; i++) {
			TachePrincipale l = this.liste.getTache(i - 1);
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

	/**
	 * @return La liste de tâches réelle que représente la vue
	 */
	public ListeTaches getListe() {
		return this.liste;
	}
}
