package mvc.controleur.tache;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import mvc.modele.ModeleOllert;
import mvc.vue.secondaire.VueAntecedents;
import mvc.vue.structure.VueTache;
import ollert.donnee.tache.TachePrincipale;

/**
 * Contrôleur de la première partie d'ajout d'antécédents (clic sur les menottes) à une tâche pour indiquer que cette tâche va être dépendante d'une autre
 */
public class ControleurAntecedents implements EventHandler<ActionEvent> {

	/**
	 * Modele de l'application
	 */
	private final ModeleOllert modele;

	/**
	 * Constructeur du contrôleur
	 *
	 * @param modeleControle Modele de l'application
	 */
	public ControleurAntecedents(ModeleOllert modeleControle) {
		this.modele = modeleControle;
	}

	/**
	 * Gère l'ajout d'antécédents à une tâche (clic sur les menottes)
	 *
	 * @param event action de l'utilisateur
	 */
	@Override
	public void handle(ActionEvent event) {
		TachePrincipale t = (TachePrincipale) this.modele.getTache(((VueTache) ((VueAntecedents) event.getSource()).getParent()).getLocalisation());

		if (modele.getListeAnt() == null) {
			modele.setListeAnt(t.getAntecedents());
			modele.setTacheCible(t);
		} else {
			if (modele.getTacheCible() == t) {
				modele.setTacheCible(null);
				modele.setListeAnt(null);
			} else {
				modele.setListeAnt(t.getAntecedents());
				modele.setTacheCible(t);
			}
		}

		modele.notifierObservateurs();
	}
}
