package Arkanoid.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class GameUIController {

    @FXML
    private AnchorPane gameUIPane;

    //liên kết với HBox có fx:id="livesContainer"
    @FXML
    private HBox livesContainer;

    //liên kết với Label có fx:id="levelLabel"
    @FXML
    private Label levelLabel;

    // Biến để lưu ảnh trái tim
    private Image heartImage;
    @FXML
    public void initialize() {
        heartImage = new Image(getClass().getResourceAsStream("/images/items/heart.png"));
    }

    /**
     * Cập nhật số lượng trái tim hiển thị trên màn hình.
     * Hàm này sẽ được GameManager gọi.
     * @param lives Số mạng hiện tại của người chơi.
     */
    public void updateLives(int lives) {
        // Bước 1: Xóa tất cả các trái tim cũ đang hiển thị
        livesContainer.getChildren().clear();

        // Bước 2: Thêm lại đúng số lượng trái tim mới
        for (int i = 0; i < lives; i++) {
            ImageView heartImageView = new ImageView(heartImage);
            // (Tùy chọn) Chỉnh kích thước cho trái tim
            heartImageView.setFitWidth(40);
            heartImageView.setFitHeight(40);
            livesContainer.getChildren().add(heartImageView);
        }
    }

    /**
     * Cập nhật số màn chơi đang hiển thị.
     * Hàm này sẽ được GameManager gọi.
     * @param level Màn chơi hiện tại.
     */
    public void updateLevel(int level) {
        levelLabel.setText("Level " + level);
    }

    /**
     * Ẩn hoặc hiện toàn bộ giao diện HUD.
     * @param isVisible true để hiện, false để ẩn.
     */
    public void setVisible(boolean isVisible) {
        gameUIPane.setVisible(isVisible);
    }
}
