package ollert;

import java.io.*;

public class Sauvegarde {

    public static String DIR="ressource/fichier/";

    public static Page chargerPage(String nomFichier) {
        try{
            File fichier =  new File(DIR+nomFichier);
            ObjectInputStream ois =  new ObjectInputStream(new FileInputStream(fichier));
            Page p = (Page)ois.readObject();
            ois.close();
            return p;
        }catch (Exception e){
            return null;
        }
    }

    public static boolean sauvegarderPage(Page page) {
        try{
            File fichier =  new File(DIR+page.getTitre()+".ol");
            //System.out.println(fichier.getAbsolutePath());
            // ouverture d'un flux sur un fichier
            ObjectOutputStream oos =  new ObjectOutputStream(new FileOutputStream(fichier)) ;
            // sérialization de la Page
            oos.writeObject(page);
            // fermeture du flux
            oos.close();
            return true;
        }catch (IOException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
