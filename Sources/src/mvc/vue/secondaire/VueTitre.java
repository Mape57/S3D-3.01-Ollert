package mvc.vue.secondaire;

import javafx.scene.control.Label;
import javafx.scene.text.Font;
import mvc.vue.structure.Observateur;
import mvc.vue.structure.Sujet;
import mvc.vue.structure.VuePrincipale;
import mvc.ModeleOllert;
import ollert.donnee.tache.TacheAbstraite;

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
		// show all the text
		this.setWrapText(true);
	}

	/**
	 * Actualise la vue courante
	 * @param sujet le modèle à partir duquel la vue est actualisée
	 */
	@Override
	public void actualiser(Sujet sujet) {
		ModeleOllert modele = (ModeleOllert) sujet;
		VuePrincipale vueTache = (VuePrincipale) this.getParent();
		TacheAbstraite<?> tache = modele.getTache(vueTache.getLocalisation());
		this.setText(tache.getTitre());
	}
}
