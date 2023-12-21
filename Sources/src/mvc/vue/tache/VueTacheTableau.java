package mvc.vue.tache;

import javafx.scene.layout.GridPane;
import mvc.modele.Sujet;
import mvc.vue.Observateur;
import mvc.vue.tache.contenu.VueTitreTache;
import ollert.tache.TachePrincipale;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe de la vue représentant une tâche sous forme de tableau
 */
public class VueTacheTableau extends GridPane implements VueTache {
	/**
	 * Liste des observateurs (les vues du contenu de la tâche (VueTitreTache par exemple))
	 */
	private List<Observateur> observateurs;
	/**
	 * Tâche réelle que représente la vue
	 */
	private TachePrincipale tache;

	/**
	 * Constructeur de la classe VueTacheTableau
	 * @param tache Tâche réelle que représente la vue
	 */
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
		this.notifierObservateurs();
	}

	/**
	 * @return tache réelle que représente la vue
	 */
	public TachePrincipale getTache() {
		return this.tache;
	}
}
