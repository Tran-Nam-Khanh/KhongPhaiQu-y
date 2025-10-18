package Arkanoid.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class WinScreenController {

    @FXML
    private Button playAgainButton;

    @FXML
    private Button mainMenuButton;

    // Hàm này sẽ được gọi khi nhấn nút "Chơi Lại"
    @FXML
    void handlePlayAgainAction(ActionEvent event) {
        System.out.println("Nút Chơi Lại (Win Screen) đã được nhấn!");
        // TODO: Sau này GameManager sẽ gọi hàm restartGame() ở đây
    }

    // Hàm này sẽ được gọi khi nhấn nút "Về Menu Chính"
    @FXML
    void handleMainMenuAction(ActionEvent event) {
        System.out.println("Nút Về Menu (Win Screen) đã được nhấn!");
        // TODO: Sau này GameManager sẽ gọi hàm returnToMenu() ở đây
    }
}
