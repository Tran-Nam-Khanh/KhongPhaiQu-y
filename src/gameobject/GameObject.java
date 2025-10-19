package gameobject;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public abstract class GameObject {

    protected double x; //toa do x
    protected double y;  //toa do y
    protected double width;
    protected double height; //chieu dai va chieu rong cua vat the
    protected Image image; //luu hinh anh

    public GameObject(double x, double y, double width, double height, Image image) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = image;
    }

    // ======== GETTER / SETTER ========

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    // ======== HÀM CHUNG ========

    //Ve vat the
    public void draw(GraphicsContext gc) {
        if (image != null) {
            gc.drawImage(image, x, y, width, height);
        }
    }

    /** Trả về khung va chạm (dùng để kiểm tra collision) */
    public Rectangle2D getBounds() {
        return new Rectangle2D(x, y, width, height);
    }

    /** Hàm cập nhật mỗi frame (nếu object có chuyển động, override trong class con) */
    public void update(double deltaTime) {
        // Mặc định: không làm gì cả
    }
}
