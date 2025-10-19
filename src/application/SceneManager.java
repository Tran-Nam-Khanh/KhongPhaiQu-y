package application;

import com.KhongPhaiQuy.Arkanoid.ui.GameUIController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManager {

    private static final String UI_RESOURCE_PATH = "/com/KhongPhaiQuy/Arkanoid/ui/";

    public static void goToMainMenu(Stage stage) {
        try {
            Parent root = FXMLLoader.load(SceneManager.class.getResource(UI_RESOURCE_PATH + "MainMenu.fxml"));
            stage.setScene(new Scene(root, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT));
        } catch (IOException e) {
            System.err.println("Fail to load MainMenu.fxml");
            e.printStackTrace();
        }
    }

    public static void goToGameOver(Stage stage) {
        try {
            Parent root = FXMLLoader.load(SceneManager.class.getResource(UI_RESOURCE_PATH + "GameOver.fxml"));
            stage.setScene(new Scene(root, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT));
        } catch (IOException e) {
            System.err.println("Fail to load GameOver.fxml");
            e.printStackTrace();
        }
    }

    public static void goToWinScreen(Stage stage) {
        try {
            Parent root = FXMLLoader.load(SceneManager.class.getResource(UI_RESOURCE_PATH + "WinScreen.fxml"));
            stage.setScene(new Scene(root, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT));
        } catch (IOException e) {
            System.err.println("Fail to load WinScreen.fxml");
            e.printStackTrace();
        }
    }

    public static void startGame(Stage stage) {
        try {
            Canvas canvas = new Canvas(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
            GraphicsContext gc = canvas.getGraphicsContext2D();

            FXMLLoader uiLoader = new FXMLLoader(SceneManager.class.getResource(UI_RESOURCE_PATH + "GameUI.fxml"));
            AnchorPane gameUIPane = uiLoader.load();
            GameUIController gameUIController = uiLoader.getController();

            StackPane root = new StackPane();
            root.getChildren().addAll(canvas, gameUIPane);

            Scene scene = new Scene(root);
            stage.setScene(scene);

            scene.setCursor(javafx.scene.Cursor.NONE);

            GameManager gameManager = new GameManager(stage, gc, gameUIController);
            gameManager.startGame(scene);

        } catch (IOException e) {
            System.err.println("Fail to load game!");
            e.printStackTrace();
        }
    }
}
