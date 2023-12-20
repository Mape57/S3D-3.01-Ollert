package mvc.vue;

import mvc.modele.Sujet;

public interface Observateur {
	/**
	 *Méthode appelée pour mettre à jour la vue lorsque le sujet observé change.
	 * @param sujet sujet observé
	 */
	void actualiser(Sujet sujet);
}
