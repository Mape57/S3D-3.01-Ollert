module javafx {
    requires javafx.controls;
    requires java.desktop;
	requires javafx.swing;

	exports mvc;
    exports mvc.fabrique;
    exports mvc.vue;
    exports mvc.vue.liste;
    exports mvc.vue.page;
    exports mvc.vue.tache;
	exports ollert;
    exports mvc.modele;
	exports ollert.tache;
    exports ollert.tache.donneesTache;
    exports ollert.tache.comparateur;
	exports mvc.vue.tache.tableau;
}