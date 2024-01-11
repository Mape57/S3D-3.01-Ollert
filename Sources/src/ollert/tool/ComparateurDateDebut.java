package ollert.tool;

import ollert.donnee.tache.Tache;

import java.util.Comparator;

/**
 * Comparateur de tache par date de debut puis par date de fin pour avoir les tâches commençant le plus tôt et
 * les tâches finissant le plus tard en premier.
 */
public class ComparateurDateDebut implements Comparator<Tache> {
    /**
     * Compare deux taches par date de debut puis par date de fin
     * @param o1 premiere tâche à comparer
     * @param o2 deuxieme tâche à comparer
     * @return -1 si la premiere tache commence avant la deuxième et termine après, 1 si la premiere tache commence apres la deuxième,
     * 0 si les deux taches commencent et terminent en même temps
     */
    @Override
    public int compare(Tache o1, Tache o2) {
        if(o1.getDateDebut().isBefore(o2.getDateDebut())){
            return -1;
        }else if(o1.getDateDebut().isAfter(o2.getDateDebut())){
            return 1;
        }else{
            return -o1.getDateFin().compareTo(o2.getDateFin());
        }
    }
}
