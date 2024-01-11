package mvc.vue.secondaire.tacheComplete.contenu;

import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.util.Callback;
import mvc.controleur.tacheComplete.ControleurDateDebut;
import mvc.modele.ModeleOllert;
import mvc.modele.Sujet;
import mvc.vue.structure.Observateur;
import ollert.donnee.tache.SousTache;
import ollert.donnee.tache.TachePrincipale;
import ollert.donnee.tache.Tache;

import java.time.LocalDate;

public class VueDateDebut extends DatePicker implements Observateur {

	public VueDateDebut() {
		this.setMinWidth(105);
	}

	@Override
	public void actualiser(Sujet sujet) {
		ModeleOllert modele = (ModeleOllert) sujet;
		Tache<?> tache = modele.getTacheComplete();
		Callback<DatePicker, DateCell> dayCellFactoryDebut = this.getDayCellFactory(tache);
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
						LocalDate dateMin = LocalDate.MAX;
						LocalDate dateMax = LocalDate.MIN;
						if (tache instanceof TachePrincipale) {
							// DEPENDANCES //

							// Récupération de la date minimum
							for (TachePrincipale tp1 : ((TachePrincipale) tache).getDependances()) {
								if ((tp1.getDateDebut() != null) && (tp1.getDateDebut().isBefore(dateMin))) {
									dateMin = tp1.getDateDebut();
								}
							}
							if (tache.getDateFin() != null) {
								if (item.isEqual(tache.getDateFin()) || (item.isAfter(tache.getDateFin())) || item.isAfter(dateMin)) {
									setDisable(true);
									setStyle("-fx-background-color: #ffc0cb;");
								}
							}


							// ANTECEDENTS
							for (TachePrincipale tp2 : ((TachePrincipale) tache).getAntecedents()) {
								if ((tp2.getDateFin() != null) && (tp2.getDateFin().isAfter(dateMax))) {
									dateMax = tp2.getDateFin();
								}
							}
							if (item.isBefore(dateMax)) {
								setDisable(true);
								setStyle("-fx-background-color: #ffc0cb;");
							}

							// SOUSTACHES
							dateMax = LocalDate.MIN;
							dateMin = LocalDate.MAX;
							for (SousTache st : ((TachePrincipale) tache).getSousTaches()) {
								if (st.getDateDebut() != null && st.getDateDebut().isBefore(dateMin)) {
									dateMin = st.getDateDebut();
								} else if (st.getDateFin() != null && st.getDateFin().isBefore(dateMax)) {
									dateMax = st.getDateFin();
								}
							}
							if (item.isBefore(dateMax) || item.isAfter(dateMin)) {
								setDisable(true);
								setStyle("-fx-background-color: #ffc0cb;");
							}
						} else if (tache instanceof SousTache) {
							// PAS D'ANTECEDENTS NI DE DEPENDANCES

							// TACHE PARENT - Doit être avant la date de fin d'au moins une journée
							if (((Tache<?>) tache.getParent()).getDateDebut() != null) {
								dateMax = ((Tache<?>) tache.getParent()).getDateDebut();
							}
							if (((Tache<?>) tache.getParent()).getDateFin() != null) {
								dateMin = ((Tache<?>) tache.getParent()).getDateFin();
							}
							// SOUSTACHES
							for (SousTache st : ((SousTache) tache).getSousTaches()) {
								if (st.getDateDebut() != null && st.getDateDebut().isBefore(dateMin)) {
									dateMin = st.getDateDebut();
								} else if (st.getDateFin() != null && st.getDateFin().isBefore(dateMax)) {
									dateMax = st.getDateFin();
								}
							}
							if (tache.getDateFin() != null) {
								if (item.isBefore(dateMax) || item.isAfter(dateMin) || item.equals(tache.getDateFin()) || item.isAfter(tache.getDateFin())) {
									setDisable(true);
									setStyle("-fx-background-color: #ffc0cb;");
								}
							} else {
								if (item.isBefore(dateMax) || item.isAfter(dateMin) || item.equals(tache.getDateFin())) {
									setDisable(true);
									setStyle("-fx-background-color: #ffc0cb;");
								}
							}

						}
					}
				};
			}
		};
		return dayCellFactory;
	}
}
