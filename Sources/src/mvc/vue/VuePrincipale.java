package mvc.vue;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import mvc.Sujet;

public interface VuePrincipale extends Observateur, Sujet {
	ObservableList<Node> getChildren();
	Parent getParent();
}
