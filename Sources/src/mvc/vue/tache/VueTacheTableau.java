package mvc.vue.tache;

import javafx.scene.layout.GridPane;
import mvc.controleur.tache.ControlleurDrag;
import mvc.controleur.tache.ControlleurDragOutside;
import mvc.modele.ModeleOllert;
import mvc.modele.Sujet;
import mvc.vue.Observateur;
import mvc.vue.tache.contenu.*;
import ollert.tache.TachePrincipale;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe de la vue représentant une tâche sous forme de tableau
 * La vue est à la fois modèle (pour actualiser le contenu) et observateur (lors de la modification de son titre)
 */
public class VueTacheTableau extends GridPane implements VueTache {
	/**
	 * Liste des observateurs (les vues du contenu de la tâche (VueTitreTache par exemple))
	 */
	private List<Observateur> observateurs;
	/**
	 * Tâche réelle que représente la vue
	 */
	private TachePrincipale tache;

	/**
	 * Constructeur de la classe VueTacheTableau
	 * @param tache Tâche réelle que représente la vue
	 */
	public VueTacheTableau(TachePrincipale tache, ModeleOllert modeleControle) {
		this.observateurs = new ArrayList<>();
		this.tache = tache;

		this.setOnDragDetected(new ControlleurDrag(modeleControle));
		this.setOnDragOver(new ControlleurDragOutside(modeleControle));

		// Ajout des vues du contenu de la tâche
		VuePriorite vuePriorite = new VuePriorite();
		VueAjouterSousTache vueAjouterSousTache = new VueAjouterSousTache();
		VueDependance vueDependance = new VueDependance();
		VueCalendrier vueCalendrier = new VueCalendrier();
		VueTitre vueTitre = new VueTitre();
		VueMembres vueMembres = new VueMembres();
		VueEtiquettes vueEtiquettes = new VueEtiquettes();
		this.addRow(0, vuePriorite, vueAjouterSousTache, vueDependance, vueCalendrier);
		this.addRow(1, vueTitre);
		this.addRow(2, vueEtiquettes, vueMembres);
		GridPane.setColumnSpan(vueTitre, this.getColumnCount());

		// Ajout des vues comme observateurs de la tâche
		this.ajouterObservateur(vuePriorite);
		this.ajouterObservateur(vueAjouterSousTache);
		this.ajouterObservateur(vueDependance);
		this.ajouterObservateur(vueCalendrier);
		this.ajouterObservateur(vueTitre);
		this.ajouterObservateur(vueMembres);
		this.ajouterObservateur(vueEtiquettes);

		this.setGridLinesVisible(true);
		this.setHgap(10);
		this.setVgap(10);
		this.setStyle("-fx-background-color: yellow; -fx-border-color: black; -fx-border-width: 2px;");

		// Mise à jour initiale du contenu de la vue
		this.notifierObservateurs();
	}

	/**
	 * Ajoute un observateur à la liste des observateurs
	 * @param observateur L'observateur à ajouter
	 */
	@Override
	public void ajouterObservateur(Observateur observateur) {
		this.observateurs.add(observateur);
	}

	/**
	 * Supprime un observateur de la liste des observateurs
	 * @param observateur L'observateur à supprimer
	 */
	@Override
	public void supprimerObservateur(Observateur observateur) {
		this.observateurs.remove(observateur);
	}

	/**
	 * Notifie les observateurs de la liste des observateurs
	 */
	@Override
	public void notifierObservateurs() {
		for (Observateur observateur : this.observateurs)
			observateur.actualiser(this);
	}

	/**
	 * Actualise la vue courante
	 * @param sujet le modèle à partir duquel la vue est actualisée
	 */
	@Override
	public void actualiser(Sujet sujet) {
		this.notifierObservateurs();
	}

	/**
	 * @return tache réelle que représente la vue
	 */
	public TachePrincipale getTache() {
		return this.tache;
	}
}
