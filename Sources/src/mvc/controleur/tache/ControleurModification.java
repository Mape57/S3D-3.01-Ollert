package mvc.controleur.tache;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import mvc.modele.ModeleOllert;
import mvc.vue.liste.VueListe;
import mvc.vue.tache.VueTache;
import mvc.vue.tache.contenu.VueEtiquettes;
import mvc.vue.tache.contenu.VueMembres;
import ollert.tache.ListeTaches;
import ollert.tache.Tache;
import java.util.Optional;
import static ollert.tache.donneesTache.Priorite.*;

public class ControleurModification implements EventHandler<MouseEvent> {

    private ModeleOllert modele;

    /**
     * Constructeur de la classe ControleurModification
     */
    public ControleurModification(ModeleOllert modele) {
        this.modele = modele;
    }

    @Override
    public void handle(MouseEvent event) {
        int indiceVL;
        VueTache vueTache = (VueTache) event.getSource();
        VueListe vueListe = (VueListe) ((ScrollPane) vueTache.getParent().getProperties().get("scrollPane")).getParent();
        int indiceVT;
        if (vueListe.getParent() instanceof HBox){
            HBox parent = (HBox)vueListe.getParent();
            indiceVL = parent.getChildren().indexOf(vueListe);
            VBox listeTaches = (VBox)((ScrollPane) vueListe.getChildren().get(1)).getContent();
            indiceVT = listeTaches.getChildren().indexOf(vueTache);
        }else{
            VBox parent = (VBox)vueListe.getParent();
            indiceVL = parent.getChildren().indexOf(vueListe);
            indiceVT = parent.getChildren().indexOf(vueTache);
        }
        Tache<ListeTaches> t = this.modele.getDonnee().getListes().get(indiceVL).getTache(indiceVT);
        System.out.println(t.getTitre());

        // Créer une nouvelle alerte
        GridPane gp = new GridPane();
        TextField titre = new TextField(t.getTitre());
        titre.setEditable(true); // LE DESACTIVER UNE FOIS LA PAGE QUITTEE
        titre.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                t.setTitre(newValue);
            }
        });
        gp.add(titre, 0, 0);

        Label nomListe = new Label("Fait partie de la liste " + this.modele.getDonnee().getListes().get(indiceVL).getTitre()); // A MODIFIER POUR AFFICHER LE TITRE DE LA LISTE PARENT
        gp.add(nomListe, 0, 1);


        Label debut = new Label("Début : ");
        gp.add(debut, 1, 1);
        DatePicker dpDebut = new DatePicker(); // INTEGRER INTERACTION AVEC LA DATE DE DEBUT D'UNE TACHE (MODIFICATION, EXCEPTIONS,...)
        gp.add(dpDebut, 2, 1);

        Label fin = new Label("Fin : ");
        gp.add(fin, 1, 2);
        DatePicker dpFin = new DatePicker(); // INTEGRER INTERACTION AVEC LA DATE DE FIN D'UNE TACHE (MODIFICATION, EXCEPTIONS,...)
        gp.add(dpFin, 2, 2);





        Label d = new Label("Description");
        gp.add(d, 0, 3);
        TextField description = new TextField(t.getDescription()); // A MODIFIER POUR AFFICHER LA DESCRIPTION D'UNE TACHE ET LE RENDRE MODIFIABLE
        description.setPromptText("Pas de description.");
        description.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                t.setDescription(newValue);
            }
        });
        gp.add(description, 0, 4);


        Label info = new Label("Informations de la tâche");
        gp.add(info, 0, 5);
        Label membres = new Label("Membres participants");
        Button supprMembre = new Button("Supprimer");
        Button ajoutMembre = new Button("Ajouter");
        gp.add(membres, 0, 6);
        gp.add(supprMembre, 1, 6);
        gp.add(ajoutMembre, 2, 6);
        VueMembres vueMembres = new VueMembres(); // AJOUTER LA FIN DU PRENOM
        gp.add(vueMembres, 0, 7); // A MODIFIER CAR VA TOUT CASSER


        Label etiquettes = new Label("Etiquettes de la tâche");
        Button supprTag = new Button("Supprimer");
        Button ajoutTag = new Button("Ajouter");
        gp.add(etiquettes, 0, 8);
        gp.add(supprTag, 1, 8);
        gp.add(ajoutTag, 2, 8);
        VueEtiquettes vueEtiquettes = new VueEtiquettes();
        gp.add(vueEtiquettes, 0, 9); // A MODIFIER CAR VA TOUT CASSER

        Label prio = new Label("Priorité");
        gp.add(prio, 0, 10);
        Button faible = new Button("Faible"); // IMPLEMENTER MODIFICATION
        Button moyenne = new Button("Moyenne"); // LA PRIORITE SELECTIONNEE DOIT ETRE EN COULEUR ET NON CLIQABLE
        Button elevee = new Button("Elevée"); // LES AUTRES SONT GRISEES MAIS CLIQUABLES
        // switch pour mettre à jour les boutons
        switch (t.getPriorite()){
            case FAIBLE:
                faible.setDisable(true);
                faible.setStyle("-fx-background-color: yellow;");
                moyenne.setStyle("-fx-background-color: lightgray;");
                elevee.setStyle("-fx-background-color: lightgray;");
                //les autres sont actifs
                break;
            case MOYENNE:
                moyenne.setDisable(true);
                moyenne.setStyle("-fx-background-color: orange;");
                faible.setStyle("-fx-background-color: lightgray;");
                elevee.setStyle("-fx-background-color: lightgray;");
                //les autres sont actifs
                break;
            case ELEVEE:
                elevee.setDisable(true);
                elevee.setStyle("-fx-background-color: red;");
                faible.setStyle("-fx-background-color: lightgray;");
                moyenne.setStyle("-fx-background-color: lightgray;");
                //les autres sont actifs
                break;
            case INDEFINI:
                faible.setStyle("-fx-background-color: lightgray;");
                moyenne.setStyle("-fx-background-color: lightgray;");
                elevee.setStyle("-fx-background-color: lightgray;");
                break;
        }
        faible.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                faible.setDisable(true);
                moyenne.setDisable(false);
                elevee.setDisable(false);
                faible.setStyle("-fx-background-color: yellow;");
                moyenne.setStyle("-fx-background-color: lightgray;");
                elevee.setStyle("-fx-background-color: lightgray;");
                t.setPriorite(FAIBLE);
                System.out.println(t.getPriorite());
            }
        });

        moyenne.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle (ActionEvent event){
                faible.setDisable(false);
                moyenne.setDisable(true);
                elevee.setDisable(false);
                moyenne.setStyle("-fx-background-color: orange;");
                faible.setStyle("-fx-background-color: lightgray;");
                elevee.setStyle("-fx-background-color: lightgray;");
                t.setPriorite(MOYENNE);
                System.out.println(t.getPriorite());
            }
        });

        elevee.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle (ActionEvent event){
                faible.setDisable(false);
                moyenne.setDisable(false);
                elevee.setDisable(true);
                elevee.setStyle("-fx-background-color: red;");
                faible.setStyle("-fx-background-color: lightgray;");
                moyenne.setStyle("-fx-background-color: lightgray;");
                t.setPriorite(ELEVEE);
                System.out.println(t.getPriorite());
            }
        });

        gp.add(faible, 0, 11);
        gp.add(moyenne, 1, 11);
        gp.add(elevee, 2, 11);



        Label dep = new Label("Liste des dépendances");
        gp.add(dep, 0, 12);
        int x = 0;
        int y = 13;
        // while (jusqu'a ce que toutes les dépendances aient été traitées)
        //      if (x > xMax)
        //          y++;
        //          x = 0;
        //      gp.add(new Label (nom de la tache), i, y);
        //      i++;



        Label sTache = new Label("Liste des sous-tâches");
        gp.add(sTache, 0, y);
        y++;
        // while (jusqu'a ce que toutes les sous-tâches aient été traitées)
        //      if (x > xMax)
        //          y++;
        //          x = 0;
        //      gp.add(new VueTacheTableau, i, y); FAIRE EN SORTE QUE CE SOIT LA VUE DE LA TACHE VOULUE
        //      i++;

        Button archiver = new Button("Archiver");
        Button supprimer = new Button("Supprimer");
        supprimer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation de Suppression");
                alert.setHeaderText(null);
                alert.setContentText("Voulez-vous vraiment supprimer cette tâche ?");

                ButtonType buttonTypeValider = new ButtonType("Valider");
                ButtonType buttonTypeAnnuler = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
                alert.getButtonTypes().setAll(buttonTypeValider, buttonTypeAnnuler);

                Optional<ButtonType> result = alert.showAndWait();

                if (result.isPresent() && result.get() == buttonTypeValider) {

                }
            }
        });
        gp.add(archiver, 1, y);
        gp.add(supprimer, 2, y);






        Stage stage = new Stage();
        stage.setScene(new Scene(gp, 1200, 700));  // Ajustez la taille au besoin

        // Afficher la Stage
        stage.show();
    }
}
