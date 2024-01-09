package mvc.vue.tache.contenu;

import javafx.scene.control.Label;
import javafx.scene.text.Font;
import mvc.modele.ModeleOllert;
import mvc.modele.Sujet;
import mvc.vue.Observateur;
import mvc.vue.VuePrincipale;
import mvc.vue.tache.VueTache;
import ollert.tache.Tache;
import ollert.tache.TachePrincipale;

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
		Tache<?> tache = modele.getTache(vueTache.getLocalisation());
		System.out.println("VueTitre : " + tache.getTitre());
		this.setText(tache.getTitre());
	}
}
