package mvc.controleur.tache;


import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import mvc.fabrique.FabriqueVueTableau;
import mvc.modele.ModeleOllert;
import mvc.vue.liste.VueListe;
import mvc.vue.tache.VueTache;
import mvc.vue.tache.VueTacheInterface;
import ollert.tache.ListeTaches;
import ollert.tache.Tache;
import ollert.tache.TachePrincipale;
import ollert.tache.donneesTache.Etiquette;
import ollert.tache.donneesTache.Utilisateur;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ollert.tache.donneesTache.Priorite.*;

public class ControlleurModification implements EventHandler<MouseEvent> {

    private ModeleOllert modele;

    private boolean modeSuppression = false;
    private List<Label> selected = new ArrayList<>();


    /**
     * Constructeur de la classe ControleurModification
     */
    public ControlleurModification(ModeleOllert modele) {
        this.modele = modele;
    }

    @Override
    public void handle(MouseEvent event) {
        VueTache vueTache = (VueTache) event.getSource();
        VueListe vueListe;
        if (modele.getFabrique() instanceof FabriqueVueTableau){
            System.out.println(vueTache.getParent());
            vueListe = (VueListe) ((ScrollPane) vueTache.getParent().getProperties().get("scrollPane")).getParent();
        }else{
            vueListe = (VueListe)vueTache.getParent().getParent();
        }

        int indiceVL;
        int indiceVT;
        if (vueListe.getParent() instanceof HBox){
            HBox parent = (HBox)vueListe.getParent();
            indiceVL = parent.getChildren().indexOf(vueListe);
            VBox listeTaches = (VBox)((ScrollPane) vueListe.getChildren().get(1)).getContent();
            indiceVT = listeTaches.getChildren().indexOf(vueTache);
        }else{
            VBox parent = (VBox)vueListe.getParent();
            indiceVL = parent.getChildren().indexOf(vueListe);

            VueListe vl = (VueListe)parent.getChildren().get(indiceVL);
            VBox vb = (VBox)vl.getChildren().get(1);
            indiceVT = vb.getChildren().indexOf(vueTache);

            System.out.println(indiceVL);
            System.out.println("==>"+indiceVT);
        }

        Tache<ListeTaches> t = this.modele.getDonnee().getListes().get(indiceVL).getTache(indiceVT);

        if (modele.getTacheEnGrand() == null){

            modele.setTacheEnGrand(t);
            VueTacheInterface vueTacheInterface = new VueTacheInterface(modele);
            modele.ajouterObservateur(vueTacheInterface);
            Stage stage = new Stage();
            stage.setOnHiding(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent windowEvent) {
                    modele.setTacheEnGrand(null);
                    modele.supprimerObservateur(vueTacheInterface);
                }
            });
            stage.setScene(new Scene(vueTacheInterface, 1200, 700));  // Ajustez la taille au besoin

            // Afficher la Stage
            stage.show();
        }
        /**TextField titre = new TextField(t.getTitre());
        titre.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                t.setTitre(newValue);
                modele.notifierObservateurs();
            }
        });
        int x = 0;
        int y = 0;
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
        Callback<DatePicker, DateCell> dayCellFactoryDebut= this.getDayCellFactory(t);
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
        DatePicker dpFin = new DatePicker();
        dpFin.setValue(t.getDateFin());
        Callback<DatePicker, DateCell> dayCellFactoryFin= this.getDayCellFactory(t);
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
        Button faible = new Button("Faible");
        Button moyenne = new Button("Moyenne");
        Button elevee = new Button("Elevée");
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
        gp.add(supprimer, x, y);**/







    }

    /**private Callback<DatePicker, DateCell> getDayCellFactory(Tache tache) {

        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {

            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);

                        // DEPENDANCES //

                        // Récupération de la date minimum
                        LocalDate dateMin = LocalDate.MAX;
                        for (TachePrincipale tp1 : ((TachePrincipale)tache).getDependances()){
                            if ((tp1.getDateDebut() != null) && (tp1.getDateDebut().isBefore(dateMin))){
                                dateMin = tp1.getDateDebut();
                            }
                        }
                        if (item.equals(dateMin) || item.isAfter(dateMin)){
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }

                        // ANTECEDENTS
                        LocalDate dateMax = LocalDate.MIN;
                        for (TachePrincipale tp2 : ((TachePrincipale)tache).getAntecedents()){
                            if ((tp2.getDateFin() != null) && (tp2.getDateFin().isAfter(dateMax))){
                                dateMax = tp2.getDateFin();
                            }
                        }
                        if (item.equals(dateMax) || item.isBefore(dateMax)){
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }

                        // SOUSTACHES

                    }
                };
            }
        };
        return dayCellFactory;
    }**/
}
