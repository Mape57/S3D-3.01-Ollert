package mvc.vue.page;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;

/**
 *
 * Classe ParentScrollPane permet de creer un lien avec son contenu allant dans les deux sens
 * ainsi en recuperant la propriete "scrollPane" d'un noeud, on peut recuperer le scrollPane qui le contient
 */
public class ParentScrollPane extends ScrollPane {
    public void setContentAndChildrenProp(Node node) {
        super.setContent(node);
        node.getProperties().put("scrollPane", this);
    }
}