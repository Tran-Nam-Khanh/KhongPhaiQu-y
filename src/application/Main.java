package application;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Khởi tạo SceneManager với cửa sổ chính (Stage)
            SceneManager sceneManager = SceneManager.getInstance();
            sceneManager.setPrimaryStage(primaryStage);

            // Đặt tiêu đề và cấu hình cửa sổ
            primaryStage.setTitle("Arkanoid");
            primaryStage.setResizable(false);

            // Hiển thị màn hình menu chính đầu tiên
            sceneManager.showMainMenu();
            primarystage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
