package ollert;

import java.time.LocalDate;
import java.util.List;

public class TacheComposite extends Tache{

    private List<Tache> sousTaches;

    private TacheComposite parent;
    private List<TacheComposite> dependances;
    private List<TacheComposite> antecedents;
    private LocalDate[] dates;


    public TacheComposite(String titre) {
        super(titre);
        // TODO : à compléter
    }

    @Override
    public void creerSousTache(String titre) {
        // TODO : à compléter
    }

    @Override
    public Tache supprimerSousTache(int indice) {
        // TODO : à compléter
        return null;
    }

    @Override
    public void ajouterDependance(Tache tache) {
        // TODO : à compléter
    }

    @Override
    public void supprimerDependance(Tache tache) {
        // TODO : à compléter
    }

    public void setDateDebut(LocalDate date){
        // TODO : à compléter
    }

    public void setDateFin(LocalDate date){
        // TODO : à compléter
    }

    public Tache obtenirSousTache(int indice){
        // TODO : à compléter
        return null;
    }


}
