package mvc.vue.liste;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import mvc.controleur.liste.ControlleurAjouterTache;
import mvc.controleur.liste.ControlleurDragTache;
import mvc.controleur.liste.ControlleurModifierTitre;
import mvc.controleur.liste.ControlleurSupprimerTache;
import mvc.fabrique.FabriqueVueTableau;
import mvc.modele.ModeleOllert;
import mvc.modele.Sujet;
import mvc.vue.page.ParentScrollPane;
import mvc.vue.tache.VueTacheTableau;
import ollert.Page;
import ollert.tache.ListeTaches;
import ollert.tache.Tache;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe de la vue représentant une liste de tâches sous forme de tableau
 */
public class VueListeTableau extends VBox implements VueListe {
	public static final int WIDTH = 300;

	/**
	 * Constructeur de la classe VueListeTableau
	 */
	public VueListeTableau(ModeleOllert modeleControle) {
		this.setStyle(
				"-fx-padding: 10;" +
				"-fx-spacing: 10;" +
				"-fx-border-style: solid; -fx-border-color: black; -fx-border-width: 1 1 1 1;" +
				"-fx-background-color: #baa898"
		);

		this.setPrefWidth(WIDTH);

		// header de la liste
		HBox header = new HBox();
		Label titreListe = new Label();
		Button btn_modif = new Button("Modif");
		btn_modif.setOnAction(new ControlleurModifierTitre(modeleControle));
		Button btn_ajouter = new Button("Ajouter");
		btn_ajouter.setOnAction(new ControlleurAjouterTache(modeleControle));
		header.getChildren().addAll(titreListe, btn_modif, btn_ajouter);
		this.getChildren().add(header);


		ParentScrollPane centre = new ParentScrollPane();
		VBox listeTaches = new VBox();
		listeTaches.setOnDragOver(new ControlleurDragTache(modeleControle));
		centre.setContentAndChildrenProp(listeTaches);
		this.getChildren().add(centre);


		// footer de la liste
		HBox footer = new HBox();
		Button btn_archiver = new Button("Archiver");
		Button btn_supprimer = new Button("Supprimer");
		btn_supprimer.setOnAction(new ControlleurSupprimerTache(modeleControle, this));
		footer.getChildren().addAll(btn_archiver, btn_supprimer);
		this.getChildren().add(footer);
	}

	/**
	 * Actualise la vue courante
	 * @param sujet le modèle à partir duquel la vue est actualisée
	 */
	@Override
	public void actualiser(Sujet sujet) {
		ModeleOllert modele = (ModeleOllert) sujet;
		Page page = modele.getDonnee();
		HBox parent = (HBox) this.getParent();
		int indice = parent.getChildren().indexOf(this);

		// maj top
		HBox top = (HBox) this.getChildren().get(0);
		Label titre = (Label) top.getChildren().get(0);
		titre.setText(page.getListes().get(indice).getTitre());

		// maj centre
		ScrollPane scrollPane = (ScrollPane) this.getChildren().get(1);
		VBox centre = (VBox) scrollPane.getContent();
		ListeTaches lt = page.getListeTaches(indice);
		centre.getChildren().clear();
		for (Tache t : lt.getTaches()) {
			VueTacheTableau vt_tmp = new FabriqueVueTableau().creerVueTache(modele);
			centre.getChildren().add(vt_tmp);
			vt_tmp.actualiser(sujet);
		}
	}

	/**
	 * @return La liste de tâches réelle que représente la vue
	 */
	public ListeTaches getListe() {
		return null;
	}

	public List<Integer> getLocalisation() {
		ArrayList<Integer> loc = new ArrayList<>();
		Parent parent = this.getParent();
		loc.add(parent.getChildrenUnmodifiable().indexOf(this));
		return loc;
	}

	public Node getParentPrincipale() {
		return ((ScrollPane) this.getParent().getProperties().get("scrollPane")).getParent();
	}

	public VBox getChildrenPrincipale() {
		return (VBox) ((ScrollPane) this.getChildren().get(1)).getContent();
	}
}
