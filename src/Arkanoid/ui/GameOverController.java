package Arkanoid.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class GameOverController {

    // Liên kết với Button có fx:id="playAgainButton" trong FXML
    @FXML
    private Button playAgainButton;

    // Liên kết với Button có fx:id="mainMenuButton" trong FXML
    @FXML
    private Button mainMenuButton;

    // Hàm này sẽ được gọi khi nhấn nút "Chơi Lại"
    @FXML
    void handlePlayAgainAction(ActionEvent event) {
        System.out.println("Nút Chơi Lại (Game Over) đã được nhấn!");
        // TODO: Sau này GameManager sẽ gọi hàm restartGame() ở đây
    }

    // Hàm này sẽ được gọi khi nhấn nút "Về Menu Chính"
    @FXML
    void handleMainMenuAction(ActionEvent event) {
        System.out.println("Nút Về Menu (Game Over) đã được nhấn!");
        // TODO: Sau này GameManager sẽ gọi hàm returnToMenu() ở đây
    }
}
