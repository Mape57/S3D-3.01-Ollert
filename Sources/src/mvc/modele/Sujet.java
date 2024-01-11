package mvc.modele;

import mvc.vue.structure.Observateur;

public interface Sujet {
	/**
	 * Ajoute un observateur à la liste des observateurs
	 *
	 * @param observateur L'observateur à ajouter
	 */
	void ajouterObservateur(Observateur observateur);

	/**
	 * Supprime un observateur de la liste des observateurs
	 *
	 * @param observateur L'observateur à supprimer
	 */
	void supprimerObservateur(Observateur observateur);

	/**
	 * Notifie tous les observateurs que les données ont changé
	 */
	void notifierObservateurs();
}
