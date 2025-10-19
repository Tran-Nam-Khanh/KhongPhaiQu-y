package gameobject;

import javafx.scene.layout.Pane;
// gạch này có 1 máu 1 hit đi luôn
public class NormalBrick extends Brick {

    public NormalBrick(double x, double y, Pane gamePane) {
        // Gọi constructor của Brick (x, y, width, height, hitPoints, imagePath, gamePane)
        super(x, y, 50, 20, 1, "/resources/images/brick/Brick9.png", gamePane);
    }

    @Override
    public void hit(Pane gamePane) {
        super.hit(gamePane);

    }
}
