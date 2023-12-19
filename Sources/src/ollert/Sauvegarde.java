package ollert;

import java.io.*;

public class Sauvegarde {

    public static Page chargerPage(String nomFichier) {
        try{
            File fichier =  new File(nomFichier);
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
            File fichier =  new File("ressource/"+page.getTitre()+".ol");
            // ouverture d'un flux sur un fichier
            ObjectOutputStream oos =  new ObjectOutputStream(new FileOutputStream(fichier)) ;
            // sérialization de la Page
            oos.writeObject(page);
            // fermeture du flux
            oos.close();
            return true;
        }catch (IOException e){
            return false;
        }
    }
}
