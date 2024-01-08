package mvc.vue.liste;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import mvc.controleur.liste.ControlleurAjouterTache;
import mvc.controleur.liste.ControlleurSupprimerTache;
import mvc.modele.ModeleOllert;
import mvc.modele.Sujet;
import ollert.Page;
import ollert.tache.ListeTaches;
import ollert.tache.Tache;
import ollert.tache.donneesTache.Etiquette;
import ollert.tache.donneesTache.Priorite;
import ollert.tache.donneesTache.Utilisateur;

import java.util.List;

/**
 * Classe de la vue représentant une liste de tâches sous forme de tableau
 */
public class VueListeTableur extends GridPane implements VueListe {

	/**
	 * Constructeur de la classe VueListeTableau
	 */
	public VueListeTableur(ModeleOllert modeleControle) {
		this.setHgap(10);
		this.setVgap(10);
	}

	/**
	 * Actualise la vue courante
	 * @param sujet le modèle à partir duquel la vue est actualisée
	 */
	@Override
	public void actualiser(Sujet sujet) {
		this.getChildren().clear();
		ModeleOllert modele = (ModeleOllert) sujet;
		Page page = (Page)modele.getDonnee();
		VBox parent = (VBox) this.getParent();
		int indice = parent.getChildren().indexOf(this);
		ListeTaches lt = modele.getDonnee().getListeTaches(indice);

		HBox nomListe = new HBox();
			Label label = new Label(lt.getTitre());
			Button modif = new Button("Modif");
			Button ajouter = new Button("Ajouter");
			ajouter.setOnAction(new ControlleurAjouterTache(modele));
			Button supp = new Button("Supp");
			supp.setOnAction(new ControlleurSupprimerTache(modele, this));
			Button archiver = new Button("Archiver");
		nomListe.getChildren().addAll(label, modif, ajouter, supp, archiver);

		this.add(nomListe, 0, 0);
		this.add(new Label("Début"), 1, 0);
		this.add(new Label("Echéance"), 2, 0);
		this.add(new Label("Membres"), 3, 0);
		this.add(new Label("Type de travail"), 4, 0);
		this.add(new Label("Priorite"), 5, 0);

		int i=1;
		for (Tache t : lt.getTaches()){
			HBox titre = new HBox();
			titre.getChildren().add(new Label(t.getTitre()));
			this.add(titre, 0, i);

			if (t.getDateDebut() != null){
				this.add(new Label(t.getDateDebut().toString()), 1, i);
			}else{
				this.add(new Label("XX-XX-XXXX"), 1, i);
			}

			if (t.getDateFin() != null){
				this.add(new Label(t.getDateFin().toString()), 2, i);
			}else{
				this.add(new Label("XX-XX-XXXX"), 2, i);
			}

			String chaine = "";
			for (int j=0; j<t.getMembres().size(); j++){
				Utilisateur u = (Utilisateur) t.getMembres().get(j);
				chaine += "\""+u.getPseudo()+"\"";
			}
			this.add(new Label(chaine), 3, i);

			chaine = "";
			for (int j=0; j<t.getTags().size(); j++){
				Etiquette u = (Etiquette) t.getTags().get(j);
				chaine += "\""+u.getValeur()+"\"";
			}
			this.add(new Label(chaine), 4, i);

			if (t.getPriorite() != Priorite.INDEFINI){
				this.add(new Label(t.getPriorite().toString()), 5, i);
			}else{
				this.add(new Label(""), 5, i);
			}
			i++;
		}

	}

	/**
	 * @return La liste de tâches réelle que représente la vue
	 */
	public ListeTaches getListe() {
		return null;
	}

	@Override
	public List<Integer> getLocalisation() {
		return null;
	}

	public Node getParentPrincipale() {
		return null;
	}

	public Node getChildrenPrincipale() {
		return null;
	}
}
