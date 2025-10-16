package Arkanoid;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class HuyTestMain extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            URL fxmlLocation = getClass().getResource("/ui/MainMenu.fxml");
            if (fxmlLocation == null) {
                System.out.println("Không tìm thấy file MainMenu.fxml. Hãy kiểm tra lại đường dẫn.");
                return;
            }

            // 2. Tải file FXML đó lên
            Parent root = FXMLLoader.load(fxmlLocation);

            // 3. Tạo một Scene (khung cảnh) chứa giao diện vừa tải
            Scene scene = new Scene(root);

            // 4. Đặt tiêu đề cho cửa sổ
            primaryStage.setTitle("Arkanoid - Main Menu");

            // 5. Gắn Scene vào Stage (cửa sổ chính)
            primaryStage.setScene(scene);

            // 6. Hiển thị cửa sổ lên màn hình
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
