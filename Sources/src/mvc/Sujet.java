package mvc;

import mvc.vue.Observateur;

public interface Sujet {
	void ajouterObservateur(Observateur observateur);
	void supprimerObservateur(Observateur observateur);
	void notifierObservateurs();
}
