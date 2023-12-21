package mvc.vue.tache.contenu;

import javafx.scene.control.TextField;
import mvc.modele.Sujet;
import mvc.vue.Observateur;
import mvc.vue.tache.VueTache;

/**
 * Classe de la vue représentant le titre d'une tâche
 */
public class VueTitreTache extends TextField implements Observateur {
	/**
	 * actualise la vue courante
	 * @param sujet le modèle à partir duquel la vue est actualisée
	 */
	@Override
	public void actualiser(Sujet sujet) {
		VueTache vue = (VueTache) sujet;
		this.setText(vue.getTache().getTitre());
	}
}
