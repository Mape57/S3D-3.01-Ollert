package mvc.vue.tache.contenu;

import javafx.scene.control.Label;
import javafx.scene.text.Font;
import mvc.modele.Sujet;
import mvc.vue.Observateur;
import mvc.vue.tache.VueTache;

/**
 * Classe de la vue représentant le titre d'une tâche
 */
public class VueTitre extends Label implements Observateur {
	/**
	 * Constructeur de la classe VueTitre
	 */
	public VueTitre() {
		this.setHeight(20);
		this.setWidth(40);
		this.setFont(new Font("Arial", 15));
		this.setWrapText(true);
	}

	/**
	 * Actualise la vue courante
	 * @param sujet le modèle à partir duquel la vue est actualisée
	 */
	@Override
	public void actualiser(Sujet sujet) {
		VueTache vue = (VueTache) sujet;
		this.setText(vue.getTache().getTitre());
	}
}
