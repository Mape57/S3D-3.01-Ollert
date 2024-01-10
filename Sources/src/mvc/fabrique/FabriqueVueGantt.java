package mvc.fabrique;

import javafx.scene.layout.HBox;
import mvc.modele.ModeleOllert;
import mvc.vue.liste.VueListeTableau;
import mvc.vue.page.ParentScrollPane;
import mvc.vue.page.VuePageGantt;
import mvc.vue.tache.VueTache;
import mvc.vue.tache.tableau.VueTacheTableauPrincipale;

/**
 * Implementation de la FabriqueVue pour un affichage en tableau
 * : affichage classique (colonnes)
 */
public class FabriqueVueGantt extends FabriqueVue {

    public FabriqueVueGantt(ModeleOllert modeleOllert) {
        super(modeleOllert);
    }

    /**
     * Crée la vue d'une page sous forme de tableau
     * @return Vue de la page
     */
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

    /**
     * Crée la vue d'une liste sous forme de tableau
     * @return Vue de la liste
     */
    @Override
    public VueListeTableau creerVueListe() {
        return null;
    }

    /**
     * Crée la vue d'une tache sous forme de tableau
     * @return Vue de la tache
     */
    @Override
    public VueTacheTableauPrincipale creerVueTache() {
        return null;
    }

    @Override
    public VueTache creerVueSousTache() {
        return null;
    }
}
