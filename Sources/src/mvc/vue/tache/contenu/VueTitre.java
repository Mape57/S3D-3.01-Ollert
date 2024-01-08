package mvc.vue.tache.contenu;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import mvc.modele.ModeleOllert;
import mvc.modele.Sujet;
import mvc.vue.Observateur;
import mvc.vue.liste.VueListe;
import mvc.vue.tache.VueTache;
import ollert.tache.TachePrincipale;

/**
 * Classe de la vue représentant le titre d'une tâche
 */
public class VueTitre extends TextField implements Observateur {
	/**
	 * Constructeur de la classe VueTitre
	 */
	public VueTitre() {
		this.setHeight(20);
		this.setWidth(40);
		this.setFont(new Font("Arial", 15));
		this.setEditable(false);
		//this.setPromptText();
	}

	/**
	 * Actualise la vue courante
	 * @param sujet le modèle à partir duquel la vue est actualisée
	 */
	@Override
	public void actualiser(Sujet sujet) {
		ModeleOllert modele = (ModeleOllert) sujet;
		VueTache vueTache = (VueTache) this.getParent();
		TachePrincipale tache = (TachePrincipale) modele.getParent(vueTache.getLocalisation());
		this.setText(tache.getTitre());
	}
}
