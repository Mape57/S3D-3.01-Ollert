package mvc.vue.structure;

import mvc.modele.Sujet;

/**
 * Interface des observateurs (les vues) d'un sujet (les modèles)
 */
public interface Observateur {
	/**
	 * Méthode appelée pour mettre à jour la vue lorsque le sujet observé change.
	 *
	 * @param sujet sujet observé
	 */
	void actualiser(Sujet sujet);
}
