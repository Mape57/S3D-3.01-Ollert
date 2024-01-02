package ollert.tache;

/**
 * Classe representant une sous-tache
 */
public class SousTache extends Tache<Tache<?>> {


	//-------------------------------//
	//         Constructeurs         //
	//-------------------------------//

	/**
	 * Constructeur d'une SousTache
	 *
	 * @param titre        Titre de la sous-tache
	 * @param tacheParente Tache parente
	 */
	public SousTache(String titre, Tache<?> tacheParente) {
		super(titre);
		this.parent = tacheParente;
	}


	//------------------------------------//
	//------------ METHODES --------------//
	//------------------------------------//

	/**
	 * Retourne la tache parente de la sous-tache
	 *
	 * @return tache parente
	 */
	@Override
	public Tache<?> getParent() {
		return (Tache<?>) this.parent;
	}

	/**
	 * Remplace la tache parente par la tache specifiee en parametre
	 *
	 * @param tacheParente nouvelle tache parente
	 * @throws NullPointerException si la tache parente est null
	 */
	public void setParent(Tache<?> tacheParente) {
		if (tacheParente == null) throw new NullPointerException("La tâche parente ne doit pas être null");
		this.parent = tacheParente;
	}
}
