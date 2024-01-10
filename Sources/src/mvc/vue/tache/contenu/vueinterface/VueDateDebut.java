package mvc.vue.tache.contenu.vueinterface;

import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.util.Callback;
import mvc.controleur.tache.interfac.ControleurDateDebut;
import mvc.modele.ModeleOllert;
import mvc.modele.Sujet;
import mvc.vue.Observateur;
import ollert.tache.Tache;
import ollert.tache.TachePrincipale;

import java.time.LocalDate;

public class VueDateDebut extends DatePicker implements Observateur {

    public VueDateDebut(){

    }

    @Override
    public void actualiser(Sujet sujet) {
        ModeleOllert modele = (ModeleOllert) sujet;
        TachePrincipale tache = (TachePrincipale) modele.getTacheEnGrand();
        Callback<DatePicker, DateCell> dayCellFactoryDebut= this.getDayCellFactory(tache);
        this.setDayCellFactory(dayCellFactoryDebut);
        this.setValue(tache.getDateDebut());
        this.valueProperty().addListener(new ControleurDateDebut(modele));
    }

    private Callback<DatePicker, DateCell> getDayCellFactory(Tache tache) {

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
                        if (item.isEqual(tache.getDateFin()) || (item.isAfter(tache.getDateFin())) || item.isAfter(dateMin)){
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
                        if (item.isBefore(dateMax)){
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }

                        // SOUSTACHES

                    }
                };
            }
        };
        return dayCellFactory;
    }
}
