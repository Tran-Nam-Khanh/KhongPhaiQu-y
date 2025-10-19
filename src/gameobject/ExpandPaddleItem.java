package gameobject;

import javafx.scene.layout.Pane;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

/**
 * Item giúp paddle dài ra tạm thời
 */
public class ExpandPaddleItem extends PowerUp {

    private static final double EXPAND_RATIO = 1.5; // Paddle dài ra 1.5 lần
    private static final double DURATION = 5;       // Thời gian hiệu ứng (giây)

    public ExpandPaddleItem(Pane gameRoot, double x, double y) {
        super(gameRoot, x, y, "/resources/images/items/expand_paddle.png");
    }

    @Override
    protected void applyEffect(Paddle paddle) {
        double originalWidth = paddle.getWidth();

        // Tăng chiều rộng
        paddle.setWidth(originalWidth * EXPAND_RATIO);

        // Tạo hiệu ứng chờ 5 giây, sau đó trả lại kích thước ban đầu
        PauseTransition pause = new PauseTransition(Duration.seconds(DURATION));
        pause.setOnFinished(e -> paddle.setWidth(originalWidth));
        pause.play();
    }
}
