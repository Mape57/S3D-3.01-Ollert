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
import javafx.util.Callback;
import mvc.modele.ModeleOllert;
import mvc.vue.liste.VueListe;
import mvc.vue.tache.VueTache;
import ollert.tache.ListeTaches;
import ollert.tache.SousTache;
import ollert.tache.Tache;
import ollert.tache.TachePrincipale;
import ollert.tache.donneesTache.Etiquette;
import ollert.tache.donneesTache.Utilisateur;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static ollert.tache.donneesTache.Priorite.*;

public class ControleurModification implements EventHandler<MouseEvent> {

    private ModeleOllert modele;

    private boolean modeSuppression = false;
    private List<Label> selected = new ArrayList<>();


    /**
     * Constructeur de la classe ControleurModification
     */
    public ControleurModification(ModeleOllert modele) {
        this.modele = modele;
    }

    @Override
    public void handle(MouseEvent event) {
        int x = 0;
        int y = 0;
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
                modele.notifierObservateurs();
            }
        });
        gp.add(titre, x, y);
        y++;


        Label nomListe = new Label("Fait partie de la liste " + this.modele.getDonnee().getListes().get(indiceVL).getTitre()); // A MODIFIER POUR AFFICHER LE TITRE DE LA LISTE PARENT
        gp.add(nomListe, x, y);
        x++;

        Label debut = new Label("Début : ");
        gp.add(debut, x, y);
        x++;
        DatePicker dpDebut = new DatePicker(); // INTEGRER INTERACTION AVEC LA DATE DE DEBUT D'UNE TACHE (MODIFICATION, EXCEPTIONS,...)
        dpDebut.setValue(t.getDateDebut());
        Callback<DatePicker, DateCell> dayCellFactoryDebut= this.getDayCellFactory();
        dpDebut.setDayCellFactory(dayCellFactoryDebut);
        dpDebut.valueProperty().addListener((observable, oldValue, newValue) -> {
            t.setDateDebut(newValue);
            modele.notifierObservateurs();
        });
        gp.add(dpDebut, x, y);
        y++;
        x--;

        Label fin = new Label("Fin : ");
        gp.add(fin, x, y);
        x++;
        DatePicker dpFin = new DatePicker(); // INTEGRER INTERACTION AVEC LA DATE DE FIN D'UNE TACHE (MODIFICATION, EXCEPTIONS,...)
        dpFin.setValue(t.getDateFin());
        Callback<DatePicker, DateCell> dayCellFactoryFin= this.getDayCellFactory();
        dpFin.setDayCellFactory(dayCellFactoryFin);
        dpFin.valueProperty().addListener((observable, oldValue, newValue) -> {
            t.setDateFin(newValue);
            modele.notifierObservateurs();
        });
        gp.add(dpFin, x, y);
        x = 0;
        y++;





        Label d = new Label("Description");
        gp.add(d, x, y);
        y++;
        TextField description = new TextField(t.getDescription()); // A MODIFIER POUR AFFICHER LA DESCRIPTION D'UNE TACHE ET LE RENDRE MODIFIABLE
        description.setPromptText("Pas de description.");
        description.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                t.setDescription(newValue);
            }
        });
        gp.add(description, x, y);
        y++;


        Label info = new Label("Informations de la tâche");
        gp.add(info, x, y);
        y++;
        Label membres = new Label("Membres participants");
        Button supprMembre = new Button("Supprimer");
        Button ajoutMembre = new Button("Ajouter");
        gp.add(membres, x, y);
        x++;
        gp.add(supprMembre, x, y);
        x++;
        gp.add(ajoutMembre, x, y);
        x = 0;
        y++;
        for (Utilisateur u : t.getMembres()){
            Label label = new Label(u.getPseudo());
            label.setOnMouseClicked(e -> {
                if (modeSuppression) {
                    if (!selected.contains(label)){
                        selected.add(label);
                    }
                    else {
                        selected.remove(label);
                    }
                }
            });
            gp.add(label, x, y);
            x++;
        }
        y++;
        x=0;
        supprMembre.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                modeSuppression = !modeSuppression;
                if (!modeSuppression){
                    for (Label u : selected){
                        t.supprimerUtilisateur(u.getText());
                    }
                    selected = new ArrayList<>();
                    modele.notifierObservateurs();
                }
                supprMembre.setText(modeSuppression?"Valider" : "Supprimer");
            }
        });


        ajoutMembre.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Saisie du nom du nouveau membre");
                dialog.setHeaderText(null);
                dialog.setContentText("Nom du nouveau membre :");

                Optional<String> result = dialog.showAndWait();

                result.ifPresent(t::ajouterUtilisateur); // TROUVER UNE SOLUTION POUR AFFICHAGE DYNAMIQUE
                modele.notifierObservateurs();
            }
        });
        //VueMembres vueMembres = new VueMembres(); // AJOUTER LA FIN DU PRENOM
        //gp.add(vueMembres, 0, 7); // A MODIFIER CAR VA TOUT CASSER


        Label etiquettes = new Label("Etiquettes de la tâche");
        Button supprTag = new Button("Supprimer");
        Button ajoutTag = new Button("Ajouter");
        gp.add(etiquettes, x, y);
        x++;
        gp.add(supprTag, x, y);
        x++;
        gp.add(ajoutTag, x, y);
        x = 0;
        y++;

        for (Etiquette etiquette : t.getTags()){
            Label label = new Label(etiquette.getValeur());
            label.setOnMouseClicked(e -> {
                if (modeSuppression) {
                    if (!selected.contains(label)){
                        selected.add(label);
                    }
                    else {
                        selected.remove(label);
                    }
                }
            });
            gp.add(label, x, y);
            x++;
        }
        y++;
        x=0;
        supprTag.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                modeSuppression = !modeSuppression;
                if (!modeSuppression){
                    for (Label u : selected){
                        t.supprimerEtiquette(u.getText());
                    }
                    selected = new ArrayList<>();
                    modele.notifierObservateurs();
                }
                supprTag.setText(modeSuppression?"Valider" : "Supprimer");
            }
        });
        ajoutTag.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Saisie du nom de la nouvelle étiquette");
                dialog.setHeaderText(null);
                dialog.setContentText("Nom dé la nouvelle étiquette :");

                Optional<String> result = dialog.showAndWait();

                result.ifPresent(t::ajouterEtiquette); // TROUVER UNE SOLUTION POUR AFFICHAGE DYNAMIQUE
                modele.notifierObservateurs();
            }
        });


        Label prio = new Label("Priorité");
        gp.add(prio, x, y);
        y++;
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

        gp.add(faible, x, y);
        x++;
        gp.add(moyenne, x, y);
        x++;
        gp.add(elevee, x, y);
        x = 0;
        y++;

        if (t instanceof TachePrincipale){
            Label dep = new Label("Liste des dépendances");
            gp.add(dep, x, y);
            y++;
            for (TachePrincipale tp : ((TachePrincipale) t).getAntecedents()){
                Label label = new Label(tp.getTitre());
                gp.add(label, x, y);
                x++;
            }
            y++;
            x=0;
        }




        Label sTache = new Label("Liste des sous-tâches");
        gp.add(sTache, x, y);
        y++;
        for (SousTache st : t.getSousTaches()){

        }
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
                    modele.getDonnee().getListes().get(indiceVL).removeTache(t);
                    modele.notifierObservateurs();

                    Stage stage = (Stage) supprimer.getScene().getWindow();
                    stage.close();
                }
            }
        });
        x=10;
        gp.add(archiver, x, y);
        x++;
        gp.add(supprimer, x, y);






        Stage stage = new Stage();
        stage.setScene(new Scene(gp, 1200, 700));  // Ajustez la taille au besoin

        // Afficher la Stage
        stage.show();
    }

    private Callback<DatePicker, DateCell> getDayCellFactory() {

        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {

            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);

                        // Disable LE 13/12
                        if (item.getDayOfMonth() == 13 && item.getMonth() == Month.DECEMBER) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                };
            }
        };
        return dayCellFactory;
    }
}
