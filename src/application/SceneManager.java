package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class SceneManager {

    private static SceneManager instance;
    private Stage primaryStage;

    // Private constructor để ngăn việc tạo instance từ bên ngoài
    private SceneManager() {}

    // Phương thức để lấy instance duy nhất của lớp (Singleton Pattern)
    public static synchronized SceneManager getInstance() {
        if (instance == null) {
            instance = new SceneManager();
        }
        return instance;
    }

    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;
    }

    // Tải một file FXML và trả về root node của nó
    private Parent loadFXML(String fxmlFile) throws IOException {
        // Đường dẫn tới file FXML, giả sử chúng nằm trong /resources/ui/
        String fxmlPath = "/ui/" + fxmlFile;
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        return loader.load();
    }

    // Hiển thị một Scene mới
    private void showScene(Parent root) {
        Scene scene = new Scene(root, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        primaryStage.setScene(scene);
    }

    public void showMainMenu() {
        try {
            Parent root = loadFXML("main-menu.fxml"); // Tên file FXML của bạn
            showScene(root);
        } catch (IOException e) {
            System.err.println("Không thể tải màn hình Main Menu!");
            e.printStackTrace();
        }
    }

    public void showGameScreen() {
        try {
            Parent root = loadFXML("game-ui.fxml"); // Tên file FXML của bạn
            showScene(root);
            // Yêu cầu focus để nhận sự kiện bàn phím
            root.requestFocus();
        } catch (IOException e) {
            System.err.println("Không thể tải màn hình Game!");
            e.printStackTrace();
        }
    }

    public void showGameOverScreen() {
        try {
            Parent root = loadFXML("game-over.fxml"); // Tên file FXML của bạn
            showScene(root);
        } catch (IOException e) {
            System.err.println("Không thể tải màn hình Game Over!");
            e.printStackTrace();
        }
    }
}
