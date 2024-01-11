package mvc.vue.structure;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;

import java.util.List;

/**
 * Interface de base permettant l'affichage d'un groupe d'element
 * Sujet permettant l'ajout de sous-observateur
 * Observateur permettant l'actualisation de la VuePrincipale en elle-meme
 */
public interface VuePrincipale extends Observateur {
	/**
	 * @return La liste des enfants de la vue
	 */
	ObservableList<Node> getChildren();

	/**
	 * Methode d'obtention du Node enfant le plus proche contenant d'autre VuePrincipale
	 *
	 * @return Node possedant des VuePrincipales
	 */
	Node getChildrenPrincipale();

	/**
	 * @return Le parent de la vue
	 */
	Parent getParent();

	/**
	 * Methode d'obtention le parent le plus proche etant une VuePrincipale
	 *
	 * @return
	 */
	Node getParentPrincipale();

	/**
	 * Methode calculant la liste d'indices correspondant au parcours a realiser pour l'atteindre
	 * Cette méthode doit respecter la symétrie donnée/vue
	 * Peut etre utiliser en complement de ModeleOllert.getTache(List<Integer>)
	 *
	 * @return liste d'indices indiquant le chemin pour atteindre this
	 */
	List<Integer> getLocalisation();
}
