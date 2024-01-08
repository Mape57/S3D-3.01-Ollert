package mvc.vue.liste;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import mvc.controleur.liste.ControlleurAjouterTache;
import mvc.controleur.liste.ControlleurModifierTitre;
import mvc.controleur.liste.ControlleurSupprimerTache;
import mvc.fabrique.FabriqueVueTableau;
import mvc.modele.ModeleOllert;
import mvc.modele.Sujet;
import mvc.vue.tache.VueTacheTableau;
import ollert.Page;
import ollert.tache.ListeTaches;
import ollert.tache.Tache;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe de la vue représentant une liste de tâches sous forme de tableau
 */
public class VueListeTableau extends BorderPane implements VueListe {

	/**
	 * Constructeur de la classe VueListeTableau
	 */
	public VueListeTableau(ModeleOllert modeleControle) {

		// header de la liste
		HBox header = new HBox();
			Label titreListe = new Label();
			Button btn_modif = new Button("Modif");
			btn_modif.setOnAction(new ControlleurModifierTitre(modeleControle));
			Button btn_move = new Button("Move");
			Button btn_ajouter = new Button("Ajouter");
			btn_ajouter.setOnAction(new ControlleurAjouterTache(modeleControle));
			header.getChildren().addAll(titreListe, btn_modif, btn_move, btn_ajouter);
		this.setTop(header);

		// centre de la liste
		VBox centre = new VBox();
		this.setCenter(centre);

		// footer de la liste
		HBox footer = new HBox();
			Button btn_archiver = new Button("Archiver");
			Button btn_supprimer = new Button("Supprimer");
			btn_supprimer.setOnAction(new ControlleurSupprimerTache(modeleControle, this));
		footer.getChildren().addAll(btn_archiver, btn_supprimer);
		this.setBottom(footer);

		// header listeTaches
		/*HBox header = new HBox();
		header.setStyle("-fx-background-color: red; -fx-padding: 10px;");
			VueTitreListe vtl = new VueTitreListe();
			this.observateurs.add(vtl);

			Button btn_modif = new Button("Modif");
			Button btn_deplacer = new Button("Deplacer");
			Button btn_ajouter = new Button("Ajouter");
			btn_ajouter.setOnAction(new ControlleurAjouterTache(modeleControle));

			header.getChildren().addAll(vtl, btn_modif, btn_deplacer, btn_ajouter);
		this.getChildren().add(header);


		//footer listeTaches
		HBox footer = new HBox();
		footer.setStyle("-fx-background-color: green; -fx-padding: 10px;");
			Button btn_archiver = new Button("Archiver");
			btn_archiver.setOnAction(new ControlleurArchiverTache(	modeleControle));
			Button btn_supprimer = new Button("Supprimer");
			btn_supprimer.setOnAction(new ControlleurSupprimerTache(modeleControle));

			footer.getChildren().addAll(btn_archiver, btn_supprimer);
		this.getChildren().add(footer);
		this.notifierObservateurs();*/
	}

	/**
	 * Actualise la vue courante
	 * @param sujet le modèle à partir duquel la vue est actualisée
	 */
	@Override
	public void actualiser(Sujet sujet) {
		ModeleOllert modele = (ModeleOllert) sujet;
		Page page = (Page)modele.getDonnee();
		HBox parent = (HBox) this.getParent();
		int indice = parent.getChildren().indexOf(this);

		// maj top
		HBox top = (HBox) this.getTop();
		Label titre = (Label) top.getChildren().get(0);
		titre.setText(page.getListes().get(indice).getTitre());

		// maj centre
		VBox centre = (VBox) this.getCenter();
		ListeTaches lt = page.getListeTaches(indice);
		centre.getChildren().clear();
		for (Tache t : lt.getTaches()) {
			VueTacheTableau vt_tmp = new FabriqueVueTableau().creerVueTache(modele);
			centre.getChildren().add(vt_tmp);
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
}
