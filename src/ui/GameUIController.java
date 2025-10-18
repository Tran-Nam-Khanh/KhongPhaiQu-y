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

    //HBox có fx:id="livesContainer"
    @FXML
    private HBox livesContainer;

    //Label có fx:id="levelLabel"
    @FXML
    private Label levelLabel;

    private Image heartImage;
    @FXML
    public void initialize() {
        heartImage = new Image(getClass().getResourceAsStream("/images/items/heart.png"));
    }
    //xóa và thêm mạng

    public void updateLives(int lives) {
        livesContainer.getChildren().clear();

        for (int i = 0; i < lives; i++) {
            ImageView heartImageView = new ImageView(heartImage);
            heartImageView.setFitWidth(40);
            heartImageView.setFitHeight(40);
            livesContainer.getChildren().add(heartImageView);
        }
    }

    //số màn đang chơi
    public void updateLevel(int level) {
        levelLabel.setText("Level " + level);
    }

    //ẩn hoặc hiện giao diện HUD
    public void setVisible(boolean isVisible) {
        gameUIPane.setVisible(isVisible);
    }
}
