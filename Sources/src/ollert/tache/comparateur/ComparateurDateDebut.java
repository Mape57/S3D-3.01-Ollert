package ollert.tache.comparateur;

import ollert.tache.TachePrincipale;

import java.util.Comparator;

public class ComparateurDateDebut implements Comparator<TachePrincipale> {
    @Override
    public int compare(TachePrincipale o1, TachePrincipale o2) {
        return o1.getDateDebut().compareTo(o2.getDateDebut());
    }
}
