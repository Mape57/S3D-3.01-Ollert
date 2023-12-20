package ollert.tache.donneesTache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// TODO réfléchir à la conception d'étiquette et d'utilisateur (même méthodes/attributs)

/**
 * Classe représentant un utilisateur présent dans une ou plusieurs tâches.
 */
public class Utilisateur {

	/**
	 * Map contenant la liste des utilisateurs présentent dans une page
	 *
	 * @key : String correspond au titre de la page qui est unique
	 * @value : ArrayList<Utilisateur>> correspond à la liste des utilisateurs présents dans une page
	 */
	private static Map<String, ArrayList<Utilisateur>> utilisateurs = new HashMap<String, ArrayList<Utilisateur>>();
	/**
	 * Représente le nombre d'utilisations de cet utilisateur
	 */
	private int nbUse;
	/**
	 * Représente le nom de cet utilisateur
	 */
	private String pseudo;

	/**
	 * Création d'un Utilisateur
	 *
	 * @param nom pseudo de ce nouvel utilisateur
	 * @throws NullPointerException si le nom est nul
	 */
	private Utilisateur(String nom) {
		if (nom == null) throw new NullPointerException("Le pseudo de l'utilisateur ne peut pas être null");
		this.pseudo = nom;
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
		Utilisateur that = (Utilisateur) o;
		return Objects.equals(pseudo, that.pseudo);
	}

	/**
	 * Obtenir Utilisateur renvoie l'utilisateur correspondant à la page passée en paramètre et au nom de l'étiquette
	 * Une page est créée si la page passée en paramètre n'existe pas
	 * Un utilisateur est créé si l'utilisateur n'existe pas dans la page recherchée
	 *
	 * @param nomPage        correspond au nom de la page sur laquelle on cherche l'utilisateur
	 * @param nomUtilisateur valeur de l'utilisateur recherché
	 * @return l'utilisateur recherché
	 */
	public static Utilisateur obtenirUtilisateur(String nomPage, String nomUtilisateur) {
		ArrayList<Utilisateur> list = utilisateurs.get(nomPage);
		if (list == null) {
			list = new ArrayList<>();
			utilisateurs.put(nomPage, list);
		}
		if (nomUtilisateur == null) throw new NullPointerException("Le nom d'utilisateur ne peut pas être vide.");
		Utilisateur e = new Utilisateur(nomUtilisateur);
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
	 * Supprimer Utilisateur supprime l'utilisateur correspondant à la page passée en paramètre et au nom de l'utilisateur
	 * Le nombre d'utilisations de l'utilisateur est décrémenté de 1
	 * L'utilisateur est supprimé de la liste si ce nombre atteint 0
	 * L'association clé-valeur associée à la page est supprimée si la liste d'utilisateurs de cette page est vide
	 *
	 * @param nomPage        correspond au nom de la page sur laquelle on cherche l'utilisateur
	 * @param nomUtilisateur valeur de l'utilisateur recherché
	 * @return l'utilisateur supprimé
	 */
	public static Utilisateur supprimerUtilisateur(String nomPage, String nomUtilisateur) {
		ArrayList<Utilisateur> list = utilisateurs.get(nomPage);
		if (list == null) throw new NullPointerException("Le nom de la page doit correspondre à une page existante.");
		if (nomUtilisateur == null) throw new NullPointerException("Le nom de l'utilisateur ne peut pas être vide.");
		Utilisateur u = new Utilisateur(nomUtilisateur);
		if (!list.contains(u)) throw new NullPointerException("L'utilisateur n'est pas présent dans la page.");
		u = list.get(list.indexOf(u));
		list.get(list.indexOf(u)).nbUse--;
		if (list.get(list.indexOf(u)).nbUse == 0) {
			list.remove(u);
			if (list.isEmpty()) {
				utilisateurs.remove(nomPage);
			}
		}
		return u;
	}

	public String getPseudo() {
		return pseudo;
	}

	public static Map<String, ArrayList<Utilisateur>> getUtilisateurs() {
		return utilisateurs;
	}
}
