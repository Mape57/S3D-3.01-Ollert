package ollert;

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

        System.out.println("--------------------");

        try {
            page.archiverTache(0, 0);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println(page);
    }
}
