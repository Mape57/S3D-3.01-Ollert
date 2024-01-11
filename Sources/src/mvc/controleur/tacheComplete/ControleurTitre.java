package mvc.controleur.tacheComplete;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import mvc.modele.ModeleOllert;

/**
 *
 */
public class ControleurTitre implements ChangeListener<String> {

	/**
	 * Modele de l'application
	 */
	private final ModeleOllert modele;

	/**
	 * Constructeur de la classe ControleurDescription
	 *
	 * @param modeleOllert Modele de l'application
	 */
	public ControleurTitre(ModeleOllert modeleOllert) {
		this.modele = modeleOllert;
	}

	/**
	 * Gère la modification de la description d'une tâche
	 *
	 * @param observableValue observable
	 * @param s               ancienne description
	 * @param t1              nouvelle description
	 */
	@Override
	public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
		modele.getTacheComplete().setTitre(t1);
	}
}
