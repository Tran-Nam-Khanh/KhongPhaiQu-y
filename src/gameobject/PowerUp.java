package gameobject;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * Lớp cha cho tất cả các PowerUp (item rơi ra khi phá gạch)
 */
public abstract class PowerUp {
    protected Pane gameRoot;
    protected ImageView imageView;
    protected double x, y;
    protected double speedY = 2; // tốc độ rơi xuống
    protected boolean active = true;

    private AnimationTimer fallTimer;

    public PowerUp(Pane gameRoot, double x, double y, String imagePath) {
        this.gameRoot = gameRoot;
        this.x = x;
        this.y = y;

        Image image = new Image(getClass().getResourceAsStream(imagePath));
        imageView = new ImageView(image);
        imageView.setLayoutX(x);
        imageView.setLayoutY(y);
        gameRoot.getChildren().add(imageView);

        startFalling();
    }

    /**
     * Bắt đầu cho item rơi xuống
     */
    private void startFalling() {
        fallTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!active) {
                    stop();
                    return;
                }
                y += speedY;
                imageView.setLayoutY(y);

                // Nếu rơi quá màn hình thì xóa
                if (y > gameRoot.getHeight()) {
                    remove();
                }

                // Kiểm tra va chạm với paddle
                for (var node : gameRoot.getChildren()) {
                    if (node instanceof Paddle) {
                        Paddle paddle = (Paddle) node;
                        if (checkCollision(paddle)) {
                            applyEffect(paddle);
                            remove();
                            break;
                        }
                    }
                }
            }
        };
        fallTimer.start();
    }

    //kiemtra va cham cua item va thanh paddle
    private boolean checkCollision(Paddle paddle) {
        return imageView.getBoundsInParent().intersects(paddle.getImageView().getBoundsInParent());
    }

    //KHi BAT DUOC THI NHAN HIEU UNG
    protected abstract void applyEffect(Paddle paddle);

    //XOA ITEM RA KHOI MAN HINH
    protected void remove() {
        active = false;
        gameRoot.getChildren().remove(imageView);
    }
}
