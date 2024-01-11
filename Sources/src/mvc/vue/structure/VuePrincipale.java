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

	Node getChildrenPrincipale();

	/**
	 * @return Le parent de la vue
	 */
	Parent getParent();

	Node getParentPrincipale();

	List<Integer> getLocalisation();
}
