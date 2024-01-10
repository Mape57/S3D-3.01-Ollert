package ollert.tache.donneesTache;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// TODO réfléchir à la conception d'étiquette et d'utilisateur (même méthodes/attributs)

/**
 * Classe représentant une étiquette présente dans une ou plusieurs tâches.
 */
public class Etiquette implements Serializable {

	/**
	 * Map contenant la liste des étiquettes présentent dans une page
	 *
	 * @key : String correspond au titre de la page qui est unique
	 * @value : ArrayList<Etiquette>> correspond à la liste des étiquettes présentes dans une page
	 */
	private static Map<String, ArrayList<Etiquette>> etiquettes = new HashMap<String, ArrayList<Etiquette>>();
	/**
	 * Représente le nombre d'utilisations de cette étiquette
	 */
	private int nbUse;
	/**
	 * Représente la valeur de cette étiquette
	 */
	private String valeur;

	/**
	 * Création d'un Etiquette
	 *
	 * @param nom valeur de cette nouvelle étiquette
	 * @throws NullPointerException si le nom est nul
	 */
	private Etiquette(String nom) {
		if (nom == null) throw new NullPointerException("La valeur de l'étiquette ne peut pas être null");
		this.valeur = nom;
		this.nbUse = 0;
	}

	/**
	 * @param o Objet de la comparaison
	 * @return True si les deux objets sont les mêmes, False sinon
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Etiquette etiquette = (Etiquette) o;
		return Objects.equals(valeur, etiquette.valeur);
	}

	/**
	 * Obtenir Etiquette renvoie l'étiquette correspondante à la page passée en paramètre et au nom de l'étiquette
	 * Une page est créée si la page passée en paramètre n'existe pas
	 * Une étiquette est créée si l'étiquette n'existe pas dans la page recherchée
	 *
	 * @param nomPage      correspond au nom de la page sur laquelle on cherche l'étiquette
	 * @param nomEtiquette valeur de l'étiquette recherchée
	 * @return l'étiquette recherchée
	 */
	public static Etiquette obtenirEtiquette(String nomPage, String nomEtiquette) {
		ArrayList<Etiquette> list = etiquettes.get(nomPage);
		if (list == null) {
			list = new ArrayList<>();
			etiquettes.put(nomPage, list);
		}
		if (nomEtiquette == null) throw new NullPointerException("Le nom de l'étiquette ne peut pas être vide.");
		Etiquette e = new Etiquette(nomEtiquette);
		if (list.contains(e)) {
			list.get(list.indexOf(e)).nbUse++;
			return list.get(list.indexOf(e));
		} else {
			e.nbUse++;
			list.add(e);
			return e;
		}
	}

	/**
	 * Supprimer Etiquette supprime l'étiquette correspondante à la page passée en paramètre et au nom de l'étiquette
	 * Le nombre d'utilisations de l'étiquette est décrémenté de 1
	 * L'étiquette est supprimée de la liste si ce nombre atteint 0
	 * L'association clé-valeur associée à la page est supprimée si la liste d'étiquettes de cette page est vide
	 *
	 * @param nomPage      correspond au nom de la page sur laquelle on cherche l'étiquette
	 * @param nomEtiquette valeur de l'étiquette recherchée
	 * @return l'étiquette supprimée
	 */
	public static Etiquette supprimerEtiquette(String nomPage, String nomEtiquette) {
		ArrayList<Etiquette> list = etiquettes.get(nomPage);
		if (list == null) throw new NullPointerException("Le nom de la page doit correspondre à une page existante.");
		if (nomEtiquette.isEmpty()) throw new NullPointerException("Le nom de l'étiquette ne peut pas être vide.");
		Etiquette e = new Etiquette(nomEtiquette);
		if (!list.contains(e)) throw new NullPointerException("L'étiquette n'est pas présente dans la page.");
		e = list.get(list.indexOf(e));
		list.get(list.indexOf(e)).nbUse--;
		if (list.get(list.indexOf(e)).nbUse == 0) {
			list.remove(e);
			if (list.isEmpty()) {
				etiquettes.remove(nomPage);
			}
		}
		return e;
	}

	public String getValeur() {
		return valeur;
	}

	@Override
	public String toString() {
		return "Etiquette{" +
				"nbUse=" + nbUse +
				", valeur='" + valeur + '\'' +
				'}';
	}
}
