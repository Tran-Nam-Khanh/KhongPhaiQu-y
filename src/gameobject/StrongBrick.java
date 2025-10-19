package gameobject;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;


public class StrongBrick extends Brick {

    public StrongBrick(double x, double y, Pane gamePane) {
        // hitPoints = 1, chỉ dùng 1 ảnh duy nhất
        super(x, y, 40, 20, 1, "resources/images/brick/Brick1.png", gamePane);
    }

    @Override
    public void hit(Pane gamePane) {
        setHitPoints(getHitPoints() - 1);

        if (getHitPoints() <= 0) {
            // Xóa viên gạch khi hết máu
            gamePane.getChildren().remove(getImageView());

            // Rơi ra StrongBallItem
            StrongBallItem item = new StrongBallItem(getX(), getY(), gamePane);
            gamePane.getChildren().add(item.getImageView());
            item.startFalling();
        }
    }
}

