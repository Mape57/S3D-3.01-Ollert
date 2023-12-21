package mvc.vue.tache;

import mvc.vue.VuePrincipale;
import ollert.tache.TachePrincipale;

/**
 * Interface des vues (tableau, tableur, Gantt) d'une tâche
 */
public interface VueTache extends VuePrincipale {
	/**
	 * @return La tâche réelle que représente la vue
	 */
	TachePrincipale getTache();
}
