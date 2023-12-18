package ollert;

import java.io.*;

public class Sauvegarde {

    public static Page chargerPage(String nomFichier) {
        Page p = null;
        try{
            File fichier =  new File(nomFichier);
            ObjectInputStream ois =  new ObjectInputStream(new FileInputStream(fichier));
            p = (Page)ois.readObject();
            ois.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return p;
    }

    public static void sauvegarderPage(Page page) {
        try{
            File fichier =  new File(page.getTitre()+".ol");
            // ouverture d'un flux sur un fichier
            ObjectOutputStream oos =  new ObjectOutputStream(new FileOutputStream(fichier)) ;
            // sérialization de la Page
            oos.writeObject(page);
            // fermeture du flux
            oos.close();
        }catch (IOException e){
            System.out.println(e.toString());
        }
    }
}
