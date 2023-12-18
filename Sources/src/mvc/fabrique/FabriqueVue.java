package mvc.fabrique;

import mvc.vue.liste.VueListe;
import mvc.vue.page.VuePage;
import mvc.vue.tache.VueTache;

public interface FabriqueVue {
	VueTache creerVueTache();
	VueListe creerVueListe();
	VuePage creerVuePage();
}
