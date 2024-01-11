package ollert.tool;

import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import mvc.modele.ModeleOllert;
import mvc.vue.principale.tableau.tache.VueTacheTableauAbstraite;
import mvc.vue.structure.VuePrincipale;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Classe permettant de placer un separateur lors d'un drag and drop
 */
public class PlaceurSeparateur {
	/**
	 * Place le separateur correctement selon les parametres
	 *
	 * @param modele      modele de reference
	 * @param listeTaches liste des taches ou le separateur doit etre place
	 * @param vue         parent de la liste des taches
	 * @return true si le separateur a ete place
	 */
	public static boolean placerSeparateur(ModeleOllert modele, VBox listeTaches, VuePrincipale vue) {
		List<Integer> indicesDragged = null;
		if (modele.getIndicesDragged() != null)
			indicesDragged = new ArrayList<>(modele.getIndicesDragged());

		// on ajoute le separateur si on est en drag
		if (modele.getDraggedTache() != null) {
			if (indicesDragged == null)
				return true;

			// si le deplacement en vers la liste actuelle
			if (Objects.equals(indicesDragged.subList(0, indicesDragged.size() - 1), vue.getLocalisation())) {
				indicesDragged.remove(0);
				Separator separator = new Separator();
				separator.setStyle("-fx-border-style: solid; -fx-border-width: 1px; -fx-background-color: black;");

				// si le deplacement est vers une liste vide
				if (listeTaches.getChildren().isEmpty()) {
					listeTaches.getChildren().add(separator);
					return true;
				}

				// si le deplacement est vers la fin de la liste
				if (indicesDragged.get(0) >= listeTaches.getChildren().size()) {
					listeTaches.getChildren().add(listeTaches.getChildren().size(), separator);
					return true;
				}

				VueTacheTableauAbstraite n = (VueTacheTableauAbstraite) listeTaches.getChildren().get(indicesDragged.get(0));
				n.actualiser(modele);
				if (indicesDragged.size() > 1)
					indicesDragged.remove(0);

				// si on recherche une sous tache on continue
				while (indicesDragged.size() > 1)
					n = (VueTacheTableauAbstraite) n.getChildrenPrincipale().getChildren().get(indicesDragged.remove(0));

				// on ajoute le separateur
				((VBox) n.getParent()).getChildren().add(indicesDragged.get(0), separator);
			}
			return true;
		}
		return false;
	}
}
