package ollert.tache.comparateur;

import ollert.tache.TachePrincipale;

import java.util.Comparator;

public class ComparateurDateDebut implements Comparator<TachePrincipale> {
    @Override
    public int compare(TachePrincipale o1, TachePrincipale o2) {
        if(o1.getDateDebut().compareTo(o2.getDateDebut())<0){
            return -1;
        }else if(o1.getDateDebut().compareTo(o2.getDateDebut())>0){
            return 1;
        }else{
            return -o1.getDateFin().compareTo(o2.getDateFin());
        }
    }
}
