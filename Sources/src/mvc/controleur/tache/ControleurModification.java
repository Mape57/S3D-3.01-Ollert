package mvc.controleur.tache;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
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
import mvc.vue.tache.contenu.VueTitre;
import ollert.tache.ListeTaches;
import ollert.tache.Tache;

public class ControleurModification implements EventHandler<MouseEvent> {

    private ModeleOllert modele;
    public Label duree;

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
        VueTitre vueTitre = new VueTitre(); // AFFICHER LE TITRE PAR DEFAUT
        vueTitre.setEditable(true); // LE DESACTIVER UNE FOIS LA PAGE QUITTEE
        gp.add(vueTitre, 0, 0);

        Label nomListe = new Label("Fait partie de la liste " + this.modele.getDonnee().getListes().get(indiceVL).getTitre()); // A MODIFIER POUR AFFICHER LE TITRE DE LA LISTE PARENT
        gp.add(nomListe, 0, 1);

        // VERSION AVEC DATES
        /**
        Label debut = new Label("Début : ");
        gp.add(debut, 1, 1);
        DatePicker dpDebut = new DatePicker(); // INTEGRER INTERACTION AVEC LA DATE DE DEBUT D'UNE TACHE (MODIFICATION, EXCEPTIONS,...)
        gp.add(dpDebut, 2, 1);

        Label fin = new Label("Fin : ");
        gp.add(fin, 1, 2);
        DatePicker dpFin = new DatePicker(); // INTEGRER INTERACTION AVEC LA DATE DE FIN D'UNE TACHE (MODIFICATION, EXCEPTIONS,...)
        gp.add(dpFin, 2, 2);**/

        // VERSION SANS DATES
        Label fin = new Label("Durée : ");
        gp.add(fin, 1, 2);
        duree = new Label();
        duree.setText(String.valueOf(t.getDuree()));

        gp.add(duree, 3, 2);
        Button moins = new Button("-");
        if (t.getDuree()==1){
            moins.setDisable(true);
        }
        moins.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (t.getDuree() > 1){
                    t.setDuree(t.getDuree() - 1);
                    majDureeAffichage(t.getDuree());
                }
                else {
                    moins.setDisable(true);
                }
            }
        });
        gp.add(moins, 4, 2);
        Button plus = new Button("+");
        plus.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                t.setDuree(t.getDuree() + 1);
                majDureeAffichage(t.getDuree());
                moins.setDisable(false);
            }
        });
        gp.add(plus, 2, 2);



        Label d = new Label("Description");
        gp.add(d, 0, 3);
        TextField description = new TextField(t.getDescription()); // A MODIFIER POUR AFFICHER LA DESCRIPTION D'UNE TACHE ET LE RENDRE MODIFIABLE
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
        gp.add(archiver, 1, y);
        gp.add(supprimer, 2, y);






        Stage stage = new Stage();
        stage.setScene(new Scene(gp, 1200, 700));  // Ajustez la taille au besoin

        // Afficher la Stage
        stage.show();
    }
    private void majDureeAffichage(int nvDuree) {
        // Mettre à jour le texte du label avec la nouvelle valeur de la durée
        duree.setText(String.valueOf(nvDuree));
    }

}
