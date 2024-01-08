package mvc.vue.tache;

import javafx.scene.layout.GridPane;
import mvc.modele.ModeleOllert;
import mvc.modele.Sujet;
import mvc.vue.tache.contenu.*;
import ollert.tache.TachePrincipale;

import java.util.List;

/**
 * Classe de la vue représentant une tâche sous forme de tableau
 * La vue est à la fois modèle (pour actualiser le contenu) et observateur (lors de la modification de son titre)
 */
public class VueTacheTableur extends GridPane implements VueTache {

	/**
	 * Constructeur de la classe VueTacheTableau
	 */
	public VueTacheTableur(ModeleOllert modeleControle) {
		//this.setOnDragDetected(new ControlleurDrag(modeleControle));
		//this.setOnDragOver(new ControlleurDragOutside(modeleControle));


		// Ajout des vues du contenu de la tâche
		VuePriorite vuePriorite = new VuePriorite();
		VueAjouterSousTache vueAjouterSousTache = new VueAjouterSousTache();
		VueDependance vueDependance = new VueDependance();
		VueCalendrier vueCalendrier = new VueCalendrier();
		VueTitreTacheComplete vueTitre = new VueTitreTacheComplete();
		VueMembres vueMembres = new VueMembres();
		VueEtiquettes vueEtiquettes = new VueEtiquettes();
		this.addRow(0, vuePriorite, vueAjouterSousTache, vueDependance, vueCalendrier);
		this.addRow(1, vueTitre);
		this.addRow(2, vueEtiquettes, vueMembres);
		GridPane.setColumnSpan(vueTitre, this.getColumnCount());

		//this.setGridLinesVisible(true);
		this.setHgap(10);
		this.setVgap(10);
		this.setStyle("-fx-background-color: yellow; -fx-border-color: black; -fx-border-width: 2px;");

		// Mise à jour initiale du contenu de la vue
		//this.notifierObservateurs();
	}

	/**
	 * Actualise la vue courante
	 * @param sujet le modèle à partir duquel la vue est actualisée
	 */
	@Override
	public void actualiser(Sujet sujet) {
		//this.notifierObservateurs();
	}

	/**
	 * @return tache réelle que représente la vue
	 */
	public TachePrincipale getTache() {
		return null;
	}

	@Override
	public List<Integer> getLocalisation() {
		return null;
	}
}
