package mvc.vue;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import mvc.modele.Sujet;

/**
 * Interface de base permettant l'affichage d'un groupe d'element
 * Sujet permettant l'ajout de sous-observateur
 * Observateur permettant l'actualisation de la VuePrincipale en elle-meme
 */
public interface VuePrincipale extends Observateur, Sujet {
	ObservableList<Node> getChildren();
	Parent getParent();
}
