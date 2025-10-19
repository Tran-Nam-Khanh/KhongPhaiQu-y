package gameobject;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import java.util.Random;

/**
 * Gạch bí ẩn (QuestionBrick) — khi phá sẽ sinh ra một item ngẫu nhiên.
 */
public class QuestionBrick extends Brick {

    private final Random random = new Random();

    public QuestionBrick(Pane gameRoot, double x, double y) {
        super(gameRoot, x, y);

        Image image = new Image(getClass().getResourceAsStream("/resources/images/bricks/question_brick.png"));
        imageView = new ImageView(image);
        width = image.getWidth();
        height = image.getHeight();

        imageView.setLayoutX(x);
        imageView.setLayoutY(y);
        gameRoot.getChildren().add(imageView);
    }

    @Override
    public void hit(Pane gameRoot) {
        // Khi bị bóng chạm → biến mất + spawn item
        gameRoot.getChildren().remove(imageView);
        spawnRandomItem(gameRoot);
    }

    /**
     * Sinh ra một item ngẫu nhiên từ danh sách các loại có sẵn
     */
    private void spawnRandomItem(Pane gameRoot) {
        int type = random.nextInt(5); // 5 loại item khác nhau
        double itemX = x + width / 2;
        double itemY = y + height;

        switch (type) {
            case 0:
                new PowerBallItem(gameRoot, itemX, itemY);
                break;
            case 1:
                new ExpandPaddleItem(gameRoot, itemX, itemY);
                break;
            case 2:
                new DoubleBallItem(gameRoot, itemX, itemY);
                break;
            case 3:
                new ExtraLifeItem(gameRoot, itemX, itemY);
                break;
            case 4:
                new StopTimeItem(gameRoot, itemX, itemY);
                break;
            default:
                new ReversePaddleItem(gameRoot, itemX, itemY);
                break;
        }
    }
}
