package mvc.vue.tache.contenu;

import javafx.scene.control.TextArea;
import mvc.modele.Sujet;
import mvc.vue.Observateur;
import mvc.vue.tache.VueTache;

/**
 * Classe de la vue représentant le titre d'une tâche
 */
public class VueTitre extends TextArea implements Observateur {
	/**
	 * actualise la vue courante
	 * @param sujet le modèle à partir duquel la vue est actualisée
	 */
	@Override
	public void actualiser(Sujet sujet) {
		VueTache vue = (VueTache) sujet;
		this.setText(vue.getTache().getTitre());
		this.setHeight(20);
		this.setWidth(60);
		this.setEditable(false);
	}
}
