package Arkanoid.ui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainMenuController {

    @FXML
    private Button startButton;

    @FXML
    private Button exitButton;

    @FXML
    void handleStartButtonAction(ActionEvent event) {
        System.out.println("Nút Bắt Đầu đã được nhấn!");
    }

    @FXML
    void handleExitButtonAction(ActionEvent event) {
        System.out.println("Nút Thoát đã được nhấn! Đang đóng ứng dụng...");
        Platform.exit();
    }
}
