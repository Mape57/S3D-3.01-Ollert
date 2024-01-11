module javafx {
    requires javafx.controls;
    requires java.desktop;
	requires javafx.swing;

    exports fabrique;
	exports ollert;
	exports ollert.donnee.tache;
    exports ollert.donnee.tache.attribut;
	exports mvc.vue.principale.tableau.tache;
	exports ollert.donnee;
	exports ollert.tool;
	exports ollert.donnee.structure;
	exports mvc.vue.structure;
	exports mvc.vue.principale.gantt;
	exports mvc.vue.principale.tableau;
	exports mvc.vue.principale.tableur;
	exports mvc.vue.secondaire.tacheComplete;
	exports mvc;
	exports mvc.modele;
}