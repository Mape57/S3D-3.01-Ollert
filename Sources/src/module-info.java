module javafx {
    requires javafx.controls;
    requires java.desktop;

    exports mvc;
    exports mvc.fabrique;
    exports mvc.vue;
    exports mvc.vue.liste;
    exports mvc.vue.page;
    exports mvc.vue.tache;
    exports mvc.vue.sousTache;
    exports ollert;
    exports mvc.modele;
	exports ollert.tache;
    exports ollert.tache.donneesTache;
    exports ollert.tache.Comparateur;
}