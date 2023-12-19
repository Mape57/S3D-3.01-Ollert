package mvc.fabrique;

import mvc.vue.liste.VueListe;
import mvc.vue.liste.VueListeTableau;
import mvc.vue.page.VuePage;
import mvc.vue.page.VuePageTableau;
import mvc.vue.tache.VueTache;
import mvc.vue.tache.VueTacheTableau;
import ollert.ListeTaches;
import ollert.Page;
import ollert.Tache;

public class FabriqueVueTableau implements FabriqueVue {
	@Override
	public VueTache creerVueTache(Tache tache) {
		return new VueTacheTableau(tache);
	}

	@Override
	public VueListe creerVueListe(ListeTaches liste) {
		return new VueListeTableau(liste);
	}

	@Override
	public VuePage creerVuePage(Page page) {
		return new VuePageTableau(page);
	}
}
