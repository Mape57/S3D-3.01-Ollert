package ollert.donneesTache;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

// TODO réfléchir à la conception d'étiquette et d'utilisateur (même méthodes/attributs)

/**
 * Classe représentant un utilisateur présent dans une ou plusieurs tâches.
 */
public class Utilisateur {

    /**
     * Map contenant la liste des utilisateurs présentent dans une page
     * @key : String correspond au titre de la page qui est unique
     * @value : ArrayList<Utilisateur>> correspond à la liste des utilisateurs présents dans une page
     */
    private static Map<String, ArrayList<Utilisateur>> utilisateurs;
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
    private Utilisateur(String nom){
        if (nom == null) throw new NullPointerException("Le pseudo de l'utilisateur ne peut pas être null");
        this.pseudo = nom;
        this.nbUse = 0;
    }

    /**
     *
     * @param o Objet de la comparaison
     * @return True si les deux objets sont les mêmes, False sinon
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utilisateur that = (Utilisateur) o;
        return nbUse == that.nbUse && Objects.equals(pseudo, that.pseudo);
    }

    public static Utilisateur obtenirUtilisateur(String nomPage, String nomUtilisateur){
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
        }
        else {
            e.nbUse++;
            list.add(e);
            return e;
        }
    }

    public static void supprimerUtilisateur(String nomPage, String nomUtilisateur){
        ArrayList<Utilisateur> list = utilisateurs.get(nomPage);
        if (list == null) throw new NullPointerException("Le nom de la page doit correspondre à une page existante.");
        if (nomUtilisateur == null) throw new NullPointerException("Le nom de l'utilisateur ne peut pas être vide.");
        Utilisateur u = new Utilisateur(nomUtilisateur);
        if (list.contains(u)) throw new NullPointerException("L'utilisateur n'est pas présent dans la page.");
        list.get(list.indexOf(u)).nbUse--;
        if (list.get(list.indexOf(u)).nbUse == 0){
            list.remove(u);
            if (list.isEmpty()){
                utilisateurs.remove(nomPage);
            }
        }
    }
}
