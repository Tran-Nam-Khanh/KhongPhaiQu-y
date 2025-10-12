package Arkanoid;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public void start(Stage stage) {
        Game game = new Game();
        Scene scene = new Scene(game, 800, 600);
        stage.setTitle("Arkanoid - Team Project");
        stage.setScene(scene);
        stage.show();

        game.start();
    }

    public static void main(String[] args) {
        launch();
    }
}
