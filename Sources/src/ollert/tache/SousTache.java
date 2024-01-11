package ollert.tache;

import ollert.tache.donneesTache.Etiquette;
import ollert.tache.donneesTache.Utilisateur;

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

	public SousTache(TachePrincipale tache, Tache<?> parent) {
		super(tache.getTitre());
		this.parent = parent;
		this.getSousTaches().addAll(tache.getSousTaches());
		for (SousTache sousTache : this.getSousTaches())
			sousTache.setParent(this);

		for (int i = tache.getDependances().size() - 1; i >= 0; i--) {
			TachePrincipale dependance = tache.getDependances().get(i);
			tache.supprimerDependance(dependance);
		}

		for (int i = tache.getAntecedents().size() - 1; i >= 0; i--) {
			TachePrincipale antecedant = tache.getAntecedents().get(i);
			antecedant.supprimerDependance(tache);
		}

		this.setDescription(tache.getDescription());
		this.setDateDebut(tache.getDateDebut());
		this.setDateFin(tache.getDateFin());
		this.setPriorite(tache.getPriorite());
		for (Utilisateur utilisateur : tache.getMembres())
			this.ajouterUtilisateur(utilisateur.getPseudo());

		for (Etiquette etiquette : tache.getTags())
			this.ajouterEtiquette(etiquette.getValeur());
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
	@Override
	public void setParent(Tache<?> tacheParente) {
		if (tacheParente == null) throw new NullPointerException("La tâche parente ne doit pas être null");
		this.parent = tacheParente;
	}
}
