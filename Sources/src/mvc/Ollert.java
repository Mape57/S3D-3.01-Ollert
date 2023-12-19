package mvc;

import javafx.application.Application;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Ollert extends Application {

    public static void main(String[] args) {launch(args);}

    @Override
    public void start(Stage stage) {
        GridPane racine = new GridPane();

        Scene scene = new Scene(racine,935,670);
        stage.setScene(scene);
        stage.show();
    }
}
