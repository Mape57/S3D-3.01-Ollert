package mvc.fabrique;

import mvc.modele.ModeleOllert;
import mvc.vue.liste.VueListeTableau;
import mvc.vue.page.VuePageGantt;
import mvc.vue.sousTache.VueSousTache;
import mvc.vue.tache.VueTacheTableau;

/**
 * Implementation de la FabriqueVue pour un affichage en tableau
 * : affichage classique (colonnes)
 */
public class FabriqueVueGantt implements FabriqueVue {

    private ModeleOllert modeleOllert;

    public FabriqueVueGantt(ModeleOllert modeleOllert) {
        this.modeleOllert = modeleOllert;
    }

    /**
     * Crée la vue d'une tache sous forme de tableau
     * @return Vue de la tache
     */
    @Override
    public VueTacheTableau creerVueTache() {
        return null;
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
     * Crée la vue d'une page sous forme de tableau
     * @return Vue de la page
     */
    @Override
    public VuePageGantt creerVuePage() {
        return new VuePageGantt();
    }

    @Override
    public VueSousTache creerVueSousTache() {
        return null;
    }
}
