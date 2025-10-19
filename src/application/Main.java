package application;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Arkanoid - KhongPhaiQuy Adventure");
        primaryStage.setResizable(false);
        SceneManager.goToMainMenu(primaryStage);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
