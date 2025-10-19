package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import ui.GameUIController; // Import controller

import java.io.IOException;

public class SceneManager {

    private static final String UI_PATH = "/ui/";
    /**
     * Chuyển về màn hình Menu chính
     */
    public static void goToMainMenu(Stage stage) {
        try {
            Parent root = FXMLLoader.load(SceneManager.class.getResource(UI_PATH + "MainMenu.fxml"));
            stage.setScene(new Scene(root, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Chuyển về màn hình GameOver
     */
    public static void goToGameOver(Stage stage) {
        try {
            Parent root = FXMLLoader.load(SceneManager.class.getResource(UI_PATH + "GameOver.fxml"));
            stage.setScene(new Scene(root, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Chuyển về màn hình Chiến thắng
     */
    public static void goToWinScreen(Stage stage) {
        try {
            Parent root = FXMLLoader.load(SceneManager.class.getResource(UI_PATH + "WinScreen.fxml"));
            stage.setScene(new Scene(root, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Bắt đầu màn chơi
     */
    public static void startGame(Stage stage) {
        try {
            Canvas canvas = new Canvas(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
            GraphicsContext gc = canvas.getGraphicsContext2D();

            FXMLLoader uiLoader = new FXMLLoader(SceneManager.class.getResource(UI_PATH + "GameUI.fxml"));
            AnchorPane gameUIPane = uiLoader.load();
            GameUIController gameUIController = uiLoader.getController();

            StackPane root = new StackPane();
            root.getChildren().addAll(canvas, gameUIPane);

            Scene scene = new Scene(root);
            stage.setScene(scene);

            GameManager gameManager = new GameManager(stage, gc, gameUIController);
            gameManager.startGame(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
