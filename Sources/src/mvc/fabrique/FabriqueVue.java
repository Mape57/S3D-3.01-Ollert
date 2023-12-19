package mvc.fabrique;

import mvc.vue.liste.VueListe;
import mvc.vue.page.VuePage;
import mvc.vue.tache.VueTache;
import ollert.ListeTaches;
import ollert.Page;
import ollert.Tache;

public interface FabriqueVue {
	VueTache creerVueTache(Tache tache);
	VueListe creerVueListe(ListeTaches liste);
	VuePage creerVuePage(Page page);
}
