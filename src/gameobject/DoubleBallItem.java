package gameobject;

import javafx.scene.layout.Pane;

/**
 * Item tạo thêm một quả bóng mới khi ăn trúng
 */
public class DoubleBallItem extends PowerUp {

    public DoubleBallItem(Pane gameRoot, double x, double y) {
        super(gameRoot, x, y, "/resources/images/items/double_ball.png");
    }

    @Override
    protected void applyEffect(Paddle paddle) {
        Pane gameRoot = (Pane) paddle.imageView.getParent();

        // Lấy vị trí paddle để thả bóng mới
        double newBallX = paddle.getX() + paddle.getWidth() / 2 - 10;
        double newBallY = paddle.getY() - 20;

        // Tạo bóng mới
        Ball newBall = new Ball(gameRoot, newBallX, newBallY, gameRoot.getWidth(), gameRoot.getHeight());
        newBall.setVelocity(Math.random() > 0.5 ? 3 : -3, -5); // hướng ngẫu nhiên nhẹ

        // Thêm vào game
        gameRoot.getChildren().add(newBall.getImageView());
    }
}
