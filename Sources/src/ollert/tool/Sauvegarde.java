package ollert.tool;

import ollert.donnee.Page;

import java.io.*;

/**
 * Classe permettant de sauvegarder et charger une page localement
 */
public class Sauvegarde {

	/**
	 * Dossier de sauvegarde
	 */
	public static String DIR = "Sources/ressource/fichier/";

	/**
	 * Charge une page depuis un fichier
	 * @param nomFichier Nom du fichier à charger qui doit être dans le dossier de sauvegarde
	 * @return Page chargée
	 */
	public static Page chargerPage(String nomFichier) {
		try {

			//System.out.println(" \n ");
			File fichier = new File(DIR + nomFichier);
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichier));
			Page p = (Page) ois.readObject();
			ois.close();
			return p;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Sauvegarde une page dans un fichier qui aura son titre comme nom
	 * @param page Page à sauvegarder
	 * @return true si la sauvegarde a réussi, false sinon
	 */
	public static boolean sauvegarderPage(Page page) {
		try {
			File fichier = new File(DIR + page.getTitre() + ".ol");
			//System.out.println(fichier.getAbsolutePath());
			// ouverture d'un flux sur un fichier
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichier));
			// sérialization de la Page
			oos.writeObject(page);
			// fermeture du flux
			oos.close();
			return true;
		} catch (IOException e) {
			//System.out.println(e.getMessage());
			return false;
		}
	}
}
