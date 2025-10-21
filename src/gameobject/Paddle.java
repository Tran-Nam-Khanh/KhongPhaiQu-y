package gameobject;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class Paddle extends MovableObject {
    private final double MOVE_SPEED = 8;  // tốc độ di chuyển khi dùng phím
    private double sceneWidth;             // giới hạn chiều rộng màn hình

    public Paddle(Pane gameRoot, double startX, double startY, double sceneWidth) {
        super(gameRoot, startX, startY, 0, 0);
        this.sceneWidth = sceneWidth;

        // Tải ảnh paddle
        Image image = new Image(getClass().getResourceAsStream("/resources/images/paddle/paddle1.png"));
        imageView = new ImageView(image);

        // Lấy kích thước ảnh
        width = image.getWidth();
        height = image.getHeight();

        // Đặt vị trí ban đầu
        imageView.setLayoutX(x);
        imageView.setLayoutY(y);

        // Thêm paddle vào Pane
        gameRoot.getChildren().add(imageView);

        // Gắn sự kiện di chuột
        enableMouseControl(gameRoot);
    }

    public void moveLeft() {
        if (x - MOVE_SPEED >= 0) {
            x -= MOVE_SPEED;
            updatePosition();
        }
    }

    public void moveRight() {
        if (x + width + MOVE_SPEED <= sceneWidth) {
            x += MOVE_SPEED;
            updatePosition();
        }
    }


    @Override
    public void updatePosition() {
        imageView.setLayoutX(x);
        imageView.setLayoutY(y);
    }

    @Override
    public void update() {
        // Paddle không tự di chuyển (chỉ do người chơi điều khiển)
    }

    private void enableMouseControl(Pane gameRoot) {
        gameRoot.setOnMouseMoved((MouseEvent e) -> {
            double mouseX = e.getX() - width / 2;

            // Giới hạn không cho paddle ra ngoài
            if (mouseX < 0) mouseX = 0;
            if (mouseX + width > sceneWidth) mouseX = sceneWidth - width;

            x = mouseX;
            updatePosition();
        });
    }
}
