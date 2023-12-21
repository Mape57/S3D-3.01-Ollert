package mvc.vue.liste;

import mvc.vue.VuePrincipale;
import ollert.tache.ListeTaches;

/**
 * Interface des vues (tableau, tableur, Gantt) d'une liste de tâches
 */
public interface VueListe extends VuePrincipale {
	/**
	 * @return La liste de tâches réelle que représente la vue
	 */
	ListeTaches getListe();
}
