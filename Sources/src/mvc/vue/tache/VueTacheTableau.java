package mvc.vue.tache;

import javafx.scene.layout.GridPane;
import mvc.modele.Sujet;
import mvc.vue.Observateur;
import mvc.vue.tache.contenu.*;
import ollert.tache.TachePrincipale;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe de la vue représentant une tâche sous forme de tableau
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
	public VueTacheTableau(TachePrincipale tache, Sujet modeleControle) {
		this.observateurs = new ArrayList<>();
		this.tache = tache;

		// Ajout du titre de la tache
		VuePriorite vuePriorite = new VuePriorite();
		VueDeplacement vueDeplacement = new VueDeplacement();
		VueDependance vueDependance = new VueDependance();
		VueCalendrier vueCalendrier = new VueCalendrier();
		VueTitre vueTitre = new VueTitre();
		this.addRow(0, vuePriorite, vueDeplacement, vueDependance, vueCalendrier);
		this.addRow(1, vueTitre);
		GridPane.setColumnSpan(vueTitre, this.getColumnCount());

		this.ajouterObservateur(vuePriorite);
		this.ajouterObservateur(vueDeplacement);
		this.ajouterObservateur(vueDependance);
		this.ajouterObservateur(vueCalendrier);
		this.ajouterObservateur(vueTitre);

		this.setGridLinesVisible(true);
		this.setHgap(10);
		this.setVgap(10);
		this.setStyle("-fx-background-color: yellow; -fx-border-color: black; -fx-border-width: 2px;");
		this.setHeight(500);

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
