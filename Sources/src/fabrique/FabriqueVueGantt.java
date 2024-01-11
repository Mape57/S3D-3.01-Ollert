package fabrique;

import javafx.scene.layout.HBox;
import mvc.ModeleOllert;
import mvc.vue.structure.VueListe;
import ollert.tool.ParentScrollPane;
import mvc.vue.principale.gantt.VuePageGantt;
import mvc.vue.structure.VueTache;

public class FabriqueVueGantt extends FabriqueVue {

    public FabriqueVueGantt(ModeleOllert modeleOllert) {
        super(modeleOllert);
    }

    @Override
    public VuePageGantt creerVuePage() {
        VuePageGantt vuePageGantt = new VuePageGantt();

        // centre de la page
        ParentScrollPane centre = new ParentScrollPane();
        centre.setContentAndChildrenProp(new HBox());

        vuePageGantt.setFillHeight(true);
        vuePageGantt.getChildren().add(centre);

        return vuePageGantt;
    }

    @Override
    public VueListe creerVueListe() {
        return null;
    }

    @Override
    public VueTache creerVueTache() {
        return null;
    }

    @Override
    public VueTache creerVueSousTache() {
        return null;
    }
}
