package mvc.vue.page;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import mvc.modele.ModeleOllert;
import mvc.modele.Sujet;
import mvc.vue.Observateur;
import mvc.vue.liste.VueListe;
import ollert.Page;
import ollert.tache.ListeTaches;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe de la vue représentant une page sous forme de tableau
 */
public class VuePageTableau extends HBox implements VuePage {
	/**
	 * Liste des observateurs (les vues des listes de la page (VueListeTableau))
	 */
	private List<Observateur> observateurs;
	/**
	 * Page réelle que représente la vue
	 */
	private Page page;

	/**
	 * Constructeur de la classe VuePageTableau
	 * @param page Page réelle que représente la vue
	 */
	public VuePageTableau(Page page) {
		this.observateurs = new ArrayList<>();
		this.page = page;
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
	 * Notifie les observateurs de la mise à jour de la vue
	 */
	@Override
	public void notifierObservateurs() {
		for (Observateur observateur : this.observateurs)
			observateur.actualiser(this);
	}

	/**
	 * Actualise la vue
	 * @param sujet le modèle à partir duquel la vue est actualisée
	 */
	@Override
	public void actualiser(Sujet sujet) {
		// FIXME erreur lors de la mise a jour, surplus du nombre d'enfant
		ModeleOllert modele = (ModeleOllert) sujet;

		for (int i = 0; i < this.page.sizeListe(); i++) {
			ListeTaches l = this.page.getListeTaches(i);
			// si la taille de la liste le permet
			if (i < this.getChildren().size()) {
				VueListe vl = (VueListe) this.getChildren().get(i);
				// si les listes sont differentes ils faut en ajouter une
				if (!vl.getListe().equals(l)) {
					VueListe vl_tmp = modele.getFabrique().creerVueListe(l);
					this.getChildren().add(i, (Node) vl_tmp);
				}
				vl.actualiser(modele);
			} else {
				// on creer la liste
				VueListe vl_tmp = modele.getFabrique().creerVueListe(l);
				this.getChildren().add((Node) vl_tmp);
				vl_tmp.actualiser(modele);
			}
		}

		this.notifierObservateurs();
	}

	/**
	 * @return page réelle que représente la vue
	 */
	public Page getPage() {
		return this.page;
	}
}
