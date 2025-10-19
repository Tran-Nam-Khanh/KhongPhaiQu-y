package gameobject;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.List;

//QUAN LY CHUYEN DONG
public class Ball extends MovableObject {

    private final double BALL_SPEED = 5; // tốc độ bóng
    private double dx = BALL_SPEED;      // hướng ngang
    private double dy = -BALL_SPEED;     // hướng dọc (âm = đi lên)
    private double sceneWidth;
    private double sceneHeight;
    private Timeline timeline;

    public Ball(Pane gameRoot, double startX, double startY, double sceneWidth, double sceneHeight) {
        super(gameRoot, startX, startY, 0, 0);
        this.sceneWidth = sceneWidth;
        this.sceneHeight = sceneHeight;

        // Tải hình ảnh bóng
        Image image = new Image(getClass().getResourceAsStream("/resources/images/ball/Ball1.png"));
        imageView = new ImageView(image);

        width = image.getWidth();
        height = image.getHeight();

        imageView.setLayoutX(x);
        imageView.setLayoutY(y);
        gameRoot.getChildren().add(imageView);

        startMoving();
    }

   //BAT DAU
    private void startMoving() {
        timeline = new Timeline(new KeyFrame(Duration.millis(16), e -> update()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

//CAP NHAT VI TRI BONG
    @Override
    public void update() {
        x += dx;
        y += dy;
        updatePosition();

        // Va chạm biên trái/phải
        if (x <= 0 || x + width >= sceneWidth) {
            dx *= -1;
        }

        // Va chạm trần
        if (y <= 0) {
            dy *= -1;
        }

        // Nếu rơi ra khỏi màn hình -> reset
        if (y > sceneHeight) {
            resetBall();
        }
    }


    @Override
    public void updatePosition() {
        imageView.setLayoutX(x);
        imageView.setLayoutY(y);
    }


    public void checkPaddleCollision(Paddle paddle) {
        if (imageView.getBoundsInParent().intersects(paddle.getImageView().getBoundsInParent())) {
            dy = -Math.abs(dy); // bật lên

            // Thay đổi góc phản xạ dựa vào vị trí chạm trên paddle
            double hitPos = (x + width / 2) - (paddle.getX() + paddle.getWidth() / 2);
            dx = hitPos * 0.1;
        }
    }


    public void checkBrickCollision(List<Brick> bricks, Pane gameRoot) {
        for (Brick brick : bricks) {
            if (imageView.getBoundsInParent().intersects(brick.getImageView().getBoundsInParent())) {

                // Nếu là gạch không thể phá
                if (brick instanceof UnbreakableBrick) {
                    bounceFromBrick(brick);
                } else {
                    brick.hit(gameRoot);
                    bounceFromBrick(brick);
                }
                break; // chỉ xử lý 1 viên gạch tại 1 khung hình
            }
        }
    }


    private void bounceFromBrick(Brick brick) {
        double ballCenterX = x + width / 2;
        double ballCenterY = y + height / 2;
        double brickCenterX = brick.getX() + brick.getWidth() / 2;
        double brickCenterY = brick.getY() + brick.getHeight() / 2;

        double dxDiff = ballCenterX - brickCenterX;
        double dyDiff = ballCenterY - brickCenterY;

        // Xác định hướng bật: ngang hay dọc
        if (Math.abs(dxDiff) > Math.abs(dyDiff)) {
            dx *= -1; // bật ngang
        } else {
            dy *= -1; // bật dọc
        }
    }

    /**
     * Đặt lại bóng về giữa màn hình khi rơi xuống
     */
    private void resetBall() {
        x = sceneWidth / 2 - width / 2;
        y = sceneHeight / 2 - height / 2;
        dx = BALL_SPEED;
        dy = -BALL_SPEED;
        updatePosition();
    }
}
