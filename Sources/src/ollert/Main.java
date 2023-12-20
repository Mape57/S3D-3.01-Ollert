package ollert;

import ollert.tache.donneesTache.Priorite;

public class Main {

    public static void main(String[] args) {
                
        Page page = new Page("Ollert1");
        System.out.println(page);

        System.out.println("--------------------");

        page.creerListeTaches("Liste 1");
        page.creerListeTaches("Liste 2");
        page.creerListeTaches("Liste 3");
        System.out.println(page);

        System.out.println("--------------------");
        page.getListes().get(0).creerTache("Tache 1");
        page.getListes().get(0).creerTache("Tache 2");
        System.out.println(page);

        System.out.println("--------------------");
        page.getListes().get(1).creerTache("Tache 3");
        System.out.println(page);

        System.out.println("Modification priorité");
        System.out.println("--------------------");
        page.getListes().get(0).getTaches().get(0).setPriorite(Priorite.MOYENNE);
        System.out.println(page);

        System.out.println("Modification priorité");
        System.out.println("--------------------");
        page.getListes().get(0).getTaches().get(0).setPriorite(Priorite.FAIBLE);
        System.out.println(page);

        System.out.println("Ajout utilisateur");
        System.out.println("--------------------");
        page.getListes().get(0).getTaches().get(0).ajouterUtilisateur("Enzo");
        page.getListes().get(0).getTaches().get(0).ajouterUtilisateur("Mathéo");
        System.out.println(page);

        System.out.println("Ajout utilisateur");
        System.out.println("--------------------");
        page.getListes().get(0).getTaches().get(1).ajouterUtilisateur("Mathéo");
        System.out.println(page);

        System.out.println("Suppression utilisateur");
        System.out.println("--------------------");
        page.getListes().get(0).getTaches().get(0).supprimerUtilisateur("Mathéo");
        System.out.println(page);

        System.out.println("Ajout etiquette");
        System.out.println("--------------------");
        page.getListes().get(0).getTaches().get(0).ajouterEtiquette("Tag 1");
        page.getListes().get(0).getTaches().get(0).ajouterEtiquette("Tag 2");
        System.out.println(page);

        System.out.println("Ajout utilisateur");
        System.out.println("--------------------");
        page.getListes().get(0).getTaches().get(1).ajouterEtiquette("Tag 2");
        System.out.println(page);

        System.out.println("Suppression utilisateur");
        System.out.println("--------------------");
        page.getListes().get(0).getTaches().get(0).supprimerEtiquette("Tag 2");
        System.out.println(page);


        System.out.println("--------------------");
        page.archiverTache(0, 0);
        System.out.println(page);

        System.out.println("--------------------");
        page.supprimerListeTaches(0);
        System.out.println(page);

        System.out.println("--------------------");
        page.archiverListeTaches(0);
        System.out.println(page);





        System.out.println("\n\n--------------------");
        System.out.println("Demonstration sauvegarde et chargement de page");
        System.out.println("--------------------");

        page.creerListeTaches("Liste 1");
        page.obtenirListe(0).creerTache("Tache 11");
        System.out.println(page);

        Sauvegarde.sauvegarderPage(page);

        page.obtenirListe(0).creerTache("Tache 12");
        System.out.println(page);

        System.out.println(Sauvegarde.chargerPage("Ollert1.ol"));
        Sauvegarde.sauvegarderPage(page);
        System.out.println(Sauvegarde.chargerPage("Ollert1.ol"));

    }
}
